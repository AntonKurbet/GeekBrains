package ru.geekbrains.java1.lesson5;

import ru.geekbrains.java1.lesson5.zoo.Animal;
import ru.geekbrains.java1.lesson5.zoo.Cat;
import ru.geekbrains.java1.lesson5.zoo.Dog;

import java.util.Random;

public class Lesson5 {

    //1. Создать классы Собака и Кот с наследованием от класса Животное.
    //2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
    // В качестве параметра каждому методу передается величина,
    // означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
    //3. У каждого животного есть ограничения на действия
    // (бег: кот 200 м., собака 500 м; прыжок: кот 2 м., собака 0.5 м.;
    // плавание: кот не умеет плавать, собака 10 м., .).
    //4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат.
    // (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
    //5. * Добавить животным разброс в ограничениях.
    // То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
    public static void main(String[] args) {
        Cat c = new Cat("Barsik");
        Dog d = new Dog("Sharick");

        c.run(150);
        d.run(700);
        c.jump(3);
        d.jump(3);
        c.swim(8);
        d.swim(8);

        System.out.println("-----------");

        Random r = new Random(300);

        Animal[] animals = {new Dog("Tuzick", 200, 1f, 50),
                            new Cat("Murzick", 100, 2.5f, 5) };
        for (int i = 0; i < animals.length; i++) {
            animals[i].run(r.nextInt(300));
            animals[i].swim(r.nextInt(100));
            animals[i].jump(r.nextFloat() * 5);
        }
    }
}
