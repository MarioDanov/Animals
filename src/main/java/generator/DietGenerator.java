package main.java.generator;

import java.util.Set;

public class DietGenerator {
    private static DietGenerator singleInstance = null;
    private final Set<Object> rabbitDiet;
    private final Set<Object> bearDiet;
    private final Set<Object> wolfDiet;

    private DietGenerator(Set<Object> rabbitDiet, Set<Object> bearDiet, Set<Object> wolfDiet) {
        this.rabbitDiet = rabbitDiet;
        this.bearDiet = bearDiet;
        this.wolfDiet = wolfDiet;
    }

    public static DietGenerator getInstance(Set<Object> rabbitDiet, Set<Object> bearDiet, Set<Object> wolfDiet) {
        if (singleInstance == null)
            singleInstance = new DietGenerator(rabbitDiet, bearDiet, wolfDiet);
        return singleInstance;
    }

    protected Set<Object> getRabbitDiet() {
        return rabbitDiet;
    }

    protected Set<Object> getBearDiet() {
        return bearDiet;
    }

    protected Set<Object> getWolfDiet() {
        return wolfDiet;
    }
}
