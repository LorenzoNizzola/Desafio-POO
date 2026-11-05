package org.teach.item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Inventory
{
    private final List<Item> items = new ArrayList<>();

    public void add(Item item)
    {
        items.add(item);
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public int size()
    {
        return items.size();
    }

    public Item get(int index)
    {
        return items.get(index);
    }

    public void remove(int index)
    {
        items.remove(index);
    }

    public void show()
    {
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getDescription());
        }
    }

    @Override
    public String toString()
    {
        if (items.isEmpty())
        {
            return "vazio";
        }

        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Item item : items)
        {
            counts.merge(item.getName(), 1, Integer::sum);
        }

        List<String> parts = new ArrayList<>();
        counts.forEach((nome, qtd) -> parts.add(nome + " x" + qtd));
        return String.join(", ", parts);
    }
}
