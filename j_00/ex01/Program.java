package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean simple = false;
        int i = 1;
        int check;

        if (!scanner.hasNextInt()) {
            System.err.println("IllegalArgument");
            scanner.close();
            System.exit(-1);
        }
        int number = scanner.nextInt();
        if (number <= 1) {
            System.err.println("IllegalArgument");
            scanner.close();
            System.exit(-1);
        }
        for (check = 2; check * check <= number && number % check != 0; i++) {
            check++;
        }
        if (check * check > number) {
            simple = true;
        }
        System.out.println(simple + " " + i);
        scanner.close();
    }
}
