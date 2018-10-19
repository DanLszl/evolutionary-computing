package algorithm.statistics;

import java.util.ArrayList;
import java.util.List;

public class MovingAverage {
    int movingWindow;

    double rollingSum = 0;
    List<Double> values = new ArrayList<>();

    public MovingAverage(int movingWindow) {
        this.movingWindow = movingWindow;
    }

    public void add(double value) {
        rollingSum += value;
        values.add(value);

        if (values.size() > movingWindow) {
            rollingSum -= values.get(0);
            values.remove(0);
        }
    }

    public double get() {
        return values.size() > 0 ? rollingSum / values.size() : 0;
    }

}
