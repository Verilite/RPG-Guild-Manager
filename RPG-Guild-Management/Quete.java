import Heros.BaseHero;

public class Quete {

    /**Voici tous les variables du quête et les getters**/

    /**Difficulté de la quête**/
    private int difficulty;
    public int getDifficulty(){
        return difficulty;
    }

    /**Coût en HP de la quête**/
    private double hpCost;
    public double getHpCost(){
        return hpCost;
    }

    /**Récompense d'argent**/
    private int moneyReward;
    public int getMoneyReward(){
        return moneyReward;
    }

    /**Récompense d'armures**/
    private int armorReward;
    public int getArmorReward(){
        return armorReward;
    }

    /**Si la quête était un succès. Par défaut, on le mets à false**/
    private boolean questSuccess = false;
    public boolean getQuestSuccess(){
        return questSuccess;
    }

    /**Voici les fonctions des quêtes*/
    public void doQuest(Guild guild, BaseHero hero, int heroLevel){

        /**On calcule le HP que le héros perds lors de la quête et on le soustrait de son HP actuel.**/
        Double lostHP = hpCost - (heroLevel - difficulty) * 1.5;
        hero.removeHP(lostHP);

        /**Si le héros n'a plus de HP, la quête échoue, sinon elle réussit.**/
        if (hero.getHP() <= 0){
            /** Quete échoué !**/
            guild.removeHero(hero, heroLevel);

        } else {
            /** Quete réussie !**/
            guild.bank.addFunds((double)moneyReward);
            guild.bank.addArmor(armorReward);
        }

    }


    /**Voici les constructors de notre quête**/
    public Quete(int difficulty, double hpCost, int moneyReward, int armorReward){
        this.difficulty = difficulty;
        this.hpCost = hpCost;
        this.moneyReward = moneyReward;
        this.armorReward = armorReward;
    }

}
