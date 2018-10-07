package algorithm.mutation;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class NonUniformMutation implements Mutation {

    // [0, 1]
    double probabilityOfMutating;
    double step;

    public NonUniformMutation(double p, double sigma) {this.probabilityOfMutating = p; this.step = sigma;}

    @Override
    public Population mutate(Population population) {
        Random rand = new Random();
        Population mutated = population.clone();
        for (Individual i : mutated.getIndividuals()) {
            for (int j = 0; j < i.genotypeLength(); j++) {

                boolean replace = probabilityOfMutating > rand.nextDouble();
                if (replace) {

                    // parameters for the gaussian modeling (sigma already as argument)
                    double mean = 0.0 ;

                    double oldAllele = i.getAllele(j) ;

                    // New allele is the old one plus the random gaussian addition mutation
                    double newAllele = oldAllele + rand.nextGaussian() * step + mean ;

                    // Make sure newAllele is within genetic range [-5,5]
                    while (Math.abs(newAllele) > 5){
                        if (newAllele > 5){
                            //newAllele = 5 ; // Alternate version!
                            newAllele = newAllele - 10 ;
                        } else {
                            // newAllele = -5 ; // Alternate version!
                            newAllele = newAllele + 10 ;
                        }
                    }

                    i.setAllele(j, newAllele);

                }
            }
        }
        return mutated;
    }
}
