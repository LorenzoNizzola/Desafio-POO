package org.teach.skill;

import org.teach.character.Character;
import org.teach.enemy.Enemy;

public class Fireball extends Skill
{
    public Fireball()
    {
        super("Bola de Fogo", "Dano alto + queimadura (4 de dano por 3 turnos)", 25);
    }

    @Override
    public void execute(Character player, Enemy target)
    {
        int damage = target.takeDamage(player.getEffectiveAttack() + 14);
        target.applyBurn(4, 3);
        System.out.println(player.getName() + " lançou " + name + " e causou " + damage
                + " de dano! " + target.getName() + " está queimando!");
    }
}
