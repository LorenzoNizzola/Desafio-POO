package org.teach.skill;

import org.teach.character.Character;
import org.teach.enemy.Enemy;

public class HeavySlash extends Skill
{
    public HeavySlash()
    {
        super("Golpe Pesado", "Dano de 1.5x o ataque", 20);
    }

    @Override
    public void execute(Character player, Enemy target)
    {
        int damage = target.takeDamage((int) (player.getEffectiveAttack() * 1.5));
        System.out.println(player.getName() + " usou " + name + " e causou " + damage + " de dano!");
    }
}
