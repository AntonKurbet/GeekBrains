package ru.geekbrains.java1.lesson6;

import java.io.*;
import java.util.Scanner;

public class MyFile {

    private final String fileName;
    private final String directory;

    MyFile(String fileName) {
        this.fileName = fileName;
        this.directory = new File(fileName).getAbsoluteFile().getParentFile().getAbsolutePath();
        //this.directory = new File(".").getCanonicalPath();
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
        ps.flush();
        ps.close();
    }

    public long findWord(String word) throws FileNotFoundException {
        long n = 0;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        while (scanner.hasNext()) {
            n++;
            // тут можно написать более сложную логику поиска слова и лучше сделать на regexp
            if (scanner.next().contains(word)) {
                return n;
            }
        }
        return 0;
    }

    public static String findWordInDir(String dirName, String word) throws FileNotFoundException {
        String[] fileNames = new File(dirName).list();
        StringBuilder result = new StringBuilder();
        if (fileNames != null)
            for (int i = 0; i < fileNames.length; i++) {
                if (!new File(dirName,fileNames[i]).isDirectory()
                        &&
                    new MyFile(fileNames[i]).findWord(word) > 0) {
                        result.append(fileNames[i]);
                        result.append('\n');
                }
            }
        return result.toString();
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getDirectory() {
        return this.directory;
    }
}
