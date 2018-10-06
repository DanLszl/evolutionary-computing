package algorithm.parentselection;

import algorithm.Individual;
import algorithm.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FitnessProportionalParentSelection implements ParentSelection {

    //TODO implement sigma scaling

    // Sphere evaluation doesn't work though??

    @Override
    public Population selectParents(Map<Individual, Double> populationFitness) {

        // Tried to follow the code in the java example of https://en.wikipedia.org/wiki/Fitness_proportionate_selection

        // First get the total fitness of population

        double totalFitness = 0;

        System.out.println(populationFitness.values());

        for (Double i : populationFitness.values()) {
            totalFitness += i;
        }

        // Now do a hundred iterations
        // We generate a new random variable
        // And we see where it falls
        // And from there choose which parent will go to the next round

        List<Individual> population = new ArrayList<>();

        for (int i=0; i<populationFitness.size(); i++) {

            double randomVar = Math.random()*totalFitness;

            for (Individual individual : populationFitness.keySet()){

                randomVar -= populationFitness.get(individual);

                if (randomVar < 0) {

                    population.add(individual);

                    break;

                }

            }

        }

        return new Population(population);
    }
}
