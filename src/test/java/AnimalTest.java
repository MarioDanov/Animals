package test.java;

import main.java.creatures.Animal;
import main.java.creatures.Rabbit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    @DisplayName("Should set alive to false when energy is 0")
    void feedWhenEnergyIs0ThenSetAliveToFalse() {
        Set<Object> diet = Set.of("grass", "carrots");
        Animal rabbit = new Rabbit(1, diet);
        assertTrue(rabbit.isAlive());
        assertEquals(1, rabbit.getEnergy());
        rabbit.feed("food");
        assertFalse(rabbit.isAlive());
    }

    @Test
    @DisplayName("Should update energy with +1 when object is part of diet set")
    void updateEnergyWhenFeedWithObjectFromDiet() {
        Set<Object> diet = Set.of("grass", "carrots");
        Animal rabbit = new Rabbit(1, diet);
        int beforeFeedEnergy = rabbit.getEnergy();
        rabbit.feed("grass");
        assertEquals(beforeFeedEnergy + 1, rabbit.getEnergy());
    }

    @Test
    @DisplayName("Should update energy with -1 when object is not part of diet set")
    void updateEnergyWhenFeedWithObjectNotFromDiet() {
        Set<Object> diet = Set.of("grass", "carrots");
        Animal rabbit = new Rabbit(1, diet);
        int beforeFeedEnergy = rabbit.getEnergy();
        rabbit.feed("meat");
        assertEquals(beforeFeedEnergy - 1, rabbit.getEnergy());
    }

}