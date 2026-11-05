package org.teach.character;

import org.teach.item.Inventory;
import org.teach.skill.BasicStrike;
import org.teach.skill.HeavySlash;

public class Fighter extends Character
{
    public Fighter(String name, int health, int attack, int defense, int exp, int energy, Inventory inventory)
    {
        super(name, health, attack, defense, exp, "Energia", energy, inventory);
        skills.add(new BasicStrike("Golpe Simples"));
        skills.add(new HeavySlash());
    }

    @Override
    protected int getResourceRegen()
    {
        return 10;
    }
}
