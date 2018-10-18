package algorithm.recombination;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class DiscreteRecombination implements Recombination {
    public Population recombine(Population parents) {
        System.out.println(parents.size());
        Population offspring = new Population();

        Random rand = new Random();
        for(int offspringSize = 0; offspringSize < parents.size()*7 ; offspringSize++) {
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.getIndividuals().get(selectedIndex);

            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.getIndividuals().get(selectedIndex);

            //int cutIndex = rand.nextInt(11);

            Individual childA = new Individual();

            for (int i = 0; i < 10; i++) {
                double p = rand.nextDouble();
                if (p < 0.5) {
                    childA.setAllele(i, parentA.getAllele(i));
                    childA.setSigma(i, (parentA.getSigma(i) + parentB.getSigma(i))/2);

                } else {
                    childA.setAllele(i, parentB.getAllele(i));
                    childA.setSigma(i, (parentA.getSigma(i) + parentB.getSigma(i))/2);
                }
            }

            offspring.add(childA);
        }
        System.out.println(offspring.size());
        return offspring;

    }
}
