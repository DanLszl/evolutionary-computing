package algorithm.terminationcriteria;

import algorithm.Individual;

import java.util.Map;

public class NoTerminationCriteria implements TerminationCriteria {

    @Override
    public boolean shouldTerminate(Map<Individual, Double> nextGenerationFitness) {
        return false;
    }
}
