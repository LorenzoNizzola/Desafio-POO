package org.teach.item;

import org.teach.character.Character;
import org.teach.character.Mage;

public class ManaPotion extends Item
{
    private final int manaAmount;

    public ManaPotion(int manaAmount)
    {
        super("Poção de Mana", "Restaura " + manaAmount + " de mana (apenas magos)");
        this.manaAmount = manaAmount;
    }

    @Override
    public boolean use(Character player)
    {
        if (!(player instanceof Mage))
        {
            System.out.println("Este item só pode ser usado por magos!");
            return false; //não consome o item
        }
        player.restoreResource(manaAmount);
        System.out.println(player.getName() + " recuperou " + manaAmount + " de mana!");
        return true;
    }
}
