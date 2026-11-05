package org.teach.item;

import org.teach.character.Character;

public class HealthPotion extends Item
{
    private final int healAmount;

    public HealthPotion(int healAmount)
    {
        super("Poção de Cura", "Restaura " + healAmount + " de vida");
        this.healAmount = healAmount;
    }

    @Override
    public boolean use(Character player)
    {
        player.heal(healAmount);
        return true;
    }
}
