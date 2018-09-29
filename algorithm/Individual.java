package algorithm;

public class Individual {
    public double[] genotype = new double[10];
    // TODO Refactor this
    //double fitness;
    //boolean evaluated = false;

    public Individual() {
        for (int i = 0; i < 10; i++) {
            double r = Math.random();
            genotype[i] = (r *10) - 5;
        }
    }


    public Individual(Individual other) {
        for (int i = 0; i < 10; i++) {
            this.genotype[i] = other.genotype[i];
        }
    }

}
