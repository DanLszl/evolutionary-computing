package algorithm.terminationcriteria;

import algorithm.Individual;

import java.util.Map;

public interface TerminationCriteria {
    boolean shouldTerminate(Map<Individual, Double> nextGenerationFitness);
}
