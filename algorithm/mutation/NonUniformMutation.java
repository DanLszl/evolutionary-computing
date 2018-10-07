package algorithm.mutation;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class NonUniformMutation extends Mutation {

    //std. deviation for the Gaussian distrib. aka. mutation step-size
    double sigma;

    public NonUniformMutation(double sigma, double lB, double uB) {
        super(lB, uB);
        this.sigma = sigma;
    }

    @Override
    public Population mutate(Population population) {
        Random rand = new Random();
        Population mutated = population.clone();
        for (Individual i : mutated.getIndividuals()) {
            for (int j = 0; j < i.genotypeLength(); j++) {

                //Gauss dist with mean=0, std.dev=sigma
                double deltaValue = rand.nextGaussian()*sigma;
                double newValue = checkBoundaries(i.getAllele(j) + deltaValue);

                i.setAllele(j, newValue);
            }
        }
        return mutated;
    }

}
