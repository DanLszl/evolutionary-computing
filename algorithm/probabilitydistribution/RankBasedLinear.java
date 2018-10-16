package algorithm.probabilitydistribution;


import java.util.*;


public class RankBasedLinear implements RankBased {

    private double s;

    public RankBasedLinear(double s) {
        this.s = s;
    }

    @Override
    public ProbabilityDistribution getProbabilites(int populationSize) {
        List<Double> probabilities = new ArrayList<>();

        int mu = populationSize;
        for (int i = 0; i < mu; i++) {
            probabilities.add((2-s)/mu + 2*i*(s-1)/(mu*(mu-1)));
        }

        return new ProbabilityDistribution(probabilities);
    }
}
