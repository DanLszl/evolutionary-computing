package algorithm.mutation;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class UniformMutation extends Mutation {

    // [0, 1]
    double probabilityOfMutating;

    //Boundaries for the mutated genome values
    double lowerBoundary;
    double upperBoundary;

    public UniformMutation(double p, double lB, double uB) {

        super(lB, uB);
        this.probabilityOfMutating = p;
    }

    @Override
    public Population mutate(Population population) {
        Random rand = new Random();
        Population mutated = population.clone();
        for (Individual i : mutated.getIndividuals()) {
            for (int j = 0; j < i.genotypeLength(); j++) {

                boolean replace = probabilityOfMutating > rand.nextDouble();
                if (replace) {
                    double newValue = checkBoundaries(rand.nextDouble() * 10 - 5);
                    i.setAllele(j, newValue);
                }
            }
        }
        return mutated;
    }
}
