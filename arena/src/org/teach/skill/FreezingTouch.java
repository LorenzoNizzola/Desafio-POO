package org.teach.skill;

import org.teach.character.Character;
import org.teach.enemy.Enemy;

public class FreezingTouch extends Skill
{
    public FreezingTouch()
    {
        super("Toque Gélido", "Dano leve + o inimigo perde o próximo turno", 20);
    }

    @Override
    public void execute(Character player, Enemy target)
    {
        int damage = target.takeDamage(player.getEffectiveAttack() + 4);
        target.applyStun(1);
        System.out.println(player.getName() + " usou " + name + " e causou " + damage
                + " de dano! " + target.getName() + " foi congelado!");
    }
}
