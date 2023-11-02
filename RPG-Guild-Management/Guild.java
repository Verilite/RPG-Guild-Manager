import java.util.*;
import Heros.*;

public class Guild {

    /**Variables**/
    public Bank bank;

    private ArrayList<HeroNiveau1> level1HeroList = new ArrayList<HeroNiveau1>();
    private ArrayList<HeroNiveau2> level2HeroList = new ArrayList<HeroNiveau2>();
    private ArrayList<HeroNiveau3> level3HeroList = new ArrayList<HeroNiveau3>();
    private ArrayList<HeroNiveau4> level4HeroList = new ArrayList<HeroNiveau4>();
    private ArrayList<HeroNiveau5> level5HeroList = new ArrayList<HeroNiveau5>();

    /**Fonctions**/
    public BaseHero getHeroByName(String heroName) {

        /**Recherche parmis les Heros de Niveau 1**/
        for (BaseHero hero : level1HeroList)
            if (hero.getName().equals(heroName))
                return hero;

        /**Recherche parmis les Heros de Niveau 2**/
        for (BaseHero hero : level2HeroList)
            if (hero.getName().equals(heroName))
                return hero;

        /**Recherche parmis les Heros de Niveau 3**/
        for (BaseHero hero : level3HeroList)
            if (hero.getName().equals(heroName))
                return hero;

        /**Recherche parmis les Heros de Niveau 4**/
        for (BaseHero hero : level4HeroList)
            if (hero.getName().equals(heroName))
                return hero;

        /**Recherche parmis les Heros de Niveau 5**/
        for (BaseHero hero : level5HeroList)
            if (hero.getName().equals(heroName))
                return hero;

        return null;
    }

    public int getHeroLevelByName(String heroName) {
        /**Recherche parmis les Heros de Niveau 1**/
        for (BaseHero hero : level1HeroList)
            if (hero.getName().equals(heroName))
                return 1;

        /**Recherche parmis les Heros de Niveau 2**/
        for (BaseHero hero : level2HeroList)
            if (hero.getName().equals(heroName))
                return 2;

        /**Recherche parmis les Heros de Niveau 3**/
        for (BaseHero hero : level3HeroList)
            if (hero.getName().equals(heroName))
                return 3;

        /**Recherche parmis les Heros de Niveau 4**/
        for (BaseHero hero : level4HeroList)
            if (hero.getName().equals(heroName))
                return 4;

        /**Recherche parmis les Heros de Niveau 5**/
        for (BaseHero hero : level5HeroList)
            if (hero.getName().equals(heroName))
                return 5;

        return -1;
    }

    public void trainHero(BaseHero hero, int currentHeroLevel) {

        /**On paie pour l'entrainement de l'héros**/
        bank.removeFunds(20 * Math.log(currentHeroLevel + 10));
        bank.removeArmor((int) Math.ceil(Math.log(currentHeroLevel + 10)));

        /**On augmente le niveau de l'héros. Si au max, on fait rien.**/
        switch (currentHeroLevel) {
            case (1): {
                level1HeroList.remove(hero);
                level2HeroList.add(new HeroNiveau2(
                        hero.getName(),
                        hero.getCategory(),
                        hero.getHP() + hero.getMaxHP() * 0.5,
                        hero.getMaxHP() * 1.5));

                break;
            }
            case (2): {
                level2HeroList.remove(hero);
                level3HeroList.add(new HeroNiveau3(
                        hero.getName(),
                        hero.getCategory(),
                        hero.getHP() + hero.getMaxHP() * 0.5,
                        hero.getMaxHP() * 1.5));

                break;
            }
            case (3): {
                level3HeroList.remove(hero);
                level4HeroList.add(new HeroNiveau4(
                        hero.getName(),
                        hero.getCategory(),
                        hero.getHP() + hero.getMaxHP() * 0.5,
                        hero.getMaxHP() * 1.5));

                break;
            }
            case (4): {
                level4HeroList.remove(hero);
                level5HeroList.add(new HeroNiveau5(
                        hero.getName(),
                        hero.getCategory(),
                        hero.getHP() + hero.getMaxHP() * 0.5,
                        hero.getMaxHP() * 1.5));

                break;
            }
        }

    }

