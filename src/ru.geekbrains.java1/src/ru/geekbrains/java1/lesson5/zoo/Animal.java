package ru.geekbrains.java1.lesson5.zoo;

public abstract class Animal {
    private String name;
    protected int maxRunDistance;
    protected float maxHeight;
    protected int maxSwimDistance;

    protected Animal(String name) {
        this.name = name;
    }

    protected Animal(String name, int maxRunDistance, float maxHeight, int maxSwimDistance) {
        this(name);
        this.maxRunDistance = maxRunDistance;
        this.maxHeight = maxHeight;
        this.maxSwimDistance = maxSwimDistance;
    }

    public void run(int distance) {
        if (maxRunDistance > 0)
            System.out.printf("%s runs %d m\n", name, Math.min(distance, maxRunDistance));
        else
            System.out.printf("%s didn't run\n", name);
    }

    public void swim(int distance) {
        if (maxSwimDistance > 0)
            System.out.printf("%s swims %d m\n", name, Math.min(distance,maxSwimDistance));
        else
            System.out.printf("%s didn't swim\n", name);
    }

    public void jump(float height) {
        if (maxHeight > 0)
            System.out.printf("%s jumps %2.2f m\n", name, Math.min(height,maxHeight));
        else
            System.out.printf("%s didn't jump\n", name);
    }
}
