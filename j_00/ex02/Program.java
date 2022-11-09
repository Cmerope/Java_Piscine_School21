package ex02;

import java.util.Scanner;

public class Program {
    private static int count = 0;

    private static int exit() {
        System.err.println("IllegalArgument");
        System.out.println("Count of coffee - request - " + count);
        return -1;
    }

    private static int sum(int number) {
        int num = 0;
        while (number > 0) {
            num += number % 10;
            number = number/10;
        }
        return  num;
    }

    private static void simple(int number) {
        int check;

        number = sum(number);
        for (check = 2; check * check <= number && number % check != 0; check++) {
        }
        if (check * check > number) {
            count++;
        }
    }

    public static void main (String[]args){
        Scanner scanner = new Scanner(System.in);
        boolean process = true;

        while (process) {
            if (!scanner.hasNextInt()) {
                scanner.close();
                System.exit(exit());
            }
            int number = scanner.nextInt();
            if (number <= 1) {
                scanner.close();
                System.exit(exit());
            }
            else if (number == 42)
                process = false;
            else {
                simple(number);
                }
            }
            System.out.println("Count of coffee - request - " + count);
            scanner.close();
        }
}
