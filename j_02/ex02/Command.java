package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Command {
    private String path;
    private File file;

    public Command() {
        this.path = "";
    }

    private File[] listFiles(File file) {
        File files[] = file.listFiles();
        if (files == null) {
            return new File[0];
        }
        return files;
    }

    private long getSize(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long size = 0;
        for (File files : listFiles(file)) {
            size += getSize(files);
        }
        return size;
    }

    public void ls() {
        ls(path);
    }

    public void ls(String path) {
        File file = new File(getPath(path));
        if (!file.exists()) {
            return;
        }
        for (File f : listFiles(file)) {
            System.out.println(f.getName() + " " + getSize(f) / 1024 + "KB");
        }
    }

    public void cd(String next) {
        if (next.isEmpty()) {
            return;
        }

        File file = new File(getPath(next));
        if (file.exists() && file.isDirectory()) {
            path = file.toPath().normalize().toString();
            this.file = file;
            System.out.println(path);
        } else {
            System.err.println("no such directory");
        }
    }

    public void mv(String from, String to) {
        if (from.equals(to)) {
            return;
        }

        from = getPath(from);
        to = getPath(to);

        File file = new File(from);
        File fileTo = new File(to);
        if (!file.exists()) {
            System.err.println("no such file" + from);
            return;
        }
        if (fileTo.exists() && fileTo.isDirectory()) {
            fileTo = new File(fileTo.getAbsolutePath() + "/" + file.getName());
        }
        try {
            Files.move(file.toPath(), fileTo.toPath());
        } catch (IOException e) {
            System.err.println("no such directory");
        }
    }

    public boolean currentPath(String path) {
        File file = new File(getPath(path));
        if (file.exists() && file.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    private String getPath(String name) {
        if (name.startsWith("/")) {
            return name;
        } else if (name.startsWith("./")) {
            return path + name.substring(1);
        }
        return path + "/" + name;
    }
}
