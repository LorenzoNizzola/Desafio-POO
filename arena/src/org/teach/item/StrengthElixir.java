package org.teach.item;

import org.teach.character.Character;

public class StrengthElixir extends Item
{
    private static final int BONUS = 8;
    private static final int TURNS = 3;

    public StrengthElixir()
    {
        super("Elixir de Força", "+" + BONUS + " de ataque por " + TURNS + " turnos");
    }

    @Override
    public boolean use(Character player)
    {
        player.addAttackBonus(BONUS, TURNS);
        System.out.println(player.getName() + " ficou mais forte! +" + BONUS + " de ataque por " + TURNS + " turnos.");
        return true;
    }
}
