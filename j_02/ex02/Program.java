package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
    private static Command command;

    public static void main(String[] args) {

        if (args.length != 1 || !args[0].startsWith("--current-folder")) {
            System.err.println("usage:\njava Program --current-folder=[PATH]");
            System.exit(-1);
        }

        String split[] = args[0].split("=");
        command = new Command();

        if (split.length != 2 || !command.currentPath(split[1])) {
            System.out.println("usage:\njava Program --current-folder=[PATH]");
            System.exit(-1);
        }
        command.cd(split[1]);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                applyCommand(br.readLine().trim());
            }
        } catch (IOException e) {
            System.out.println("can't read from System.in");
            System.exit(-1);
        }
    }

    private static void applyCommand(String line) {
        String text[] = line.split(" +");

        if (text[0].equals("ls")) {
            ls(text);
        } else if (text[0].equals("cd")) {
            cd(text);
        } else if (text[0].equals("mv")) {
            exit(text);
        } else if (text[0].equals("exit")) {
            System.exit(0);
        } else {
            System.err.println("unknown command");
        }
    }

    private static void ls(String[] text) {
        if (text.length == 1) {
            command.ls();
        } else if (text.length == 2) {
            command.ls(text[1]);
        } else {
            System.err.println("to many arguments");
        }
    }

    private static void exit(String[] text) {
        if (text.length != 3) {
            System.err.println("need 2 arguments");
        } else {

            command.mv(text[1], text[2]);
        }
    }

    private static void cd(String[] text) {
        if (text.length == 1) {
            return;
        } else if (text.length == 2) {
            command.cd(text[1]);
        } else {
            System.err.println("to many arguments");
        }
    }
}