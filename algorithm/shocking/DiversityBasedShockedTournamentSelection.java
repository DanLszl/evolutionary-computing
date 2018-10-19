package algorithm.shocking;

import algorithm.parentselection.AdaptiveTournamentParentSelection;
import algorithm.statistics.OnlineFitnessStatisticsPrinter;

public class DiversityBasedShockedTournamentSelection extends AdaptiveTournamentParentSelection {

    private final ShockChecker shockChecker;
    OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter;
    double plateauThreshold;

    public DiversityBasedShockedTournamentSelection(int tournamentSizeStart,
                                                    int tournamentSizeEnd,
                                                    int tournamentSizeGenerations,
                                                    ShockChecker shockChecker) {
        super(tournamentSizeStart, tournamentSizeEnd, tournamentSizeGenerations);
        this.shockChecker = shockChecker;
        this.onlineFitnessStatisticsPrinter = onlineFitnessStatisticsPrinter;
        this.plateauThreshold = plateauThreshold;
    }

    @Override
    protected void updateTournamentSize() {
        if (shockChecker.checkShock()) {
            // We are on a plateau, so introduce a shock
            System.out.println("Reset was called");
            reset();
        } else {
            super.updateTournamentSize();
        }
    }
}
