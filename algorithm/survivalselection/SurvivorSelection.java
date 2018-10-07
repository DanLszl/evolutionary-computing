package algorithm.survivalselection;

import algorithm.Population;

public interface SurvivorSelection {
    Population selectSurvivors(Population previousPopulation, Population offspring);
}
