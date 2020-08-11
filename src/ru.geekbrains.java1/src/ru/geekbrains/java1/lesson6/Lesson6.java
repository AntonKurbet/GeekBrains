package ru.geekbrains.java1.lesson6;

import java.io.FileNotFoundException;

public class Lesson6 {

    public static void main(String[] args) {
        MyFile f1 = new MyFile("test1.txt");
        MyFile f2 = new MyFile("test2.txt");
        MyFile f3 = new MyFile("test3.txt");

        try {
            f1.createFromString("1. Sozdat 2 tekstovyh fajla, primerno po 50-100 simvolov]\n" +
                    "   v kazhdom(osobogo znacheniya ne imeet);\n" +
                    "2. Napisat programmu, skleivayushchuyu eti fajly,\n" +
                    "   to est vnachale idet tekst iz pervogo fajla,\n" +
                    "   potom tekst iz vtorogo.\n");
            f2.createFromString("3*. Napisat programmu, kotoraya proveryaet prisutstvuet li \n" +
                    "   ukazannoe polzovatelem slovo v fajle (rabotaem tolko s latinicej).\n" +
                    "4**. Napisat metod, proveryayushchij, est li ukazannoe slovo v papke\n");            f3.copyFromFile(f1.getName(),false);
            f3.appendFromFile(f2.getName());

            String word1 = "skleivayushchuyu";
            long result = f3.findWord(word1);
            System.out.printf("File %s has %s word \"%s\"\n",
                    f3.getName(),result == 0 ? "no" : "in line " + result,word1);

            String word2 = "li";
            String searchResult = MyFile.findWordInDir(f1.getDir(),word2);
            System.out.printf("Word \"%s\" is %s ", word2,
                    searchResult.equals("") ? "not found in dir " + f1.getDir() : "found in this files:\n" + searchResult);

        } catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
        } catch (RuntimeException rte) {
            System.out.println(rte.getMessage());
            rte.printStackTrace();
        }
}
}
