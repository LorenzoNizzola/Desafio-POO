package org.teach.character;

import org.teach.item.Inventory;
import org.teach.skill.BasicStrike;
import org.teach.skill.PreciseShot;

public class Archer extends Character {
    public Archer(String name, int health, int attack, int defense, int exp, int focus, Inventory inventory)
    {
        super(name, health, attack, defense, exp, "Foco", focus, inventory);
        skills.add(new BasicStrike("Tiro Rápido"));
        skills.add(new PreciseShot());
    }

    @Override
    protected int getResourceRegen()
    {
        return 8;
    }
}
