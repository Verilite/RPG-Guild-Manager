public class GuildCommand {

    /**Variables et Getters**/
    private String name;
    public String getName() {
        return name;
    }

    private final String[] args;
    public String getStringArgByID(int id) {
        return args[id];
    }
    public Double getDoubleArgByID(int id) {
        return Double.parseDouble(args[id]);
    }
    public int getIntArgByID(int id) {
        return Integer.parseInt(args[id]);
    }


    /**Constructeurs, où on sépare la commande et les values des arguments de la commande**/
    public GuildCommand(String rawCommand){
        /**On load la commande comme String[]**/
        String[] commandSplit = rawCommand.split(":");
        name = commandSplit[0];

        /**On load les arguments comme String[]**/
        args = commandSplit[1].split(",");
        
    }

}