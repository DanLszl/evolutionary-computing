package algorithm.shocking;

import algorithm.Individual;
import algorithm.Population;
import algorithm.mutation.NonUniformMutation;
import algorithm.mutation.SelfAdaptiveMutation;

import java.util.Random;

public class ShockedSelfAdaptiveMutation extends SelfAdaptiveMutation {

    private int currentGeneration = 0;
    private int shockInterval;

    public ShockedSelfAdaptiveMutation(double thr, double n, double lB, double uB, int shockInterval) {
        super(thr, n, lB, uB);
        this.shockInterval = shockInterval;
    }

    @Override
    public Population mutate(Population population) {
        currentGeneration++;
        Random rand = new Random();
        if (currentGeneration % shockInterval == 0) {
            // Shocking them
            // Just replace all the sigmas with something random
            for (Individual i : population.getIndividuals()) {
                for (int j = 0; j < 10; j++) {
                    i.setSigma(j, rand.nextGaussian());
                }

            }
        }
        return super.mutate(population);
    }

}
