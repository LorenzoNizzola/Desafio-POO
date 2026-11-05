package org.teach.battle;

public class GameStats
{
    private int battlesWon = 0;
    private int itemsUsed = 0;
    private int turns = 0;

    public void registerVictory()
    {
        battlesWon++;
    }

    public void registerItemUsed()
    {
        itemsUsed++;
    }

    public void registerTurn()
    {
        turns++;
    }

    public int getBattlesWon()
    {
        return battlesWon;
    }

    public int getItemsUsed()
    {
        return itemsUsed;
    }

    public int getTurns()
    {
        return turns;
    }
}
