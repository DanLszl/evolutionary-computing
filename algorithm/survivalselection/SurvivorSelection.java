package algorithm.survivalselection;

import algorithm.Individual;

import java.util.Map;

public interface SurvivorSelection {
    Map<Individual, Double> selectSurvivors(Map<Individual, Double> previousPopulationFitness, Map<Individual, Double> offspringFitness);
}
