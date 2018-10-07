package algorithm.terminationcriteria;


import algorithm.Population;


public class NoTerminationCriteria implements TerminationCriteria {

    @Override
    public boolean shouldTerminate(Population nextGenerationFitness) {
        return false;
    }
}
