package algorithm.parentselection;

import algorithm.Individual;
import algorithm.Population;


public class FitnessProportionalParentSelection implements ParentSelection {

    //TODO implement sigma scaling

    // Sphere evaluation doesn't work though??

    @Override
    public Population selectParents(Population previousPopulation) {
        // Tried to follow the code in the java example of https://en.wikipedia.org/wiki/Fitness_proportionate_selection

        // First get the total fitness of population

        double totalFitness = 0;

        // System.out.println(previousPopulation.getFitnessValues());

        for (Double i : previousPopulation.getFitnessValues()) {
            totalFitness += i;
        }

        // Now do a hundred iterations
        // We generate a new random variable
        // And we see where it falls
        // And from there choose which parent will go to the next round

        Population population = new Population(0);

        for (int i=0; i < previousPopulation.size(); i++) {

            double randomVar = Math.random()*totalFitness;

            for (Individual individual : previousPopulation.getIndividuals()){

                randomVar -= individual.getFitness();

                if (randomVar < 0) {

                    population.add(individual);

                    break;

                }

            }

        }

        return population;
    }
}
