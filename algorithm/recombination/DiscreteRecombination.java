package algorithm.recombination;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class DiscreteRecombination implements Recombination {
    public Population recombine(Population parents) {

        Population offspring = new Population();

        Random rand = new Random();
        for(int offspringSize = 0; offspringSize < parents.size(); offspringSize++) {
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.getIndividuals().get(selectedIndex);

            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.getIndividuals().get(selectedIndex);


            Individual childA = new Individual();

            for (int i = 0; i < 10; i++) {
                double p = rand.nextDouble();
                if (p < 0.5) {
                    childA.setAllele(i, parentA.getAllele(i));
                } else {
                    childA.setAllele(i, parentB.getAllele(i));
                }
            }

            childA.setSigma((parentA.getSigma()+parentB.getSigma())/2);

            offspring.add(childA);
        }

        return offspring;

    }
}
