package hangman;

import consoleconfig.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private final int START_LIVES = 6;
    private int remainingLives = START_LIVES;
    private String word;
    private Canvas canvas;

    public Game() {
        initWord();
        initCanvasWithGallows();
    }

    public void start() {
        List<Point> stickman = new ArrayList<>();
        stickman.add(new Point(7, 2, 'O'));
        stickman.add(new Point(7, 3, '|'));
        stickman.add(new Point(6, 3, '/'));
        stickman.add(new Point(8, 3, '\\'));
        stickman.add(new Point(6, 4, '/'));
        stickman.add(new Point(8, 4, '\\'));

        Map<Integer, String> guessedLetters = new HashMap<>();
        try(Scanner console = new Scanner(System.in, Config.getInstance().getEncoding().toString())) {
            String letter;
            System.out.println("Добро пожаловать в игру виселица!");
            StringBuilder sb = new StringBuilder();
            while (remainingLives > 0 && guessedLetters.size() != word.length()) {
                canvas.draw();
                sb.append(String.format("Осталось жизней: %d\n", remainingLives));
                sb.append(String.format("Ваше слово: %s\n", buildOutputWord(word.length(), guessedLetters)));
                sb.append("Введите букву и нажмите enter: ");
                System.out.print(sb);
                sb.setLength(0);
                letter = console.nextLine();

                int index = word.indexOf(letter);
                if (index == -1) {
                    remainingLives--;
                }
                while (index != -1) {
                    guessedLetters.put(index, letter);
                    index = word.indexOf(letter, index + 1);
                }
                canvas.addPoints(stickman.stream().limit(START_LIVES - remainingLives).collect(Collectors.toList()));
            }
        } 

        if (remainingLives != 0) {
            System.out.println(String.format("Ваше слово: %s", buildOutputWord(word.length(), guessedLetters)));
            System.out.println("Вы выиграли!");
        } else {
            canvas.draw();
            System.out.println("Вы проиграли!");
        }
    }

    private static String buildOutputWord(int wordLength, Map<Integer, String> guessedLetters) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            sb.append("|");
            if (guessedLetters.containsKey(i)) {
                sb.append(guessedLetters.get(i));
            } else {
                sb.append("_");
            }
        }
        sb.append("|");

        return sb.toString();
    }

    private void initWord() {
        String[] words = {"человек", "бобёр", "программист", "лётчик", "молния"};
        Random random = new Random();

        word = words[random.nextInt(words.length)];
    }

    private void initCanvasWithGallows() {
        canvas = new Canvas(9, 6);

        List<Point> gallows = new ArrayList<>();
        gallows.add(new Point(1, 0, '#'));
        gallows.add(new Point(1, 1, '#'));
        gallows.add(new Point(1, 2, '#'));
        gallows.add(new Point(1, 3, '#'));
        gallows.add(new Point(1, 4, '#'));
        gallows.add(new Point(0, 5, '#'));
        gallows.add(new Point(1, 5, '#'));
        gallows.add(new Point(2, 5, '#'));
        gallows.add(new Point(2, 0, '-'));
        gallows.add(new Point(3, 0, '-'));
        gallows.add(new Point(4, 0, '-'));
        gallows.add(new Point(5, 0, '-'));
        gallows.add(new Point(6, 0, '-'));
        gallows.add(new Point(7, 0, '-'));
        gallows.add(new Point(7, 1, '|'));
        canvas.addPoints(gallows);
    }
}
