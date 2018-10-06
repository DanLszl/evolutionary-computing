package algorithm.parentselection;

import algorithm.Individual;
import algorithm.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TournamentParentSelection implements ParentSelection {

    @Override
    public Population selectParents(Map<Individual, Double> populationFitness) {

        int tournamentSize = 20;


        List<Individual> population = new ArrayList<>();

        //Keep going until we have the same population size again

        for (int i=0; i<populationFitness.size(); i++) {

            Map <Individual, Double> tournamentGroup = new HashMap<Individual, Double>();

            // Build a single tournament group

            for (int j=0; j<tournamentSize; j++){

                int randomVar = (int) (Math.random()*populationFitness.size());
                int counter = 0;
                for (Individual k : populationFitness.keySet()){

                    if (counter == randomVar){
                        tournamentGroup.put(k , populationFitness.get(k));
                        break;
                    }

                    counter++;
                }

            }

            // Select the winner from the tournament

            Individual winner = null;

            boolean firstRound = true;

            for (Individual competitor : tournamentGroup.keySet()){
                if ( firstRound ) {
                    winner = competitor;
                    firstRound = false;
                } else if (tournamentGroup.get(competitor) > tournamentGroup.get(winner)){
                    winner = competitor;
                }
            }

            // Add the winner to the next population

            population.add(winner);

        }

        return new Population(population);
    }
}
