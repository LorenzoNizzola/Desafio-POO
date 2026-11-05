package org.teach.character;

import org.teach.item.Inventory;
import org.teach.skill.BasicStrike;
import org.teach.skill.Fireball;
import org.teach.skill.FreezingTouch;

public class Mage extends Character
{
    public Mage(String name, int health, int attack, int defense, int exp, int magic, Inventory inventory)
    {
        super(name, health, attack, defense, exp, "Mana", magic, inventory);
        skills.add(new BasicStrike("Cajadada"));
        skills.add(new Fireball());
        skills.add(new FreezingTouch());
    }

    public int getMagic()
    {
        return resource;
    }

    @Override
    protected int getResourceRegen()
    {
        return 5;
    }
}
