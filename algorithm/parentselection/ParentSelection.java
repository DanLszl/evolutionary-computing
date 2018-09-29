package algorithm.parentselection;

import algorithm.Individual;
import algorithm.Population;

import java.util.Map;

public interface ParentSelection {
    Population selectParents(Map<Individual, Double> populationFitness);
}
