package algorithm.recombination;

import algorithm.Individual;
import algorithm.Population;

import java.util.Random;

public class DiscreteRecombination implements Recombination {
    public Population recombine(Population parents) {

        Population offspring = new Population(0);


        Random rand = new Random();
        for(int offspringSize = 0; offspringSize < parents.size() / 2; offspringSize++) {
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.individuals.get(selectedIndex);

            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.individuals.get(selectedIndex);

            int cutIndex = rand.nextInt(11);

            Individual childA = new Individual();
            Individual childB = new Individual();


            for (int i = 0; i < 10; i++) {
                if (i < cutIndex) {
                    childA.genotype[i] = parentA.genotype[i];
                    childB.genotype[i] = parentB.genotype[i];
                } else {
                    childA.genotype[i] = parentB.genotype[i];
                    childB.genotype[i] = parentA.genotype[i];
                }
            }

            offspring.individuals.add(childA);
            offspring.individuals.add(childB);
        }

        return offspring;

    }
}
