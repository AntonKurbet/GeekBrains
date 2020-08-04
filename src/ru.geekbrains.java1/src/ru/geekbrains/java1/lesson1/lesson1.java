package ru.geekbrains.java1.lesson1;

public class lesson1 {
    //Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат с плавающей точкой,
    // где a, b, c, d – целочисленные входные параметры этого метода

    public static double Calc (int a, int b, int c, int d) {
        return a * (b + (c / (double)d));
    }

    //Написать метод, принимающий на вход два целых числа, и проверяющий что их сумма лежит в пределах
    // от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    public static boolean Check(int a, int b) {
        long c = a + b;
        return (c  >= 10 & c <= 20);
    }

    //Написать метод, которому в качестве параметра передается целое число, метод должен проверить положительное
    // ли число передали, или отрицательное. Замечание: ноль считаем положительным числом.
    // Результат работы метода вывести в консоль
    public static void printIsPositive (int a) {
        String text;
        if (a >=0) text = "положительное";
        else text = "отрицательное";
        System.out.println(text);
    }

    //Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вернуть
    // приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль.
    public static void sayHello (String name) {
        System.out.println("Привет, " + name + '!');
    }

    //Написать метод, который определяет является ли год високосным. Каждый 4-й год является високосным,
    // кроме каждого 100-го, при этом каждый 400-й – високосный.
    // Для проверки работы вывести результаты работы метода в консоль
    public static boolean isLeapYear (int year) {
        return (year % 4 == 0 & year % 100 !=0) | (year % 400 == 0);
    }

    public static void main (String[] args){
        System.out.println(Calc(20,30,40,50));
        System.out.println(Check(10,5));
        System.out.println(Check(20,5));
        printIsPositive(-1);
        printIsPositive(0);
        sayHello("Иван");
        System.out.println(isLeapYear(1996));
        System.out.println(isLeapYear(2000));
        System.out.println(isLeapYear(1900));
        System.out.println(isLeapYear(2020));
    }
}
