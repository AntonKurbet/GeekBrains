package ru.geekbrains.java1.lesson5.zoo;

import ru.geekbrains.java1.lesson5.zoo.Animal;

public class Cat extends Animal {
    public Cat (String name) {
        super(name);
        maxRunDistance = 200;
        maxHeight = 2f;
        maxSwimDistance = 0;
    }

    public Cat(String name, int maxRunDistance, float maxHeight, int maxSwimDistance) {
        super(name,maxRunDistance,maxHeight,maxSwimDistance);
    }
}
