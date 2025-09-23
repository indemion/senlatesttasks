package passwordgen;

import consoleconfig.Config;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, Config.getInstance().getEncoding().toString()));
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-.$*()";
        final int MIN_PASS_LENGTH = 8;
        final int MAX_PASS_LENGTH = 12;
        Random random = new Random();
        String input;
        int passwordLength;
        try(Scanner console = new Scanner(System.in, Config.getInstance().getEncoding().toString())) {
            while (true) {
                System.out.print(String.format("Введите размер пароля который вам неообходим в диапазоне %d-%d символов: ", MIN_PASS_LENGTH, MAX_PASS_LENGTH));
                input = console.nextLine();
                try {
                    passwordLength = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Прозошла ошибка, вы ввели не целое число, попробуйте снова.");
                    continue;
                }
                if (passwordLength < MIN_PASS_LENGTH || passwordLength > MAX_PASS_LENGTH) {
                    System.out.println("Произошла ошибка, введённая длина пароля не входит в необходимый диапазон, повторите ввод снова.");
                    continue;
                }

                StringBuilder password = new StringBuilder("");
                for (int i = 0; i < passwordLength; i++) {
                    password.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
                }
                System.out.println(String.format("Ваш пароль: %s", password));

                System.out.println("Хотите сгенерировать еще пароль? (Y/n):");
                input = console.nextLine();
                if (!"y".equalsIgnoreCase(input)) {
                    break;
                }
            }
        }
    }
}
