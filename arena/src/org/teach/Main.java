package org.teach;

import org.teach.battle.Battle;
import org.teach.battle.GameStats;
import org.teach.character.Archer;
import org.teach.character.Character;
import org.teach.character.Fighter;
import org.teach.character.Mage;
import org.teach.enemy.Enemy;
import org.teach.enemy.EnemyGenerator;
import org.teach.item.HealthPotion;
import org.teach.item.Inventory;
import org.teach.item.ManaPotion;
import org.teach.item.StrengthElixir;
import org.teach.util.InputReader;

import java.util.List;

public class Main
{
    private static final int NUMERO_DE_INIMIGOS = 20;
    private static final int EXP_POR_VITORIA = 50;

    public static void main(String[] args)
    {
        startGame();
    }

    private static void startGame()
    {
        System.out.println("""
                Um aventureiro errante busca experiência e forturnas, então adentra uma masmorra antiga
                em busca de seus tesouros perdidos. Você é este aventureiro...
                """);
        String name = InputReader.lerTexto("Digite seu nome: ");

        Character player = chooseClass(name);
        showStatus(player);

        List<Enemy> enemies = EnemyGenerator.generateEnemies(NUMERO_DE_INIMIGOS);
        GameStats stats = new GameStats();

        Battle dungeon = new Battle(player, enemies, EXP_POR_VITORIA, stats);
        boolean won = dungeon.startBattle();

        finishGame(player, won, stats);
    }

    private static Character chooseClass(String name)
    {
        System.out.println("""

                Escolha uma classe:
                1. Guerreiro - vida alta e defesa sólida, usa Energia
                2. Mago      - frágil, mas usa magias com efeitos (queimar, congelar), usa Mana
                3. Arqueiro  - equilibrado, ataques precisos que ignoram a guarda, usa Foco
                """);
        int option = InputReader.lerInteiro("Digite sua opção: ", 1, 3);

        return switch (option)
        {
            case 1 -> new Fighter(name, 100, 22, 20, 0, 50, createBase());
            case 2 -> {
                Inventory inv = createBase();
                inv.add(new ManaPotion(40));
                inv.add(new ManaPotion(40));
                yield new Mage(name, 60, 12, 10, 0, 100, inv);
            }
            default -> new Archer(name, 80, 16, 14, 0, 60, createBase());
        };
    }

    private static Inventory createBase()
    {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 3; i++)
        {
            inventory.add(new HealthPotion(20));
        }
        inventory.add(new StrengthElixir());
        return inventory;
    }

    private static void showStatus(Character character)
    {
        System.out.println("\n--- Status do Personagem ---");
        System.out.println("Nome: " + character.getName());
        System.out.println("Vida: " + character.getHealth() + "/" + character.getMaxHealth());
        System.out.println("Ataque: " + character.getAttack());
        System.out.println("Defesa: " + character.getDefense());
        System.out.println(character.getResourceName() + ": " + character.getResource() + "/" + character.getMaxResource());
        System.out.println("EXP: " + character.getExp());
        System.out.println("Inventário: " + character.getInventory());
        System.out.println("---------------------------");
    }

    private static void finishGame(Character player, boolean won, GameStats stats)
    {
        System.out.println("\n========================================");
        if (won) {
            System.out.println("🏆 Você chegou ao fundo da masmorra, parabéns " + player.getName() + "!");
        } else {
            System.out.println("💀 Você foi derrotado na masmorra... Fim de jogo.");
        }
        System.out.println("Batalhas vencidas: " + stats.getBattlesWon() + "/" + NUMERO_DE_INIMIGOS);
        System.out.println("Turnos jogados: " + stats.getTurns());
        System.out.println("Itens usados: " + stats.getItemsUsed());
        System.out.println("Vida final: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("EXP total: " + player.getExp());
        System.out.println("========================================");
    }
}
