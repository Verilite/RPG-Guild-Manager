/**
 * TP1 - IFT1025 - par Seifeddine Maaroufi et Jaeheon Kim
 * On recrée un Guild qu'on pourrait voir dans un jeu vidéo dans ce programme.
 */
import java.util.*;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] userInput = scanner.nextLine().split(" ");

        /**
         * Voici les Strings qu'on a utilisé durant les tests.
         *String[] exemple1 = "guild:100.0,10 buy-hero:Berserker,2,52.5,6,30.5 do-quest:2,5.3,60,3".split(" ");
         *String[] exemple2 = "guild:100.0,10 buy-hero:Berserker,2,52.5,6,30.5 buy-hero:Zorro,1,36.2,2,15.0 do-quest:2,5.3,60,3 train-hero:Zorro".split(" ");
         *String[] exemple3 = "guild:100.0,10 buy-hero:Rogue,3,73.5,7,40.7 train-hero:Rogue do-quest:2,5.3,60,3 train-hero:Zorro".split(" ");
         *String[] entré = exemple2; // {exemple1, exemple2, exemple3, args}
         **/

        /**Déclaration des variables**/
        Guild myGuild = null;
        GuildCommandSystem myGuildCommandSystem = new GuildCommandSystem(userInput);

        /** On exécute toutes les Commandes du jeu **/
        myGuildCommandSystem.executeAllCommands(myGuild);

        /** J'affiche le rapport de l'état du jeu apres l'exécution des commandes **/
        String gameReport = myGuildCommandSystem.generateGameReport();
        System.out.println(gameReport);

    }
}