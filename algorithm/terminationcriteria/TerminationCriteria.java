package algorithm.terminationcriteria;


import algorithm.Population;

public interface TerminationCriteria {
    boolean shouldTerminate(Population nextGenerationFitness);
}
