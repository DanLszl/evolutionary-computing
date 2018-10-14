package algorithm.mutation;

import algorithm.Individual;
import algorithm.Population;
import java.lang.Math;
import java.util.Random;

public class SelfAdaptiveMutation extends Mutation {

    double threshold;
    double overallTau;
    double n;

    public SelfAdaptiveMutation(double thr, double n, double lB, double uB) {
        super(lB, uB);
        this.threshold = thr;
        this.n = n;
        this.overallTau = Math.pow(Math.sqrt(n), -1.0);
    }

    @Override
    public Population mutate(Population population) {
        Random rand = new Random();
        Population mutated = population.clone();

        for (Individual i : mutated.getIndividuals()) {
            double pOverall = rand.nextGaussian();

            for (int j = 0; j < i.genotypeLength(); j++) {

                double deltaSigma = Math.exp(overallTau*pOverall);
                double newSigma = checkSigma(i.getSigma(j) * deltaSigma);
                i.setSigma(j, newSigma);

                double deltaValue = rand.nextGaussian()*newSigma;
                double newValue = checkBoundaries(i.getAllele(j) + deltaValue);
                i.setAllele(j, newValue);
            }
        }
        return mutated;
    }

    public double checkSigma(double value) {
        if ( Math.abs(value) < threshold) {
            return value < 0 ? -threshold : threshold;
        } else {
            return value;
        }
    }
}
