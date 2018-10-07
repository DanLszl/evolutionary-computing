package algorithm.survivalselection;


import algorithm.Population;


public class ReplaceAllSurvivalSelection implements SurvivorSelection {

    @Override
    public Population selectSurvivors(Population previousPopulation, Population offspring) {
        return offspring;
    }

}
