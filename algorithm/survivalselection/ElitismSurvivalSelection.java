package algorithm.survivalselection;

import java.util.Arrays;
import algorithm.Population;

public class ElitismSurvivalSelection implements SurvivorSelection {

    @Override
    public Population selectSurvivors(Population previousPopulation, Population offspring) {
//        Arrays.sort(offspring);
        return offspring;
    }

}