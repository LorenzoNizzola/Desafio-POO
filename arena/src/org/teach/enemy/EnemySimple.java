package org.teach.enemy;

import org.teach.character.Character;
import java.util.Random;

public class EnemySimple extends Enemy
{
    private static final Random RANDOM = new Random();

    public EnemySimple(String name, int health, int attack, int defense)
    {
        super(name, health, attack, defense);
    }

    @Override
    public void act(Character player)
    {
        // IA simples: 80% ataca, 20% defende
        if (RANDOM.nextInt(100) < 80)
        {
            int damage = player.takeDamage(this.attack);
            System.out.println(name + " atacou você e causou " + damage + " de dano!");
        }
        else
        {
            defend();
        }
    }
}
