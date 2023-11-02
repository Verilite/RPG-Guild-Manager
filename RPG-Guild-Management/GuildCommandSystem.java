import Heros.BaseHero;

public class GuildCommandSystem {

    /**Variables du GuildCommandSystem**/
    private GuildCommand[] guildCommands;
    private String errorsText = ""; /**Cas de base pour les erreurs**/
    private Guild guild;

    /**Fonctions du GuildCommandSystem**/
    public GuildCommandSystem(String[] inputArgs) {

        /**On load les commands donnés**/
        guildCommands = new GuildCommand[inputArgs.length];
        for (int i = 0; i < inputArgs.length; i++) {
            guildCommands[i] = new GuildCommand(inputArgs[i]);
        }

    }

    public void executeAllCommands(Guild myGuild) {

        //Initialisation
        this.errorsText = "";

        /**On exécute chacun des guildCommands dans le tableau donné par le user.**/
        for (int i = 0; i < guildCommands.length; i++) {
            GuildCommand currentCommand = guildCommands[i];

            /**On a un cas pour chaque type de command.**/
            switch (currentCommand.getName()) {
                case "guild": {

                    /**On récupère les arguments selon les paramètres**/
                    Double fund = currentCommand.getDoubleArgByID(0);
                    int armor = currentCommand.getIntArgByID(1);

                    /**On vérifie l'intégrité des Arguments**/
                    if (fund < 0) {
                        gameError(currentCommand.getName(), "Les fonds de la guilde ne peuvent pas être négatifs");
                        break;
                    }
                    if (armor < 0) {
                        gameError(currentCommand.getName(), "La valeur de l'armure ne peut pas être négative");
                        break;
                    }

                    /**On initialise la guilde selon les paramètres**/
                    myGuild = new Guild(fund, armor);

                    break;
                }

                case "buy-hero": {

                    /**On récupére les Arguments**/
                    String heroName = currentCommand.getStringArgByID(0);
                    int heroCategory = currentCommand.getIntArgByID(1);
                    Double heroPrice = currentCommand.getDoubleArgByID(2);
                    int heroArmorPrice = currentCommand.getIntArgByID(3);
                    Double heroHP = currentCommand.getDoubleArgByID(4);

                    /**On vérifie l'intégrité des Arguments**/
                    if (myGuild.bank.getFunds() < heroPrice) {
                        gameError(currentCommand.getName(), "La guilde n'a pas assez de fonds pour acheter cet Hero");
                        break;
                    }
                    if (myGuild.bank.getFunds() < heroPrice) {
                        gameError(currentCommand.getName(), "La guilde n'a pas assez d'armure pour acheter cet Hero");
                        break;
                    }

                    /**On achète l'Hero pour la guilde**/
                    myGuild.buyHero(heroName,
                            heroCategory,
                            heroPrice,
                            heroArmorPrice,
                            heroHP);

                    break;
                }

                case "buy-armure": {

                    /**Arguments**/
                    int armorCount = currentCommand.getIntArgByID(0);
                    Double armorPrice = currentCommand.getDoubleArgByID(1);

                    /**Intégrité des arguments**/
                    if (armorCount < 0) {
                        gameError(currentCommand.getName(), "La quantité d'armure à acheter ne peut être négative");
                        break;
                    }
                    if (armorPrice < 0) {
                        gameError(currentCommand.getName(), "Le prix d'une armure ne peut pas être négatif");
                        break;
                    }
                    if (myGuild.bank.getFunds() < (armorPrice * armorPrice)) {
                        gameError(currentCommand.getName(),
                                "La guilde n'a pas assez de fonds pour acheter ces Armures");
                        break;
                    }

                    /**On achète l'armure pour la guilde.**/
                    myGuild.buyArmor(armorCount,
                            armorPrice);

                    break;
                }

                case "do-quest": {

                    /**Arguments**/
                    int questCategory = currentCommand.getIntArgByID(0);
                    Double questHPCost = currentCommand.getDoubleArgByID(1);
                    int questMoneyReward = currentCommand.getIntArgByID(2);
                    int questArmorReward = currentCommand.getIntArgByID(3);

                    /**Intégrité des arguments**/
                    if ((questCategory < 0) || (questCategory > 4)) {
                        gameError(currentCommand.getName(), "La Catégorie de la quête doit être entre 0 et 4");
                        break;
                    }
                    if (questHPCost < 0) {
                        gameError(currentCommand.getName(), "Le cout en Point de vie ne peut pas être négatif");
                        break;
                    }
                    if (questMoneyReward < 0) {
                        gameError(currentCommand.getName(), "Le récompense en Argent ne peut pas être négative");
                        break;
                    }
                    if (questArmorReward < 0) {
                        gameError(currentCommand.getName(), "Le récompense en Armure ne peut pas être négative");
                        break;
                    }
                    BaseHero hero = myGuild.getNearestLevelHero(questCategory + 1);
                    if (hero == null) {
                        gameError(currentCommand.getName(),
                                "Il n'y a aucun héros dans la guilde qui peut prendre ce quête.");
                        break;
                    }

                    /**On fait la quête**/
                    Quete myQuest = new Quete(questCategory, questHPCost, questMoneyReward, questArmorReward);
                    int heroLevel = myGuild.getHeroLevelByName(hero.getName());
                    myQuest.doQuest(myGuild, hero, heroLevel);

                    break;
                }

                case "train-hero": {

                    /**Arguments**/
                    String heroName = currentCommand.getStringArgByID(0);

                    /**Intégrité des arguments**/
                    BaseHero hero = myGuild.getHeroByName(heroName);
                    int heroLevel = myGuild.getHeroLevelByName(heroName);

                    if (hero == null) {
                        gameError(currentCommand.getName(),
                                "Il n'y a aucun héros appelé \"" + heroName + "\" dans la guilde.");
                        break;
                    }
                    if (myGuild.getHeroLevelByName(heroName) == 5) {
                        gameError(currentCommand.getName(),
                                "Le héros est déja au niveau maximale (5), il ne peut plus être entrainé.");
                        break;
                    }
                    if (myGuild.bank.getFunds() < 20 * Math.log(heroLevel + 10)) {
                        gameError(currentCommand.getName(),
                                "La guilde n'a pas assez de Fonds pour entrainer cet Hero");
                        break;
                    }
                    if (myGuild.bank.getArmor() < Math.ceil(Math.log(heroLevel + 10))) {
                        gameError(currentCommand.getName(),
                                "La guilde n'a pas assez d'Armures pour entrainer cet Hero");
                        break;
                    }

                    /**On entraine le héros**/
                    myGuild.trainHero(hero, heroLevel);

                    break;
                }

                /**En cas de commande non géré par le GuildCommandSystem**/
                default: {
                    gameError(currentCommand.getName(), "Commande non reconnue ..");
                    break;
                }
            }

            /**Fin de l'initialisation**/
            this.guild = myGuild;

        }
    }

    /**Dans le cas où la commande génère un erreur, on crée le error log**/
    private void gameError(String commandName, String errorMessage) {
        errorsText += "-" + errorMessage + "  -  [on command : " + commandName + "]\n";
    }

    public String generateGameReport() {

        /** Je retourne un rapport sur l'état du jeu apres l'éxecution des commandes**/
        String outputText = "Guild Bank account: " + String.format("%,.1f", guild.bank.getFunds()) + " gold & "
                + guild.bank.getArmor()
                + " armours\n" +
                "Heroes:\n" + guild.generateHerosReport();

        /**Rapport des erreurs s'il y a eu lieu**/
        if (!errorsText.equals(""))
            outputText += "Erreurs:\n" + errorsText;

        return outputText;
    }

}
