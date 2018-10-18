package algorithm.survivalselection;

import java.util.Arrays;
import algorithm.Population;

public class ElitismSurvivalSelection implements SurvivorSelection {

    @Override
    public Population selectSurvivors(Population previousPopulation, Population offspring) {
        offspring.sort();
        return new Population(offspring.getIndividuals().subList(0,100));
    }

}