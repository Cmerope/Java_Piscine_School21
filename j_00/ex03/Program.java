package ex03;

import java.util.Scanner;

public class Program {
    private static long resGrade = 0;
    private static long minGrade = 0;
    private static long maxWeeks = 1;

    private static int exit() {
        System.err.println("\nIllegalArgument");
        return -1;
    }

    private static long rank() {
        long i = 0;
        long res = 1;
        while (i < maxWeeks - 1) {
            res *= 10;
            i++;
        }
        return res;
    }

    private static void paint(){
        int i = 0;
        while (maxWeeks > 0) {
            System.out.print("Week " + (1 + i) +(((1 + i) < 10) ? "  " : " "));
            long j = resGrade % 10;
            while (j > 0) {
                System.out.printf("=");
                j--;
            }
            System.out.println(">");
            resGrade = resGrade / 10;
            maxWeeks--;
            i++;
        }
    }

    private  static void number(Scanner scanner){
        minGrade = 9;
        long grade = 0;
        for (int i = 0; i < 5; i++) {
            if (!scanner.hasNextInt()) {
                scanner.close();
                System.exit(exit());
            }
            grade = scanner.nextInt();
            if (grade >= 1 && grade <= 9){
                if (grade < minGrade)
                    minGrade = grade;
                continue;
            }
            scanner.close();
            System.exit(exit());
        }
        scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("42") ) {
            System.out.println(maxWeeks);

            if (!str.equals("Week " + maxWeeks)) {
                scanner.close();
                System.exit(exit());
            }
            number(scanner);
            if (resGrade == 0)
                resGrade += minGrade;
            else
                resGrade += minGrade * rank();
            if (maxWeeks == 18) {
                break;
            }
            maxWeeks++;
            str = scanner.nextLine();
            if (str.equals("42"))
                maxWeeks--;
        }
        paint();
        scanner.close();
    }
}