    public void buyHero(String heroName, int heroCategory, Double heroPrice, int heroArmorPrice, Double heroHP) {

        /**On paie pour le héros acheté.**/
        bank.removeFunds(heroPrice);
        bank.removeArmor(heroArmorPrice);

        /**On ajoute le héros qu'on a acheté dans le guild.**/
        switch (heroCategory) {
            case (0): {
                level1HeroList.add(new HeroNiveau1(heroName, heroCategory, heroHP, heroHP));
                break;
            }
            case (1): {
                level1HeroList.add(new HeroNiveau1(heroName, heroCategory, heroHP, heroHP));
                break;
            }
            case (2): {
                level2HeroList.add(new HeroNiveau2(heroName, heroCategory, heroHP, heroHP));
                break;
            }
            case (3): {
                level3HeroList.add(new HeroNiveau3(heroName, heroCategory, heroHP, heroHP));
                break;
            }
            case (4): {
                level4HeroList.add(new HeroNiveau4(heroName, heroCategory, heroHP, heroHP));
                break;
            }
        }

    }

    public void removeHero(BaseHero hero, int heroLevel) {

        /**On enlève le héros de la guilde.**/
        switch (heroLevel) {
            case (1): {
                level1HeroList.remove(hero);
                break;
            }
            case (2): {
                level2HeroList.remove(hero);
                break;
            }
            case (3): {
                level3HeroList.remove(hero);
                break;
            }
            case (4): {
                level4HeroList.remove(hero);
                break;
            }
            case (5): {
                level5HeroList.remove(hero);
                break;
            }
        }

    }

    public void buyArmor(int armorCount, Double armorPrice) {

        /**On paie pour l'armure acheté**/
        bank.removeFunds(armorPrice * armorCount);

        /**On rajoute l'Armure achetée à la guilde**/
        bank.addArmor(armorCount);

    }

    /**Constructors**/
    public Guild(double funds, int armor) {

        /**Initialisation de la banque**/
        this.bank = new Bank(funds, armor);

    }

    public BaseHero getNearestLevelHero(int desiredLevel) {

        /**déclaration**/
        BaseHero outputHero;

        /**On cherche un héros du même niveau que le niveau désiré**/
        outputHero = getTheFirstHeroFromThisLevel(desiredLevel);
        if (outputHero != null)
            return outputHero;

        int delta = Math.max(desiredLevel, 5 - desiredLevel);
        for (int i = 1; i <= delta; i++) {
            /**On cherche un héros de niveau supérieur**/
            outputHero = getTheFirstHeroFromThisLevel(desiredLevel + 1);
            if (outputHero != null)
                return outputHero;

            /**On cherche un héros de niveau inférieur**/
            outputHero = getTheFirstHeroFromThisLevel(desiredLevel - 1);
            if (outputHero != null)
                return outputHero;

        }

        /**Si on ne trouve aucun héros, on return null.**/
        return null;
    }

    public BaseHero getTheFirstHeroFromThisLevel(int level) {

        /**On retourne le premier héros qui est dans la catégorie désirée.**/
        switch (level) {
            case (1): {
                if (level1HeroList.size() != 0)
                    return level1HeroList.get(0);
                break;
            }
            case (2): {
                if (level2HeroList.size() != 0)
                    return level2HeroList.get(0);
                break;
            }
            case (3): {
                if (level3HeroList.size() != 0)
                    return level3HeroList.get(0);
                break;
            }
            case (4): {
                if (level4HeroList.size() != 0)
                    return level4HeroList.get(0);
                break;
            }
            case (5): {
                if (level5HeroList.size() != 0)
                    return level5HeroList.get(0);
                break;
            }

            /**Si un héros du niveau demandé n'existe pas, on return null.**/
            default: {
                return null;
            }
        }

        return null;
    }

    public String generateHerosReport() {

        String outputString = "";

        /**Rapport des héros de Niveau 1**/
        for (BaseHero hero : level1HeroList)
        outputString += "-" + hero.getName() + ": level=1, HP=" + String.format("%,.1f", hero.getHP()) + "\n";

        /**Rapport des héros de Niveau 2**/
        for (BaseHero hero : level2HeroList)
        outputString += "-" + hero.getName() + ": level=2, HP=" + String.format("%,.1f", hero.getHP()) + "\n";

        /**Rapport des héros de Niveau 3**/
        for (BaseHero hero : level3HeroList)
        outputString += "-" + hero.getName() + ": level=3, HP=" + String.format("%,.1f", hero.getHP()) + "\n";

        /**Rapport des héros de Niveau 4**/
        for (BaseHero hero : level4HeroList)
        outputString += "-" + hero.getName() + ": level=4, HP=" + String.format("%,.1f", hero.getHP()) + "\n";

        /**Rapport des héros de Niveau 5**/
        for (BaseHero hero : level5HeroList)
        outputString += "-" + hero.getName() + ": level=5, HP=" + String.format("%,.1f", hero.getHP()) + "\n";

        return outputString;

    }

}
