package algorithm.survivalselection;


import algorithm.Population;
import algorithm.Individual;
import algorithm.parentselection.ParentSelection;
import algorithm.parentselection.TournamentParentSelection;


public class TournamentSurvivalSelection implements SurvivorSelection {

    private final int tournamentSize;

    public TournamentSurvivalSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Population selectSurvivors(Population previousPopulation, Population offspring) {

        //add parents to the offspring

        for (Individual individual: previousPopulation.getIndividuals()){
            offspring.add(individual);
        }


        //EXACT COPY FROM TOURNAMENTPARENTSELECTION
        // required as it only takes 1 argument 

        Population population = new Population(0);

        for (int i = 0; i < previousPopulation.size(); i++) {

            Population tournamentGroup = new Population();

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

            population.add(winner);

        }

        return population;

    }

}
