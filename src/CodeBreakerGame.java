import java.util.Random;
import java.util.Scanner;

public class CodeBreakerGame {
    private static final int CODE_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 20;

    public static void main(String[] args) {
        int[] code = generateCode();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Игра началась! У вас есть " + MAX_ATTEMPTS + " попыток, чтобы угадать 4-значный код.");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("Попытка #" + attempt + ": Введите 4 числа через пробел: ");
            int[] guess = new int[CODE_LENGTH];

            for (int i = 0; i < CODE_LENGTH; i++) {
                if (scanner.hasNextInt()) {
                    guess[i] = scanner.nextInt();
                } else {
                    System.out.println("Некорректный ввод. Попытайтесь снова.");
                    scanner.next();
                    i--;
                }
            }

            int matches = countMatches(code, guess);
            if (matches == CODE_LENGTH) {
                System.out.println("Поздравляем! Вы разгадали код за " + attempt + " попыток.");
                return;
            } else {
                System.out.println("Количество совпадений: " + matches);
            }
        }

        System.out.println("К сожалению, вы исчерпали все попытки. Загаданный код был: " + codeToString(code));
    }

    private static int[] generateCode() {
        Random random = new Random();
        int[] code = new int[CODE_LENGTH];

        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = random.nextInt(10);
        }

        return code;
    }

    private static int countMatches(int[] code, int[] guess) {
        int matches = 0;
        boolean[] checkedCode = new boolean[CODE_LENGTH];

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess[i] == code[i]) {
                matches++;
                checkedCode[i] = true;
            }
        }

        return matches;
    }

    private static String codeToString(int[] code) {
        StringBuilder sb = new StringBuilder();
        for (int num : code) {
            sb.append(num);
        }
        return sb.toString();
    }
}