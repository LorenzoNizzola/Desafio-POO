package org.teach.item;

import org.teach.character.Character;

public abstract class Item
{
    protected final String name;
    protected final String description;

    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    /*
      Aplica o efeito do item.
      e retorna true se o item foi realmente usado (e deve ser consumido).
    */

    public abstract boolean use(Character player);

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}
