package algorithm.parentselection;

import algorithm.Individual;
import algorithm.Population;

import java.util.stream.Collectors;


public class TournamentParentSelection implements ParentSelection {

    protected int tournamentSize;

    public TournamentParentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }
    
    @Override
    public Population selectParents(Population previousPopulation) {

        Population population = new Population(0);

        //Keep going until we have the same population size again

        for (int i = 0; i < previousPopulation.size(); i++) {

            Population tournamentGroup = new Population();

            // Build a single tournament group

            for (int j=0; j<tournamentSize; j++){

                int randomVar = (int) (Math.random()*previousPopulation.size());
                int counter = 0;
                for (Individual k : previousPopulation.getIndividuals()){

                    if (counter == randomVar){
                        tournamentGroup.add(k);
                        break;
                    }

                    counter++;
                }

            }

            // Select the winner from the tournament

            Individual winner = null;

            boolean firstRound = true;

            for (Individual competitor : tournamentGroup.getIndividuals()){
                if ( firstRound ) {
                    winner = competitor;
                    firstRound = false;
                } else if (competitor.getFitness() > winner.getFitness()){
                    winner = competitor;
                }
            }

            // Add the winner to the next population

            population.add(winner);

        }

        return new Population(population.getIndividuals().stream().distinct().collect(Collectors.toList()));
    }
}
