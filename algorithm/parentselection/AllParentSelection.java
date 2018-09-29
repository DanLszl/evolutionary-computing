package algorithm.parentselection;

import algorithm.Individual;
import algorithm.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AllParentSelection implements ParentSelection {


    @Override
    public Population selectParents(Map<Individual, Double> populationFitness) {
        List<Individual> population = new ArrayList<>();

        for (Individual i : populationFitness.keySet()) {
            population.add(i);
        }
        return new Population(population);
    }
}
