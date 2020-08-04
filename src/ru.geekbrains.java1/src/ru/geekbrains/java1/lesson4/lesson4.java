package ru.geekbrains.java1.lesson4;

public class lesson4 {
    //   4. Вывести при помощи методов из пункта 3 ФИО и возраст.
    //   5. Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
    //   6. * Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
    //   7. * Подсчитать средние арифметические зарплаты и возраста

    private static void incSalOver45(Employee[] emps) {
        for (int i = 0; i < emps.length; i++) {
            if (emps[i].getAge() > 45) emps[i].setSalary(emps[i].getSalary() + 5000);
        }
    }

    private static void printAllSalary(Employee[] emps) {
        for (int i = 0; i < emps.length; i++) {
            System.out.printf("Employee: %s salary: %8.2f\n",emps[i].getName(),emps[i].getSalary());
        }
    }

    private static float getAvgSalary(Employee[] emps) {
        float sum = 0;
        for (int i = 0; i < emps.length; i++) {
            sum += emps[i].getSalary();
        }
        return sum / emps.length;
    }

    private static float getAvgAge(Employee[] emps) {
        float sum = 0;
        for (int i = 0; i < emps.length; i++) {
            sum += emps[i].getAge();
        }
        return sum / emps.length;
    }

    public static void main(String[] args) {
        Employee emp = new Employee("Ivan I. Ivanov",25, 100000);
        System.out.printf("Employee: %s age: %d\n",emp.getName(),emp.getAge());

        Employee[] emps = new Employee[5];
        emps[0] = new Employee(emp);
        emps[1] = new Employee("Petr P. Petrov", 35 , 150000);
        emps[2] = new Employee("Vasily V. Vasiliev", 42 , 200000);
        emps[3] = new Employee("Nikolay N. Nikolaev", 46 , 220000);
        emps[4] = new Employee("Fedor F. Fedorov", 48 , 240000);

        for (int i = 0; i < emps.length; i++) {
            if (emps[i].getAge() > 40)
                System.out.print(emps[i].GetInfo());
        }
        System.out.println();

        incSalOver45(emps);
        printAllSalary(emps);
        System.out.println();

        System.out.printf("Average salary: %6.2f\n",getAvgSalary(emps));
        System.out.printf("Average age: %6.2f\n",getAvgAge(emps));
    }
}
