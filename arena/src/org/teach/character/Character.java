package org.teach.character;

import org.teach.enemy.Enemy;
import org.teach.item.Inventory;
import org.teach.skill.Skill;
import org.teach.util.DamageCalculator;

import java.util.ArrayList;
import java.util.List;

public abstract class Character
{
    protected final String name;
    protected int health;
    protected final int maxHealth;
    protected int attack;
    protected int defense;
    protected int exp;
    protected boolean isDefending;
    protected final Inventory inventory;

    // Recurso da classe (Energia, Mana, Foco...)
    protected final String resourceName;
    protected int resource;
    protected final int maxResource;

    // Buff temporário de ataque
    private int attackBonus = 0;
    private int attackBonusTurns = 0;

    protected final List<Skill> skills = new ArrayList<>();

    public Character(String name, int health, int attack, int defense, int exp,
                     String resourceName, int resource, Inventory inventory)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.isDefending = false;
        this.resourceName = resourceName;
        this.resource = resource;
        this.maxResource = resource;
        this.inventory = inventory;
    }

    protected int getResourceRegen()
    {
        return 0;
    }

    // ---------- Combate ----------

    //Ataque básico
    public void attackEnemy(Enemy enemy)
    {
        int damage = enemy.takeDamage(getEffectiveAttack());
        System.out.println(name + " atacou " + enemy.getName() + " e causou " + damage + " de dano!");
    }

    public void defend()
    {
        this.isDefending = true;
        System.out.println(name + " assumiu uma postura defensiva!");
    }

    // Retorna o dano sofrido
    public int takeDamage(int rawDamage)
    {
        int finalDamage = rawDamage;
        if (isDefending)
        {
            finalDamage = DamageCalculator.mitigate(rawDamage, defense);
            System.out.println(name + " defendeu o golpe! Dano reduzido de " + rawDamage + " para " + finalDamage + ".");
        }
        this.health = Math.max(0, this.health - finalDamage);
        return finalDamage;
    }

    public void resetTurnState()
    {
        this.isDefending = false;
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public void heal(int amount)
    {
        int before = health;
        health = Math.min(maxHealth, health + amount);
        System.out.println(name + " recuperou " + (health - before) + " de vida! Vida atual: " + health + "/" + maxHealth);
    }

    // ---------- Recurso e habilidades ----------

    public boolean canUse(Skill skill)
    {
        return resource >= skill.getCost();
    }

    public void spendResource(Skill skill)
    {
        resource -= skill.getCost();
    }

    public void restoreResource(int amount)
    {
        resource = Math.min(maxResource, resource + amount);
    }

    public List<Skill> getSkills()
    {
        return skills;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public int getResource()
    {
        return resource;
    }

    public int getMaxResource()
    {
        return maxResource;
    }

    // ---------- Buffs ----------

    public void addAttackBonus(int bonus, int turns) {
        this.attackBonus = bonus;
        this.attackBonusTurns = turns;
    }

    public int getEffectiveAttack()
    {
        return attack + attackBonus;
    }

    public void endTurn()
    {
        if (attackBonusTurns > 0 && --attackBonusTurns == 0)
        {
            attackBonus = 0;
            System.out.println("O bônus de ataque de " + name + " acabou.");
        }
        restoreResource(getResourceRegen());
    }

    // ---------- Getters / Setters ----------
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public void setHealth(int health) { this.health = Math.min(maxHealth, Math.max(0, health)); }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }
    public Inventory getInventory() { return inventory; }
}
