package algorithm.shocking;

import algorithm.Individual;
import algorithm.Population;
import algorithm.initialization.Initialization;
import algorithm.initialization.RandomInitialization;
import algorithm.mutation.SelfAdaptiveMutation;
import algorithm.statistics.OnlineFitnessStatisticsPrinter;

import java.util.Random;


public class DiversityBasedShockedSelfAdaptiveMutation extends SelfAdaptiveMutation {

    private final ShockChecker shockChecker;
    private OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter;
    double plateauThreshold;

    public DiversityBasedShockedSelfAdaptiveMutation(double thr, double n, double lB, double uB,
                                       ShockChecker shockChecker
                                       ) {
        super(thr, n, lB, uB);
        this.shockChecker = shockChecker;

    }

    @Override
    public Population mutate(Population population) {
        if (shockChecker.checkShock()) {
            // Shocking them

            Random rand = new Random();

//             Just replace all the sigmas with 1
            for (Individual i : population.getIndividuals()) {
                for (int j = 0; j < 10; j++) {
                    i.setSigma(j, 1);
                }

            }
        }
        return super.mutate(population);
    }

}
