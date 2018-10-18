package algorithm.recombination;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class DiscreteRecombination implements Recombination {
    public Population recombine(Population parents) {

        Population offspring = new Population();

        Random rand = new Random();
        for(int offspringSize = 0; offspringSize < parents.size(); offspringSize++) {
            //select parents
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.getIndividuals().get(selectedIndex);

            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.getIndividuals().get(selectedIndex);

            Individual childA = new Individual();

            //recombination discrete on allele, intermediary on sigma
            for (int i = 0; i < 10; i++) {
                double p = rand.nextDouble();
                if (p < 0.5) {
                    childA.setAllele(i, parentA.getAllele(i));
                    childA.setSigma(i, parentA.getSigma(i));
                } else {
                    childA.setAllele(i, parentB.getAllele(i));
                    childA.setSigma(i, parentB.getSigma(i));
                }
                if (Math.abs(childA.getSigma(i)) > 1) {
                    System.out.println(childA.getSigma(i));
                }
            }

            offspring.add(childA);
        }

        return offspring;

    }
}
