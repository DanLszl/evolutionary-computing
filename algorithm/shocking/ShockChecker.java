package algorithm.shocking;

import algorithm.statistics.OnlineFitnessStatisticsPrinter;

public class ShockChecker {

    OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter;
    double plateauThreshold;
    int patience;
    int currentPlateu = 0;

    public ShockChecker(OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter, double plateauThreshold, int patience) {
        this.onlineFitnessStatisticsPrinter = onlineFitnessStatisticsPrinter;
        this.plateauThreshold = plateauThreshold;
        this.patience = patience;
    }


    public boolean checkShock() {

        boolean flag = onlineFitnessStatisticsPrinter.getFitnessStdDevDelta() < plateauThreshold
                && onlineFitnessStatisticsPrinter.getSPDDelta() < plateauThreshold;

        if (flag) {
            currentPlateu++;
            if (currentPlateu > patience) {
                currentPlateu = 0;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
