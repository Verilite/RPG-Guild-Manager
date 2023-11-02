package Heros;

public class HeroNiveau1 extends BaseHero {
    /**Propriétés, getters, setters, variables**/
    private int level;
    public int getLevel(){
        return level;
    }
    

    public HeroNiveau1(String name, int category, Double hp, Double maxHP){

        super(name, category, hp, maxHP);

        this.level = 1;

    }

}
