package main.java.creatures;

import java.util.Set;

// in Java protected serves different purposes compared to protected in C#
public abstract class Animal {
    private final Set<Object> diet;
    public boolean alive = true;
    private int energy;

    Animal(int energy, Set<Object> diet) {
        this.energy = energy;
        this.diet = diet;
    }

    public void feed(Object food) {
        if (getEnergy() > 0)
            if (this.diet.contains(food))
                updateEnergy(1);
            else {
                updateEnergy(-1);
                if (getEnergy() == 0)
                    setAlive(false);
            }
    }

    public int getEnergy() {
        return energy;
    }

    public void updateEnergy(int energy) {
        this.energy += energy;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
