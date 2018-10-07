package algorithm.mutation;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class UniformMutation implements Mutation {

    // [0, 1]
    double probabilityOfMutating;

    public UniformMutation(double p) {
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
                    i.setAllele(j, rand.nextDouble() * 10 - 5);
                }
            }
        }
        return mutated;
    }
}
