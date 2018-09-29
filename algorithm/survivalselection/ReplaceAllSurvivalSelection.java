package algorithm.survivalselection;

import algorithm.Individual;

import java.util.Map;

public class ReplaceAllSurvivalSelection implements SurvivorSelection {

    @Override
    public Map<Individual, Double> selectSurvivors(Map<Individual, Double> previousPopulationFitness, Map<Individual, Double> offspringFitness) {
        return offspringFitness;
    }
}
