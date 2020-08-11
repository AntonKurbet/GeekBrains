package ru.geekbrains.java1.lesson5.zoo;

import ru.geekbrains.java1.lesson5.zoo.Animal;

public class Dog extends Animal {
    public Dog (String name) {
        super(name);
        maxRunDistance = 500;
        maxHeight = 0.5f;
        maxSwimDistance = 10;
    }

    public Dog(String name, int maxRunDistance, float maxHeight, int maxSwimDistance) {
        super(name,maxRunDistance,maxHeight,maxSwimDistance);
    }
}
