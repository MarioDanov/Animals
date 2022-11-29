package main.java;

import main.java.creatures.Animal;
import main.java.generator.AnimalGenerator;
import main.java.generator.DietGenerator;
import main.java.utilities.DietUtils;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Application{

    public static void main(String[] args) {

        DietGenerator diets = DietGenerator.getInstance(DietUtils.getAnimalDiet("rabbit"), DietUtils.getAnimalDiet("wolf"), DietUtils.getAnimalDiet("bear"));

        HashMap<String, Integer> input = new HashMap<>();

        Scanner myObj = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to our game of animals!");

        System.out.println();
        System.out.println("Please select how many rabbits do you want?");
        input.put("rabbit", myObj.nextInt());
        System.out.println("Currently you have " + input.get("rabbit") + " number of " + "rabbits.");
        System.out.println("Please select how many bears do you want?");
        input.put("bear", myObj.nextInt());
        System.out.println("Currently you have " + input.get("bear") + " number of " + "bears.");
        System.out.println("Please select how many wolfs do you want?");
        input.put("wolf", myObj.nextInt());
        System.out.println("Currently you have " + input.get("wolf") + " number of " + "wolfs.");

        AnimalGenerator animalGenerator = new AnimalGenerator();
        animalGenerator.generate(input, diets);
        Set<Animal> animals = animalGenerator.getAnimals();


        while (animals.stream().anyMatch(Animal::isAlive)) {
            System.out.println("What would you like to feed them?");
            if (myObj.hasNext()) {
                String food = myObj.next();
                for (Animal a : animals) {
                    a.feed(food);
                }
            }
        }
        System.out.println("The animals are dead! Nobody to feed!");
    }
}
