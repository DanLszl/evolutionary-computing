package algorithm.statistics;

import algorithm.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OnlineFitnessStatisticsPrinter {

    List<Double> bestHistory = new ArrayList<>();
    List<Double> avgHistory = new ArrayList<>();
    List<Double> medianHistory = new ArrayList<>();
    List<Double> worstHistory = new ArrayList<>();

    public OnlineFitnessStatisticsPrinter() {
        System.out.println("coloumns = ['best', 'worst', 'avg', 'median'");
        System.out.println("data = np.array([");
    }

    public void printStats(Population generation) {
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

        System.out.print("[");
        System.out.print(best); System.out.print(", ");
        System.out.print(worst); System.out.print(", ");
        System.out.print(avg); System.out.print(", ");
        System.out.print(median);
        System.out.println("],");


        worstHistory.add(worst);
        bestHistory.add(best);
        avgHistory.add(avg);
        medianHistory.add(median);
    }

    public void close() {
        System.out.println("])");
    }
}
