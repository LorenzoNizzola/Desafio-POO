package org.teach.enemy;

import org.teach.character.Character;
import java.util.Random;

public class EnemySpecial extends Enemy
{
    private static final Random RANDOM = new Random();

    public EnemySpecial(String name, int health, int attack, int defense)
    {
        super(name, health, attack, defense);
    }

    @Override
    public void act(Character player)
    {
        boolean lowHealth = health <= 50;
        boolean criticalHealth = health <= 15;

        if (criticalHealth && RANDOM.nextInt(100) < 50)
        {
            int criticalDamage = Math.max(1, attack * 2);
            int damage = player.takeDamage(criticalDamage);
            System.out.println("⚠️ " + name + " desferiu um ATAQUE ESPECIAL CRÍTICO e causou " + damage + " de dano!");
            return;
        }

        if (lowHealth && RANDOM.nextInt(100) < 70)
        {
            System.out.println(name + " está acuado e assumiu uma defesa pesada!");
            defend();
            return;
        }

        int damage = player.takeDamage(attack);
        System.out.println(name + " atacou você e causou " + damage + " de dano!");
    }
}
