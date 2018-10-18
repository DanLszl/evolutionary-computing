package algorithm.statistics;

import algorithm.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineFitnessStatisticsPrinter {

    private final boolean printAll;
    List<Double> bestHistory = new ArrayList<>();
    List<Double> avgHistory = new ArrayList<>();
    List<Double> medianHistory = new ArrayList<>();
    List<Double> worstHistory = new ArrayList<>();

    public OnlineFitnessStatisticsPrinter(boolean printAll) {
        this.printAll = printAll;
        if (printAll) {
            System.out.println("best, worst, avg, median");
        }
    }

    public void printStats(Population generation) {
        if (printAll) {
            List<Double> fitnessValues = generation.getFitnessValues();

            Collections.sort(fitnessValues);

            double sum = 0;

            for (Double d : fitnessValues) {
                sum += d;
            }


            Double best = fitnessValues.get(fitnessValues.size() - 1);
            if (best.isNaN()) {
                System.out.println("FUCK!!!!");
            }
            Double worst = fitnessValues.get(0);
            Double avg = sum / fitnessValues.size();
            Double median = fitnessValues.get(fitnessValues.size() / 2);

            System.out.print(best); System.out.print(", ");
            System.out.print(worst); System.out.print(", ");
            System.out.print(avg); System.out.print(", ");
            System.out.println(median);

            worstHistory.add(worst);
            bestHistory.add(best);
            avgHistory.add(avg);
            medianHistory.add(median);
        }
    }
}
