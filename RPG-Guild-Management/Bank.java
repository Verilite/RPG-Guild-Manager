public class Bank {

    /**Propriétés des fonds: variables, getters, setters**/
    public Double funds;
    public Double getFunds(){
        return funds;
    }
    public void removeFunds(Double quantity){
        funds -= quantity;
    }
    public void addFunds(Double quantity){
        funds += quantity;
    }

    /**Propriétés des armures: variables, getters, setters**/
    public int armor;
    public int getArmor(){
        return armor;
    }
    public void removeArmor(int quantity){
        armor -= quantity;
    }
    public void addArmor(int quantity){
        armor += quantity;
    }


    /**Constructeurs**/
    public Bank (double funds, int armor){
        this.funds = funds;
        this.armor = armor;
    }

}
