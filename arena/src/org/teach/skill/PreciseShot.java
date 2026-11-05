package org.teach.skill;

import org.teach.character.Character;
import org.teach.enemy.Enemy;

public class PreciseShot extends Skill
{
    public PreciseShot()
    {
        super("Tiro Preciso", "Ignora a postura defensiva do inimigo", 15);
    }

    @Override
    public void execute(Character player, Enemy target)
    {
        int damage = target.takeDirectDamage((int) (player.getEffectiveAttack() * 1.3));
        System.out.println(player.getName() + " usou " + name + " e causou " + damage + " de dano direto!");
    }
}
