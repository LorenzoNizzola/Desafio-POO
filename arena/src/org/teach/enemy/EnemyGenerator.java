package org.teach.enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyGenerator
{
    private static final Random RANDOM = new Random();

    private static final String[] NOMES_SIMPLES = {
        "Goblin Surdo", "Esqueleto Cambaleante", "Slime Gosmento","Rato Perdido","Gnomo Ladrão",
            "Anão Alto","Rato Gigante","Sereia da Terra","Sapo Falante","Cervo Bípede"
    };

    private static final String[] NOMES_ESPECIAIS = {
        "Olgrath, O Devorador de Faces", "Fada Maligna", "Cavaleiro Corrompido", "Lagarto de Fogo",
            "Centauro sem Cabeça","Tokurk, O Removedor de Faces","Esqueleto Equilibrista","Goblin Cego"
    };

    public static Enemy generateRandomEnemy()
    {
        if (RANDOM.nextBoolean())
        {
            String name = NOMES_ESPECIAIS[RANDOM.nextInt(NOMES_ESPECIAIS.length)];
            return new EnemySpecial(name, RANDOM.nextInt(40) + 40, RANDOM.nextInt(8) + 10, RANDOM.nextInt(3) + 10);
        }
        String name = NOMES_SIMPLES[RANDOM.nextInt(NOMES_SIMPLES.length)];
        return new EnemySimple(name, RANDOM.nextInt(30) + 20, RANDOM.nextInt(6) + 6, RANDOM.nextInt(2) + 5);
    }

    public static List<Enemy> generateEnemies(int amount)
    {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            enemies.add(generateRandomEnemy());
        }
        return enemies;
    }
}
