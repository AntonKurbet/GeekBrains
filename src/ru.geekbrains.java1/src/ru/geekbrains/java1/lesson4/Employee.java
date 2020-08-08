package ru.geekbrains.java1.lesson4;

public class Employee {
//   1. Создать класс "Сотрудник" с полями: ФИО, зарплата, возраст;
//   2. Конструктор класса должен заполнять эти поля при создании объекта;
//   3. Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
//   8. *** Продумать конструктор таким образом, чтобы при создании каждому сотруднику присваивался личный
//   уникальный идентификационный порядковый номер
    private String name;
    private float salary;
    private int age;
    private final int id;

    private static int idSequence = 0;

    public Employee(String name, int age, float salary) {
        this();
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(Employee emp) {
        this.id = emp.id;
        this.salary = emp.salary;
        this.age = emp.age;
        this.name = emp.name;
    }

    private Employee() {
        this.id = idSequence;
        idSequence++;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public String GetInfo() {
        return String.format("Employee: %s age: %d ID: %06d\n", this.name, this.age, this.id);
    }
}
