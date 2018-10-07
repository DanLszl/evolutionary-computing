package algorithm.initialization;

import algorithm.Population;

public class RandomInitialization implements Initialization {

    private int populationSize;

    public RandomInitialization(int populationSize) {
        this.populationSize = populationSize;
    }

    @Override
    public Population initialize() {
        return new Population(populationSize);
    }
}
