package sfr.college.PodRacing;

/**
 * @author Sami
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //creating game instance
        // System.out.println("Include sound assets?");
        //  Scanner x = new Scanner(System.in);
        // boolean hasSound = !(x.next().equals("no"));
        boolean hasSound = true;
        Game game = new Game("sfr - PodRacing", hasSound);
        game.start();
    }

}
