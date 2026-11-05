package org.teach.skill;

import org.teach.character.Character;
import org.teach.enemy.Enemy;

public abstract class Skill
{
    protected final String name;
    protected final String description;
    protected final int cost;

    public Skill(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // O custo já é cobrado por quem chama; aqui fica só o efeito.
    public abstract void execute(Character user, Enemy target);

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getCost()
    {
        return cost;
    }
}
