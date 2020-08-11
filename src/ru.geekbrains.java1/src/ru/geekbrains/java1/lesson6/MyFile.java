package ru.geekbrains.java1.lesson6;

import java.io.*;
import java.util.Scanner;

public class MyFile {

    private final String fileName;

    MyFile(String fileName) {
        this.fileName = fileName;
    }

    public void createFromString(String data) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream(fileName));
        ps.print(data);
        ps.flush();
        ps.close();
    }

    public void appendFromFile(String fileName) throws FileNotFoundException {
        copyFromFile(fileName,true);
    }
    public void copyFromFile(String fileName, boolean append) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        PrintStream ps = new PrintStream(new FileOutputStream(this.fileName, append));

        while (scanner.hasNext()) {
            ps.println(scanner.nextLine());
        }
    }

    public long findWord(String word) throws FileNotFoundException {
        long n = 0;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        while (scanner.hasNextLine()) {
            n++;
            String str = scanner.nextLine();
            if (str.contains(word)) {
                return n;
            }
        }
        return 0;
    }

    public String getDir() {
        return new File(this.fileName).getAbsoluteFile().getParentFile().getAbsolutePath();
    }

    public static String findWordInDir(String dirName, String word) throws FileNotFoundException {
        String[] fileNames = new File(dirName).list();
        StringBuilder result = new StringBuilder();
        if (fileNames != null)
            for (int i = 0; i < fileNames.length; i++) {
                boolean fd = new File(dirName,fileNames[i]).isDirectory();
                if (!fd) {
                    if (new MyFile(fileNames[i]).findWord(word) > 0) {
                        result.append(fileNames[i]);
                        result.append('\n');
                    }
                }
            }
        return result.toString();
    }

    public String getName() {
        return this.fileName;
    }
}
