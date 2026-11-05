package org.teach.util;

//Fórmula única de mitigação, usada por jogadores e inimigos quando estão defendendo.
public final class DamageCalculator
{
    // Defender corta o dano pela metade e ainda subtrai parte da defesa, mas nunca zera o golpe.
    public static int mitigate(int rawDamage, int defense)
    {
        return Math.max(1, rawDamage / 2 - defense / 5);
    }
}
