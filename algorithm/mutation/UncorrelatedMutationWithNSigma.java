package algorithm.mutation;

import algorithm.Population;
import java.lang.Math;

public class UncorrelatedMutationWithNSigma extends Mutation {

    double threshold;
    double coordinateTau;
    double overallTau;

    public UncorrelatedMutationWithNSigma(double thr, double lB, double uB) {
        super(lB, uB);
        this.threshold = thr;
        //TODO how to get n?
        double n = 10.0;
        this.coordinateTau = Math.pow(Math.sqrt(2 * n), -1.0);
        this.overallTau = Math.pow(Math.sqrt(2 * Math.sqrt(n)), -1.0);
    }

    @Override
    Population mutate(Population individual) {
        return null;
    }
}
