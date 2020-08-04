package ru.geekbrains.java1.lesson2;

import java.util.Arrays;

public class lesson2 {
//    1 Задать целочисленный массив, состоящий из элементов 0 и 1.
//    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//    Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
    static int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
    private static void negativeArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            a[i] = (a[i] + 1) % 2;
        }
    }
//2 Задать пустой целочисленный массив размером 8.
// Написать метод, который помощью цикла заполнит его
// значениями 1 4 7 10 13 16 19 22;
    static int[] arr2 = new int[8];
    private static void fillArray(int[] a){
        for (int i = 0; i < a.length; i++) {
            a[i] = 1 + i * 3;
        }
    }
//3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
// написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
    static int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
    private static void recalcArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 6) a[i] *= 2;
        }
    }
//4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
    static int[] arr4;
    // проверок не делал, т.к. нрм варианта кроме обработки ошибок не вижу,
    // но мы еще не учили
    private static int getMinArrayElement() {
        int result;
        result = arr4[0];
        for (int i = 1; i < arr4.length; i++) {
            if (arr4[i] < result) result = arr4[i];
        }
        return result;
    }
    private static int getMaxArrayElement() {
        int result;
        result = arr4[0];
        for (int i = 1; i < arr4.length; i++) {
            if (arr4[i] > result) result = arr4[i];
        }
        return result;
    }

//5 * Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
// заполнить его диагональные элементы единицами, используя цикл(ы);
    static int[][] arr5 = new int[4][4];
    private static void fillDiagonal() {
        for (int i = 0; i < arr5.length; i++) {
            arr5[i][i] = 1;
        }
    }
//6 ** Написать метод, в который передается не пустой одномерный целочисленный массив,
// метод должен вернуть true если в массиве есть место, в котором сумма левой и правой
// части массива равны.
// Примеры: checkBalance([1, 1, 1, 2, 1]) → true,
// checkBalance ([2, 1, 1, 2, 1]) → false,
// checkBalance ([10, 1, 2, 3, 4]) → true.

    private static boolean checkBalance(int[] a) {
        boolean result = false;
        if (a.length > 1) {
            int left = a[0];
            int right = 0;
            for (int i = 1; i < a.length; i++) right += a[i];
            int i = 1;
            while ((left != right) & ( i != a.length)) {
                left += a[i];
                right -= a[i];
                i++;
            }
            result = left == right;
        }
        return result;
    }
//  7. **** Написать метод, которому на вход подается одномерный массив и число n
//  (может быть положительным, или отрицательным), при этом метод должен сместить все
//  элементы массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    private static void shiftArray(int[] a, int n) {
        int b;
//        вариант 0
//        int k = a.length - 1;
//        if (n > 0) {
//            for (int i = 0; i < n; i++) {
//                b = a[k];
//                for (int j = k; j > 0; j--) {
//                    a[j] = a[j - 1];
//                }
//                a[0] = b;
//            }
//        } else {
//            for (int i = 0; i < -n; i++) {
//                b = a[0];
//                for (int j = 0; j < k; j++) {
//                    a[j] = a[j + 1];
//                }
//                a[k] = b;
//            }
//        }

        int k = a.length - 1;
        n = n % a.length;
        if (n < 0) n = (a.length + n) % a.length;
        for (int i = 0; i < n; i++) {
            b = a[k];
            for (int j = k; j > 0; j--) {
                a[j] = a[j - 1];
            }
            a[0] = b;
        }
    }

    public static void main (String[] args){
        System.out.println("    Arr1=" + Arrays.toString(arr1));
        negativeArray(arr1);
        System.out.println("Neg Arr1=" + Arrays.toString(arr1));
        System.out.println("       Arr2=" + Arrays.toString(arr2));
        fillArray(arr2);
        System.out.println("Filled Arr2=" + Arrays.toString(arr2));
        System.out.println("Old Arr3=" + Arrays.toString(arr3));
        recalcArray(arr3);
        System.out.println("New Arr3=" + Arrays.toString(arr3));
        arr4 = arr3;
        if (arr4 != null) System.out.println("Arr4=" + Arrays.toString(arr4));
        System.out.println("Min=" + getMinArrayElement());
        System.out.println("Max=" + getMaxArrayElement());
        System.out.println("       Arr5=" + Arrays.deepToString(arr5));
        fillDiagonal();
        System.out.println("Filled Arr5=" + Arrays.deepToString(arr5));
        System.out.println("checkBalance([1, 1, 1, 2, 1]=" + checkBalance(new int[]{1, 1, 1, 2, 1}));
        System.out.println("checkBalance([2, 1, 1, 2, 1]=" + checkBalance(new int[]{2, 1, 1, 2, 1}));
        System.out.println("checkBalance([10, 1, 2, 3, 4]=" + checkBalance(new int[]{10, 1, 2, 3, 4}));
        int [] arr7 = new int[] {1,2,3,4,5};
        System.out.printf("shiftArray(%s,%d)=",Arrays.toString(arr7),3);
        shiftArray(arr7, 3);
        System.out.println(Arrays.toString(arr7));
        shiftArray(arr7, -3);
        System.out.printf("shiftArray(%s,%d)=",Arrays.toString(arr7),-12);
        shiftArray(arr7, -12);
        System.out.println(Arrays.toString(arr7));
    }
}
