package algorithm;

import org.vu.contest.ContestEvaluation;

import java.util.Random;

public class Individual implements Comparable<Individual> {
    private double[] genotype = new double[10];
    private double[] stepsize = new double[10];
    private Double fitness = null;

    public Individual() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            double r = rand.nextDouble();
            genotype[i] = (r * 10) - 5;

            stepsize[i] = rand.nextGaussian();

        }
    }

    public Individual(Individual other) {
        for (int i = 0; i < 10; i++) {
            this.genotype[i] = other.genotype[i];
            this.stepsize[i] = other.stepsize[i];
        }
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

    public double getSigma(int i) { return stepsize[i]; }

    public void setSigma(int i, double value) {
        stepsize[i] = value;
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

    @Override
    public int compareTo(Individual compareIndividual) {
        double compareQuantity = ((Individual) compareIndividual).getFitness();

        //ascending order
        return(this.fitness < compareQuantity ? -1 : (this.fitness == compareQuantity ? 0 : 1));
    }
}
