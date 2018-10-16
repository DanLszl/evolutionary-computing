package algorithm.probabilitydistribution;

import java.util.List;
import java.util.Random;

public class ProbabilityDistribution {

    Random rand = new Random();
    private List<Double> probabilities;


    public ProbabilityDistribution(List<Double> probabilities) {
        this.probabilities = probabilities;
    }

    public Integer sample() {
        double point = rand.nextDouble();

        double sumSoFar = 0.0;
        for (int i = 0; i < probabilities.size(); i++) {
            sumSoFar += probabilities.get(i);
            if (point < sumSoFar) {
                return i;
            }
        }
        return probabilities.size()-1;

    }

}
