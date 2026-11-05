Masmorra Anciã - Batalha em turnos no terminal (Java)

Requisitos: JDK 17 ou superior (usa switch com seta e text blocks).

Estrutura (src/org/teach):
  Main.java                 -> iniciarJogo(), escolha de classe, exibirStatus(), fimDeJogo()
  battle/                   -> Battle (turnos) e GameStats (batalhas, turnos, itens usados)
  character/                -> Character (abstrata), Fighter, Mage, Archer
  enemy/                    -> Enemy (abstrata), EnemySimple, EnemySpecial, EnemyGenerator
  item/                     -> Item (abstrata), HealthPotion, ManaPotion, StrengthElixir, Inventory
  skill/                    -> Skill (abstrata), BasicStrike, HeavySlash, Fireball, FreezingTouch, PreciseShot
  util/                     -> InputReader (Scanner único + validação), DamageCalculator

Como compilar e executar (a partir da pasta arena/):
  Linux/Mac:
    javac -encoding UTF-8 -d out $(find src -name '*.java')
    java -cp out org.teach.Main
  Windows (PowerShell):
    javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
    java -Dfile.encoding=UTF-8 -cp out org.teach.Main

Na IDE (IntelliJ/Eclipse): copie a pasta src/org para o seu projeto e rode Main.

Como jogar:
  1. Informe seu nome e escolha a classe (Guerreiro, Mago ou Arqueiro).
  2. A cada turno: Atacar (menu de habilidades da classe), Defender ou Usar item.
  3. Derrote os 3 inimigos para vencer. Se sua vida chegar a 0, você perde.
