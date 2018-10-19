package algorithm.statistics;

import algorithm.Population;
import algorithm.diversity.PopulationDiversity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineFitnessStatisticsPrinter {

    private final boolean printAll;
    List<Double> bestHistory = new ArrayList<>();
    List<Double> avgHistory = new ArrayList<>();
    List<Double> medianHistory = new ArrayList<>();
    List<Double> worstHistory = new ArrayList<>();
    List<Double> spdHistory = new ArrayList<>();
    List<Double> fitnessStdDevHistory = new ArrayList<>();

    private int movingWindowSize;

    MovingAverage movingSPD = new MovingAverage(movingWindowSize);
    MovingAverage movingFitnessStdDev = new MovingAverage(movingWindowSize);

    Double prevMovingAvgSPD = 0.0;
    Double currentMovingAvgSPD = 0.0;

    Double prevMovingFitnessStdDev = 0.0;
    Double currentMovingFitnessStdDev = 0.0;

    public OnlineFitnessStatisticsPrinter(boolean printAll, int movingWindowSize) {
        this.printAll = printAll;
        this.movingWindowSize = movingWindowSize;
        if (printAll) {
            System.out.println("best, worst, avg, spd, fitness_std_dev, median");
        }
    }

    public Double getFitnessStdDevDelta() {
        return currentMovingFitnessStdDev - prevMovingFitnessStdDev;
    }

    public Double getPrevMovingAvgSPD() {
        return currentMovingAvgSPD - currentMovingAvgSPD;
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
            Double worst = fitnessValues.get(0);
            Double avg = sum / fitnessValues.size();
            Double median = fitnessValues.get(fitnessValues.size() / 2);

            Double spd = PopulationDiversity.SPD(generation);
            Double fitnessStdDev = PopulationDiversity.std_dev(fitnessValues);

            prevMovingAvgSPD = currentMovingAvgSPD;
            movingSPD.add(spd);
            currentMovingAvgSPD = movingSPD.get();

            prevMovingFitnessStdDev = currentMovingFitnessStdDev;
            movingFitnessStdDev.add(fitnessStdDev);
            currentMovingFitnessStdDev = movingFitnessStdDev.get();

            System.out.print(best); System.out.print(", ");
            System.out.print(worst); System.out.print(", ");
            System.out.print(avg); System.out.print(", ");

            System.out.print(spd); System.out.print(", ");
            System.out.print(fitnessStdDev); System.out.print(", ");

            System.out.println(median);

            worstHistory.add(worst);
            bestHistory.add(best);
            avgHistory.add(avg);
            medianHistory.add(median);
            spdHistory.add(spd);
            fitnessStdDevHistory.add(fitnessStdDev);
        }
    }
}
