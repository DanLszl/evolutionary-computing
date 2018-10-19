package algorithm.diversity;

import algorithm.Individual;
import algorithm.Population;

import java.util.List;

public class PopulationDiversity {

    public static double euclidean(Individual a, Individual b) {
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Math.pow(a.getAllele(i) - b.getAllele(i), 2);
        }
        return Math.sqrt(sum);
    }

    public static Individual sum(Population population) {
        Individual sum = new Individual();
        for (int i = 0; i < 10; i++) {
            sum.setAllele(i, 0.0);
        }
        for (Individual i : population.getIndividuals()) {
            for (int j = 0; j < 10; j++) {
                sum.setAllele(j, sum.getAllele(j) + i.getAllele(j));
            }
        }
        return sum;
    }

    public static Individual average(Population population) {
        Individual sum = sum(population);

        int size = population.size();
        Individual avg = new Individual();

        for (int i = 0; i < 10; i++) {
            avg.setAllele(i, sum.getAllele(i) / size);
        }
        return avg;
    }


    public static double SPD(Population population) {
        Individual avg = average(population);
        double sum = 0.0;
        for (Individual i :
                population.getIndividuals()) {
            double d = euclidean(avg, i);
            sum += d;
        }

        return sum;
    }

    public static double std_dev(List<Double> numbers) {
        double sum = 0.0;
        double std = 0.0;

        for(Double number : numbers) {
            sum += number;
        }

        double mean = sum/numbers.size();

        for(Double number: numbers) {
            std += Math.pow(number - mean, 2);
        }

        return Math.sqrt(std/numbers.size());
    }

    public static double fitnessBasedDiversity(Population population) {
        return std_dev(population.getFitnessValues());
    }
}
