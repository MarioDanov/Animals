package main.java.generator;

import main.java.creatures.Animal;
import main.java.creatures.Bear;
import main.java.creatures.Rabbit;
import main.java.creatures.Wolf;

import java.util.*;

public class AnimalGenerator{
    private final Set<Animal> animals = new HashSet<>();
    private final Random energy = new Random();

    public void generate(Map<String, Integer> inputAnimals, DietGenerator diets) {
        inputAnimals.forEach((animal, number) -> {
            if (number > 0) {
                switch (animal.toLowerCase(Locale.ROOT)) {

                    case "rabbit" -> this.animals.addAll(generateRabbit(number, diets.getRabbitDiet()));

                    case "bear" -> this.animals.addAll(generateBear(number, diets.getBearDiet()));

                    case "wolf" -> this.animals.addAll(generateWolf(number, diets.getWolfDiet()));

                    default -> throw new RuntimeException("Unknown animal: " + animal);
                }
            }

        });
    }

    private Set<Rabbit> generateRabbit(Integer number, Set<Object> rabbitDiet) {
        Set<Rabbit> rabbits = new HashSet<>();
        while (number > 0) {
            number -= 1;
            rabbits.add(new Rabbit(this.energy.nextInt(1, 5), rabbitDiet));
        }
        return rabbits;
    }

    private Set<Bear> generateBear(Integer number, Set<Object> bearDiet) {
        Set<Bear> bears = new HashSet<>();
        while (number > 0) {
            number -= 1;
            bears.add(new Bear(this.energy.nextInt(1, 15), bearDiet));
        }
        return bears;
    }

    private Set<Wolf> generateWolf(Integer number, Set<Object> wolfDiet) {
        Set<Wolf> wolfs = new HashSet<>();
        while (number > 0) {
            number -= 1;
            wolfs.add(new Wolf(this.energy.nextInt(1, 12), wolfDiet));
        }
        return wolfs;
    }

    public Set<Animal> getAnimals() {
        return this.animals;
    }

}
