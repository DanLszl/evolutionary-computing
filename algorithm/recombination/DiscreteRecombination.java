package algorithm.recombination;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class DiscreteRecombination implements Recombination {
    public Population recombine(Population parents) {

        Population offspring = new Population();

        Random rand = new Random();
        for(int offspringSize = 0; offspringSize < parents.size() / 2; offspringSize++) {
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.getIndividuals().get(selectedIndex);

            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.getIndividuals().get(selectedIndex);

            int cutIndex = rand.nextInt(11);

            Individual childA = new Individual();
            Individual childB = new Individual();

            for (int i = 0; i < 10; i++) {
                if (i < cutIndex) {
                    childA.setAllele(i, parentA.getAllele(i));
                    childB.setAllele(i, parentB.getAllele(i));

                    childA.setSigma(parentA.getSigma());
                    childB.setSigma(parentB.getSigma());
                } else {
                    childA.setAllele(i, parentB.getAllele(i));
                    childB.setAllele(i, parentA.getAllele(i));

                    childA.setSigma(parentB.getSigma());
                    childB.setSigma(parentA.getSigma());
                }
            }

            offspring.add(childA);
            offspring.add(childB);
        }

        return offspring;

    }
}
