package algorithm.recombination;

import algorithm.Individual;
import algorithm.Population;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class BlendRecombination implements Recombination {

    private final double blendAlpha;

    public BlendRecombination(double blendAlpha){
        this.blendAlpha = blendAlpha;
    }


    @Override
    public Population recombine(Population parents) {

        Population offspring = new Population();

        Random rand = new Random();

        // This for loop is misleading. It actually creates two new children
        for(int offspringSize = 0; offspringSize < parents.size()/2; offspringSize++) {

            // Selects first parent
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.getIndividuals().get(selectedIndex);

            // Selects second parent
            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.getIndividuals().get(selectedIndex);

            Individual childA = new Individual();
            Individual childB = new Individual();

            int genomeLength = parentA.genotypeLength();

            double p_combine_simga = rand.nextDouble();

            for(int i=0; i<genomeLength; i++){

                double parentDifference = Math.abs(parentA.getAllele(i)-parentB.getAllele(i));
                double bottomAllele = Math.min(parentA.getAllele(i), parentB.getAllele(i)) - blendAlpha*parentDifference;
                double topAllele = Math.max(parentA.getAllele(i), parentB.getAllele(i)) + blendAlpha*parentDifference;

                childA.setAllele(i, rand.nextDouble()*(topAllele-bottomAllele) + bottomAllele);
                childB.setAllele(i, rand.nextDouble()*(topAllele-bottomAllele) + bottomAllele);

                if (p_combine_simga <0.5) {
                    childA.setSigma(i,parentA.getSigma(i));
                    childB.setSigma(i,parentB.getSigma(i));
                } else {
                    childA.setSigma(i,parentB.getSigma(i));
                    childB.setSigma(i,parentA.getSigma(i));
                }

                //if (Math.abs(childB.getSigma(i)) > 1000) {
                //    System.out.println(childB.getSigma(i));
                //}
            }

            offspring.add(childA);
            offspring.add(childB);
        }

        return offspring;

    }
}
