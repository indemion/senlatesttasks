package hangman;

import consoleconfig.Config;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, Config.getInstance().getEncoding().toString()));
        Game game = new Game();
        game.start();
    }
}
