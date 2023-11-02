package Heros;

public class BaseHero {

    /**Propriétés d'un héros de base: variables, getters**/

    /**Nom**/
    private String name;
    public String getName() {
        return name;
    }

    /**Catégorie/niveau**/
    private int category;
    public int getCategory() {
        return category;
    }

    /**HP, avec un setter**/
    private Double hp;
    public Double getHP() {
        return hp;
    }
    public void removeHP(Double quantity) {
        this.hp -= quantity;
    }

    /**HP maximum**/
    private Double maxHP;
    public Double getMaxHP() {
        return maxHP;
    }
    

    /**Constructors**/
    public BaseHero(String name, int category, Double hp, Double maxHP) {

        this.name = name;
        this.category = category;
        this.hp = hp;
        this.maxHP = maxHP;

    }

}
