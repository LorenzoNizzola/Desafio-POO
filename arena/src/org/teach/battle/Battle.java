package org.teach.battle;

import org.teach.character.Character;
import org.teach.enemy.Enemy;
import org.teach.item.HealthPotion;
import org.teach.item.Inventory;
import org.teach.item.Item;
import org.teach.skill.Skill;
import org.teach.util.InputReader;

import java.util.List;
import java.util.Random;

public class Battle
{
    private static final Random RANDOM = new Random();

    private final Character player;
    private final List<Enemy> enemies;
    private final int expReward;
    private final GameStats stats;

    public Battle(Character player, List<Enemy> enemies, int expReward, GameStats stats)
    {
        this.player = player;
        this.enemies = enemies;
        this.expReward = expReward;
        this.stats = stats;
    }

    //Retorna true se o jogador derrotou todos os inimigos; false se morreu.
    public boolean startBattle()
    {
        System.out.println("\n=== A BATALHA COMEÇOU! ===");

        int numero = 1;
        for (Enemy enemy : enemies)
        {
            System.out.println("\n>>> Luta " + numero + " de " + enemies.size());
            System.out.println("Inimigo encontrado: " + enemy.getName() + " (Vida: " + enemy.getHealth() + ")");

            if (!fight(enemy))
            {
                return false;
            }

            stats.registerVictory();
            player.setExp(player.getExp() + expReward);
            System.out.println("Você ganhou " + expReward + " de EXP! Total: " + player.getExp());
            findLoot();
            numero++;
        }
        return true;
    }

    //Uma luta completa contra um inimigo.
    private boolean fight(Enemy enemy)
    {
        while (player.isAlive() && enemy.isAlive())
        {
            showStatus(enemy);

            playersTurn(enemy);
            if (!enemy.isAlive())
            {
                System.out.println("\n🎉 Você derrotou " + enemy.getName() + "!");
                return true;
            }

            System.out.println();
            enemysTurn(enemy);
            if (!enemy.isAlive())
            {
                System.out.println("\n🎉 " + enemy.getName() + " não resistiu aos ferimentos!");
                return true;
            }
            if (!player.isAlive())
            {
                return false;
            }

            player.endTurn(); // remove buffs e regenera recurso
            stats.registerTurn();
        }
        return player.isAlive();
    }

    private void showStatus(Enemy enemy)
    {
        System.out.println("\n----------------------------------------");
        System.out.println("Sua Vida: " + player.getHealth() + "/" + player.getMaxHealth()
                + " | " + player.getResourceName() + ": " + player.getResource() + "/" + player.getMaxResource());
        System.out.println(enemy.getName() + ": " + enemy.getHealth() + " de vida" + enemy.getStatusDescription());
        System.out.println("----------------------------------------");
    }

    // ---------- Turno do jogador ----------

    private void playersTurn(Enemy enemy)
    {
        // A defesa só dura até o próximo turno do jogador, então o reset fica aqui
        player.resetTurnState();

        boolean usedTurn = false;
        while (!usedTurn)
        {
            System.out.println("Escolha sua ação:");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Usar item");
            int action = InputReader.lerInteiro("Digite sua opção: ", 1, 3);

            switch (action) {
                case 1 -> usedTurn = menuDeAtaque(enemy);
                case 2 -> {
                    player.defend();
                    usedTurn = true;
                }
                case 3 -> usedTurn = usarItem();
            }
        }
    }

    //Retorna true se o jogador realmente atacou (false = voltou ao menu).
    private boolean menuDeAtaque(Enemy enemy)
    {
        List<Skill> skills = player.getSkills();

        System.out.println("\n--- Ataques (" + player.getResourceName() + ": " + player.getResource() + ") ---");
        for (int i = 0; i < skills.size(); i++)
        {
            Skill s = skills.get(i);
            String warning = player.canUse(s) ? "" : "  [" + player.getResourceName() + " insuficiente]";
            System.out.println((i + 1) + ". " + s.getName() + " (custo " + s.getCost() + ") - " + s.getDescription() + warning);
        }
        System.out.println("0. Voltar");

        int choice = InputReader.lerInteiro("Escolha o ataque: ", 0, skills.size());
        if (choice == 0) {
            return false;
        }

        Skill skill = skills.get(choice - 1);
        if (!player.canUse(skill)) {
            System.out.println(player.getResourceName() + " insuficiente para " + skill.getName() + "!");
            return false;
        }

        player.spendResource(skill);
        skill.execute(player, enemy);
        return true;
    }

    //Retorna true se um item foi consumido (false = cancelou ou item sem efeito).
    private boolean usarItem()
    {
        Inventory inventory = player.getInventory();
        if (inventory.isEmpty())
        {
            System.out.println("Seu inventário está vazio!");
            return false;
        }

        System.out.println("\n--- Inventário ---");
        inventory.show();
        System.out.println("0. Voltar");

        int choice = InputReader.lerInteiro("Escolha o item: ", 0, inventory.size());
        if (choice == 0)
        {
            return false;
        }

        Item item = inventory.get(choice - 1);
        if (item.use(player)) {
            inventory.remove(choice - 1);
            stats.registerItemUsed();
            return true;
        }
        return false;
    }

    // ---------- Turno do inimigo ----------

    private void enemysTurn(Enemy enemy)
    {
        // A guarda do inimigo vale durante o turno do jogador, então o reset é no início do turno dele
        enemy.resetTurnState();

        if (enemy.processStatusEffects())
        {
            enemy.act(player);
        }
    }

    // ---------- Recompensa ----------

    private void findLoot()
    {
        if (RANDOM.nextInt(100) < 50)
        {
            player.getInventory().add(new HealthPotion(20));
            System.out.println("🎁 O inimigo deixou cair uma Poção de Cura!");
        }
    }
}
