package algorithm;

import org.vu.contest.ContestEvaluation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {
    private List<Individual> individuals = new ArrayList<>();

    private Population(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public Population() {
        this(0);
    }

    public Population(int populationsize) {
        for (int i = 0; i < populationsize; i++) {
            individuals.add(new Individual());
        }
    }

    /**
     * Evaluates the individuals in the population. After this call every individuals fitness will be set
     * */
    public void evaluate(ContestEvaluation evaluation) {
        for (Individual i : individuals) {
            if (!i.isEvaluated()) {
                i.evaluate(evaluation);
            }
        }
    }

    @Override
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

    public List<Double> getFitnessValues() {
        List<Double> fitnessValues = new ArrayList<>();
        for (Individual i : individuals) {
            fitnessValues.add(i.getFitness());
        }
        return fitnessValues;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void add(Individual individual) {
        individuals.add(individual);
    }

    /**
     * Sort in descending order
     * */
    public void sort() {
        individuals.sort((a, b) -> {
            if (a.getFitness() < b.getFitness()) {
                return -1;
            } else if (a.getFitness() == b.getFitness()) {
                return 0;
            } else {
                return 1;
            }
        });
    }
}
