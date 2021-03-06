package jogorpg.world_of_zuul;

import Itens.Anel;
import Personagens.Vilao;
import Personagens.Heroi;
import Personagens.Chefe;
import Itens.Bebida;
import Itens.Cajado;
import Itens.Camisa;
import Itens.Churros;
import Itens.Item;
import Itens.Moletom;
import Personagens.NPC;
import java.util.HashMap;
import java.util.Map;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Heroi heroi = new Heroi("Anonimo", 2500, 159);
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room alfredo, pisca, chrisao, churros, canidia, passarela, posto, beijinho, 
                viaduto, praca, avenida, gauchao, porta;
      
        // create the rooms
        alfredo = new Room("na entrada da escola Alfredo Ferreira Rodrigues");
        pisca = new Room("no campo do piscabol");
        chrisao = new Room("na casa do Chrisão");
        churros = new Room("no churros do Maicon");
        canidia = new Room("no campo do Canidia");
        passarela = new Room("na passarela");   // vazia / passagem
        posto = new Room("no posto Sim");
        beijinho = new Room("na casa do beijinho");
        viaduto = new Room("Embaixo do viaduto");   // passagem
        praca = new Room("na praça Coronel Marcelino");
        avenida = new Room("na Avenida da Paz");
        gauchao = new Room("no Gauchão Acessórios");
        
        // initialise room exits
        alfredo.setExit("praca", praca);
        alfredo.setExit("viaduto", viaduto);
        alfredo.setExit("pisca", pisca);

        pisca.setExit("passarela", passarela);
        pisca.setExit("chrisao", chrisao);
        pisca.setExit("beijinho", beijinho);
        pisca.setExit("alfredo", alfredo);
        pisca.setExit("canidia", canidia);

        chrisao.setExit("passarela", passarela);
        chrisao.setExit("pisca", pisca);
        chrisao.setExit("beijinho", beijinho);
        
        churros.setExit("gauchao", gauchao);
        churros.setExit("viaduto", viaduto);
        churros.setExit("avenida", avenida);
        churros.setExit("posto", posto);
        
        beijinho.setExit("chrisao", chrisao);
        beijinho.setExit("passarela", passarela);
        beijinho.setExit("pisca", pisca);
        
        passarela.setExit("beijinho", beijinho);
        passarela.setExit("avenida", avenida);
        passarela.setExit("gauchao", gauchao);
        passarela.setExit("chrisao", chrisao);
        
        gauchao.setExit("churros", churros);
        gauchao.setExit("avenida", avenida);
        gauchao.setExit("passarela", passarela);
        
        viaduto.setExit("churros", churros);
        viaduto.setExit("posto", posto);
        viaduto.setExit("alfredo", alfredo);
        
        avenida.setExit("gauchao", gauchao);
        avenida.setExit("churros", churros);
        
        canidia.setExit("praca", praca);
        canidia.setExit("pisca", pisca);
        
        praca.setExit("alfredo", alfredo);
        praca.setExit("canidia", canidia);
        
        posto.setExit("churros", churros);
        posto.setExit("viaduto", viaduto);
        
        
        // add characters in each room
        posto.setCharacter("Aldenei", new Vilao("Aldenei", 1500, 450, 190));
        posto.setCharacter("Frentista", new Vilao("Frentista", 1005, 3000, 100));
        HashMap<String, Item> invPosto = new HashMap<>();
        invPosto.put("Japona", new Moletom("Japona", 17, 66, 33));
        posto.setCharacter("Caqui", new Chefe("Caqui", 2366, 102, 230, invPosto));
        
        HashMap<String, Item> invAlfredo = new HashMap<>();
        invAlfredo.put("Cacetete", new Cajado("Cacetete", 4, 19, 120, false));
        alfredo.setCharacter("Hamilton", new Chefe("Hamilton", 1250, 3000, 100, invAlfredo));
        
        churros.setNPC("Maiquinho", new NPC("Maiquinho", "E ae zé, vais querer um churros?", new Churros("Churros", 1), 25));
        
        beijinho.setNPC("Beijinho", new NPC("Beijijnho", "Pena do bolso", new Bebida("coca cola", 3, 40), 40));
        beijinho.setCharacter("Pena", new Vilao("Pena", 850, 10, 176));
        HashMap<String, Item> invBeijinho = new HashMap<>();
        invBeijinho.put("Camisa_polo", new Camisa("Camisa_polo", 2, 29));
        beijinho.setCharacter("Seu_armando", new Chefe("Seu_armando", 2000, 0, 111, invBeijinho));
        
        pisca.setCharacter("Marcimam", new Vilao("Marcimam", 1402, 561, 50));
        pisca.setCharacter("Piscabol", new Vilao("Piscabol", 900, 1000, 250));
        
        praca.setCharacter("Mangulinho", new Vilao("Mangulinho", 1900, 50, 168));
        HashMap<String, Item> invPraca = new HashMap<>();
        invPraca.put("Camisa_polo", new Camisa("Camisa_time", 4, 31));
        praca.setCharacter("Seu_armando", new Chefe("Seu_armando", 2000, 0, 111, invPraca));
        
        HashMap<String, Item> invChrisao = new HashMap<>();
        invChrisao.put("Camisa_polo", new Cajado("Canivete", 14, 68, 10, true));
        praca.setCharacter("Chris", new Chefe("Chris", 3500, 1500, 90, invChrisao));
        
        gauchao.setCharacter("Mecanico1", new Vilao("Mecanico1", 500, 160, 200));
        gauchao.setCharacter("Mecanico2", new Vilao("Mecanico2", 980, 10, 109));
        // add items in each room
        alfredo.addItem(new Bebida("canha", 2, 50));
        
        pisca.addItem(new Anel("Anel_dourado", 3, false, 1.1));
        
        canidia.addItem(new Camisa("Colete_society", 1, 14));
        canidia.addItem(new Cajado("Trave", 16, 170 , 22, true));
        
        avenida.addItem(new Churros("Churros_nutella", 2));
        avenida.addItem(new Bebida("Agua", 2, 160));
        
        chrisao.addItem(new Moletom("Moletom_computacao", 9, 40, 34));
        
        // start game
        currentRoom = alfredo;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        System.out.println("\nYour energy: " + heroi.getEnergy() + " / " + heroi.getMaxEnergy());

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            if(heroi.getCurrentEnergy() != 0){
                Command command = parser.getCommand();
                finished = processCommand(command);
                System.out.println("\nYour energy: " + heroi.getEnergy() + " / " + heroi.getMaxEnergy());
            } else{
                finished = true;
            }
        }
        System.out.println("Até a próxima!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-Vindo ao Povo Novo, " + heroi.getName() + "!");
        System.out.println("Derrote todos os inimigos que puder.");
        System.out.println("Digite '" + CommandWord.HELP + "' para ajuda.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("Comando inválido...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }else if (commandWord == CommandWord.ATTACK) {
            attack(command);
        }else if (commandWord == CommandWord.PICK) {
            pick(command);
        }else if (commandWord == CommandWord.DROP) {
            drop(command);
        }else if (commandWord == CommandWord.TALK) {
            talk(command);
        }else if (commandWord == CommandWord.INVENTORY) {
            inventory(command);
        }else if(commandWord == CommandWord.BUY) {
            buy(command);
        }else if(commandWord == CommandWord.USE) {
            use(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Você acordou nas margens da br 392 próximo a escola Alfredo Ferreira Rodrigues");
        System.out.println("Você não se lembra de como foi parar ai e nem conhece o local.");
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
        System.out.println("Comandos:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Onde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Comando inválido?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    public void use(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Comando inválido?");
            return ;
        }
        String what = command.getSecondWord();
        Item i = heroi.getInventory().get(what);
        if(i != null && (i instanceof Bebida || i instanceof Churros)){
            if(i instanceof Bebida){
                heroi.setMaxEnergy(heroi.getMaxEnergy()+((Bebida) i).getMaxLife());
                heroi.remover(what);
            } else if(i instanceof Churros){
                if(heroi.getCurrentEnergy() < heroi.getMaxEnergy()){
                    heroi.setCurrentEnergy((int) (heroi.getCurrentEnergy()*((Churros) i).getExtraLife()));
                    heroi.remover(what);
                } else{
                    System.out.println("Vida cheia.");
                }
            }
        }
    }
    private void attack(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Atacar quem?");
            return;
        }
        
        String who = command.getSecondWord();
        Vilao vilao = (Vilao) currentRoom.getCharacters().get(who);
        if(vilao != null){
            heroi.fight(vilao);
            if(vilao.getEnergy() == 0){
                if(vilao instanceof Chefe){
                    for(Item x : ((Chefe) vilao).dropItens().values()){
                        currentRoom.addItem(x);
                    }
                }
                if(vilao.dropCoins().getCoins() != 0) currentRoom.addItem(vilao.dropCoins());
                currentRoom.removeCharacter(vilao.getName());
                System.out.println("You win!");
            } else{
                System.out.println("You lose...\nGame Over.");
            }
        }
    }
    
    private void talk(Command command){
       if(!command.hasSecondWord()) {
            System.out.println("Falar com quem?");
            return;
        }
        String who = command.getSecondWord();
        NPC npc = currentRoom.getNPCS().get(who);
        if(npc != null){
            System.out.println(npc.getName() + " diz: " + npc.getDialog());
        }
    }
    
    private void buy(Command command){
       if(!command.hasSecondWord()) {
            System.out.println("Comprar de quem?");
            return;
        }
        String who = command.getSecondWord();
        NPC npc = currentRoom.getNPCS().get(who);
        if(npc != null){
            if(heroi.pay(npc.getValue())) {
                if(heroi.inserir(npc.getItem())) {
                   System.out.println("Você comprou " + npc.itemName() + " 1x");
                   return ;
                }
                heroi.colectCoins(npc.getValue());
            } else{
                System.out.println("Dinheiro insuficiente.");
            }
        }
    }
    private void inventory(Command command){
        System.out.println("\n" + heroi.getWallet().getCoins() + " coins | Weight: " + heroi.pesoAtual() + "/" + heroi.getWeightLimit() + "\n");
        for(Item x : heroi.getInventory().values()){
            System.out.print("[Name: " + x.getName() + "/D: " + x.getDurability() +"] ");
        }
    }
    private void pick(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Pegar o que?");
            return;
        }
        String what = command.getSecondWord();
        if(currentRoom.getItens().get(what) != null)
            heroi.inserir(currentRoom.removeItem(what));
    }
    
    private void drop(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Soltar o que?");
            return;
        }
        String what = command.getSecondWord();
        if(heroi.getInventory().get(what) != null){
            currentRoom.addItem(heroi.remover(what));
        }
    }
}
