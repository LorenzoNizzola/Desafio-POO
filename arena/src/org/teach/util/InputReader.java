package org.teach.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class InputReader
{
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerTexto(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String line = readLine();
            if (!line.isBlank()) {
                return line;
            }
            System.out.println("O texto não pode ser vazio.");
        }
    }

    //Lê um inteiro dentro do intervalo [min, max], repetindo até a entrada ser válida.
    public static int lerInteiro(String prompt, int min, int max)
    {
        while (true)
        {
            System.out.print(prompt);
            try
            {
                int value = Integer.parseInt(readLine());
                if (value >= min && value <= max)
                {
                    return value;
                }
            }
            catch (NumberFormatException e)
            {
                // cai na mensagem abaixo
            }
            System.out.println("Entrada inválida. Digite um número de " + min + " a " + max + ".");
        }
    }

    private static String readLine()
    {
        try
        {
            return scanner.nextLine().trim();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("\nEntrada encerrada. Saindo do jogo.");
            System.exit(0);
            return "";
        }
    }
}
