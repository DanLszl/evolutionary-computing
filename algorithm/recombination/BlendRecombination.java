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

            double u = Math.random();

            double gamma = (1 - 2*blendAlpha) * u - blendAlpha;

            // Selects first parent
            int selectedIndex = rand.nextInt(parents.size());
            Individual parentA = parents.getIndividuals().get(selectedIndex);

            // Selects second parent
            selectedIndex = rand.nextInt(parents.size());
            Individual parentB = parents.getIndividuals().get(selectedIndex);

            Individual childA = new Individual();
            Individual childB = new Individual();

            int genomeLength = parentA.genotypeLength();

            // Create a list to hold the bottom values
            List<Double> blendGenotypeBottom = new ArrayList<>();

            // Create a list to hold the top values
            List<Double> blendGenotypeTop = new ArrayList<>();

            List<Double> blendSigmaBottom = new ArrayList<>();
            List<Double> blendSigmaTop = new ArrayList<>();

            for(int i=0; i<genomeLength; i++){

                double parentDifference = Math.abs(parentA.getAllele(i)-parentB.getAllele(i));

                // If the alleles are the same, it doesn't matter which parent you choose to be the biggest or lowest

                if (parentA.getAllele(i) >= parentB.getAllele(i)){
                    blendGenotypeBottom.add(parentB.getAllele(i)-(blendAlpha*parentDifference));
                    blendGenotypeTop.add(parentA.getAllele(i)+(blendAlpha*parentDifference));
                } else if (parentA.getAllele(i) < parentB.getAllele(i)){
                    blendGenotypeBottom.add(parentA.getAllele(i)-(blendAlpha*parentDifference));
                    blendGenotypeTop.add(parentB.getAllele(i)+(blendAlpha*parentDifference));
                }


                double sigmaDifference = Math.abs(parentA.getSigma(i)-parentB.getSigma(i));

                if (parentA.getSigma(i) >= parentB.getSigma(i)){
                    blendSigmaBottom.add(parentB.getSigma(i)-(blendAlpha*sigmaDifference));
                    blendSigmaTop.add(parentA.getSigma(i)+(blendAlpha*sigmaDifference));
                } else if (parentA.getSigma(i) < parentB.getSigma(i)){
                    blendSigmaBottom.add(parentA.getSigma(i)-(blendAlpha*sigmaDifference));
                    blendSigmaTop.add(parentB.getSigma(i)+(blendAlpha*sigmaDifference));
                }


                // in some uniform way choose a value from the above

                double firstValueToSet = Math.random()*(blendGenotypeTop.get(i)-blendGenotypeBottom.get(i)) + blendGenotypeBottom.get(i);

                childA.setAllele(i, firstValueToSet);

                double secondValueToSet = Math.random()*(blendGenotypeTop.get(i)-blendGenotypeBottom.get(i)) + blendGenotypeBottom.get(i);

                childB.setAllele(i, secondValueToSet);



                double firstSigmaToSet = Math.random()*(blendSigmaTop.get(i)-blendSigmaBottom.get(i)) + blendSigmaBottom.get(i);

                childA.setSigma(i, firstSigmaToSet);

                double secondSigmaToSet = Math.random()*(blendSigmaTop.get(i)-blendSigmaBottom.get(i)) + blendSigmaBottom.get(i);

                childB.setSigma(i, secondSigmaToSet);


            }

            offspring.add(childA);
            offspring.add(childB);
        }

        return offspring;

    }
}
