package nz.ac.vuw.ecs.swen225.gp22.persistency;


public class Persistency {

    public static String loadGame(String file) {
        System.out.println("Persistency load game called on: " + file);
        return "game from file returned";
    }
    public static void saveGameSave(String string, String pathString) {
        System.out.println("gameSaved on values: " + string + ", " + pathString);
    }

}
