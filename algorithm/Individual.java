package algorithm;

import org.vu.contest.ContestEvaluation;

import java.util.Random;

public class Individual {
    private double[] genotype = new double[10];
    private Double stepsize = null;
    private Double fitness = null;

    public Individual() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            double r = rand.nextDouble();
            genotype[i] = (r * 10) - 5;
        }

        stepsize = Math.abs(rand.nextGaussian());
//        stepsize = 1.0;
    }

    public Individual(Individual other) {
        for (int i = 0; i < 10; i++) {
            this.genotype[i] = other.genotype[i];
        }
        this.stepsize = other.stepsize;
    }

    public void evaluate(ContestEvaluation evaluation) {
        fitness = (double) evaluation.evaluate(genotype);
    }

    public boolean isEvaluated() {
        return fitness != null;
    }

    public double getAllele(int i) {
        return genotype[i];
    }

    public void setAllele(int i, double value) {
        genotype[i] = value;
        fitness = null;
    }

    public double getSigma() { return stepsize; }

    public void setSigma(double value) {
        stepsize = value;
    }

    public int genotypeLength() {
        return genotype.length;
    }

    public Double getFitness() {
        if (fitness != null) {
            return fitness;
        } else {
            throw new NullPointerException("The Individual was not evaluated (fitness is not known)");
        }
    }
}
