package algorithm;

import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Population {
    public List<Individual> individuals = new ArrayList<>();

    public Population(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public Population(int populationsize) {

        for (int i = 0; i < populationsize; i++) {
            individuals.add(new Individual());
        }
    }

    public Map<Individual, Double> evaluatePopulation(ContestEvaluation evaluation_) {
        Map<Individual, Double> pFitness = new HashMap<>();

        for (Individual i : individuals) {
            if (i.genotype == null) {
                System.out.println("What the heck");
            }
            double d = (double) evaluation_.evaluate(i.genotype);
            pFitness.put(i, d);
        }
        return pFitness;
    }

    public Population clone() {
        List<Individual> clonedIndividuals = new ArrayList<>();
        for (Individual i : individuals) {
            clonedIndividuals.add(new Individual(i));
        }
        return new Population(clonedIndividuals);
    }

    public int size() {
        return individuals.size();
    }

}
