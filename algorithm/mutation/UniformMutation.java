package algorithm.mutation;

import algorithm.Individual;
import algorithm.Population;

public class UniformMutation implements Mutation {

    // [0, 1]
    double probabilityOfMutating;

    public UniformMutation(double p) {
        this.probabilityOfMutating = p;
    }

    @Override
    public Population mutate(Population population) {
        Population mutated = population.clone();
        for (Individual i : mutated.individuals) {
            for (int j = 0; j < i.genotype.length; j++) {

                boolean replace = probabilityOfMutating > Math.random();
                if (replace) {
                    i.genotype[j] = Math.random() * 10 - 5;
                }
            }
        }
        return mutated;
    }
}
