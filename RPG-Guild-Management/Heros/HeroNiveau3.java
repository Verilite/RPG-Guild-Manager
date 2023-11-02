package Heros;

public class HeroNiveau3 extends BaseHero {

    /**Propriétés, getters, setters, variables**/

    private int level;
    public int getLevel(){
        return level;
    }

    
    public HeroNiveau3(String name, int category, Double hp, Double maxHP){

        super(name, category, hp, maxHP);

        this.level = 3;

    }

}
