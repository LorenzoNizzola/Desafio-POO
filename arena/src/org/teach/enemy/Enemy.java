package org.teach.enemy;

import org.teach.character.Character;
import org.teach.util.DamageCalculator;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy
{
    protected final String name;
    protected int health;
    protected final int attack;
    protected final int defense;
    protected boolean isDefending;

    // Efeitos de status
    private int burnTurns = 0;
    private int burnDamage = 0;
    private int stunTurns = 0;

    public Enemy(String name, int health, int attack, int defense)
    {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.isDefending = false;
    }

    public abstract void act(Character player);

    public void defend()
    {
        this.isDefending = true;
        System.out.println(name + " adotou uma postura defensiva.");
    }

    public int takeDamage(int damage)
    {
        int real = damage;
        if (isDefending)
        {
            real = DamageCalculator.mitigate(damage, defense);
            System.out.println(name + " defendeu o ataque! Dano reduzido de " + damage + " para " + real + ".");
        }
        return applyDamage(real);
    }

    public int takeDirectDamage(int damage)
    {
        return applyDamage(damage);
    }

    private int applyDamage(int damage)
    {
        this.health = Math.max(0, this.health - damage);
        return damage;
    }

    // ---------- Efeitos de status ----------

    public void applyBurn(int damage, int turns)
    {
        this.burnDamage = damage;
        this.burnTurns = turns;
    }

    public void applyStun(int turns)
    {
        this.stunTurns = turns;
    }

    /*
     Processa queimadura e atordoamento no início do turno do inimigo.
     E retorna true se o inimigo ainda pode agir neste turno.
     */
    public boolean processStatusEffects()
    {
        if (burnTurns > 0)
        {
            takeDirectDamage(burnDamage);
            burnTurns--;
            System.out.println("🔥 " + name + " sofreu " + burnDamage + " de dano de queimadura!");
            if (!isAlive()) return false;
        }
        if (stunTurns > 0)
        {
            stunTurns--;
            System.out.println("❄️ " + name + " está congelado e perdeu o turno!");
            return false;
        }
        return true;
    }

    public String getStatusDescription()
    {
        List<String> status = new ArrayList<>();
        if (burnTurns > 0)
        {
            status.add("🔥 queimando (" + burnTurns + ")");
        }
        if (stunTurns > 0)
        {
            status.add("❄️ congelado");
        }
        if (isDefending)
        {
            status.add("🛡️ em guarda");
        }
        return status.isEmpty() ? "" : " [" + String.join(", ", status) + "]";
    }

    public void resetTurnState()
    {
        this.isDefending = false;
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }
}
