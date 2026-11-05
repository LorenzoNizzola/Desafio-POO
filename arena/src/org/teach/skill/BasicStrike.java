package org.teach.skill;

import org.teach.character.Character;
import org.teach.enemy.Enemy;

public class BasicStrike extends Skill
{
    public BasicStrike(String name)
    {
        super(name, "Ataque simples, sem custo", 0);
    }

    @Override
    public void execute(Character player, Enemy target)
    {
        int damage = target.takeDamage(player.getEffectiveAttack());
        System.out.println(player.getName() + " usou " + name + " e causou " + damage + " de dano!");
    }
}
