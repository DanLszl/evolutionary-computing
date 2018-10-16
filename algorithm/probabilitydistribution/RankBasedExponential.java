package algorithm.probabilitydistribution;

import java.util.ArrayList;
import java.util.List;

public class RankBasedExponential implements RankBased {

    @Override
    public ProbabilityDistribution getProbabilites(int populationSize) {
        List<Double> probabilities = new ArrayList<>();
        double sum = 0.0;
        for (int i = 0; i < populationSize; i++) {
            double next = 1.0 - Math.exp(-i);
            sum += next;
            probabilities.add(next);
        }

        for (int i = 0; i < populationSize; i++) {
            probabilities.set(i, probabilities.get(i) / sum);
        }

        return new ProbabilityDistribution(probabilities);
    }

}
