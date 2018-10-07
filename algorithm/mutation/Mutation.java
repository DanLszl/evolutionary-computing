package algorithm.mutation;


import algorithm.Population;

public abstract class Mutation {

    //Boundaries for the mutated genome values
    public double lowerBoundary;
    public double upperBoundary;

    public Mutation(double lB, double uB) {
        this.lowerBoundary = lB;
        this.upperBoundary = uB;
    }

    // Overwrites the population
    abstract Population mutate(Population population);

    public double checkBoundaries(double value) {
        if (value < lowerBoundary) {
            return lowerBoundary;
        } else if (value > upperBoundary) {
            return upperBoundary;
        } else {
            return value;
        }
    }
}
