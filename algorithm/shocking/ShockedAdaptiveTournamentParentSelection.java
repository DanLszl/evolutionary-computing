package algorithm.shocking;

import algorithm.parentselection.AdaptiveTournamentParentSelection;

public class ShockedAdaptiveTournamentParentSelection extends AdaptiveTournamentParentSelection {

    int baseTournamentSizeGenerations;
    int numberOfShocks;
    int stepsOnMaxSize;

    int currentStepsOnMax = 0;

    public ShockedAdaptiveTournamentParentSelection(
            int tournamentSizeStart,
            int tournamentSizeEnd,
            int tournamentSizeGenerations,
            int numberOfShocks,
            int stepsOnMaxSize)
    {
        super(tournamentSizeStart, tournamentSizeEnd,
                tournamentSizeGenerations / numberOfShocks - stepsOnMaxSize);
        this.baseTournamentSizeGenerations = tournamentSizeGenerations;
        this.numberOfShocks = numberOfShocks;
        this.stepsOnMaxSize = stepsOnMaxSize;
    }

    @Override
    protected void updateTournamentSize() {
        System.out.println(tournamentSize);
        // increment until it is smaller than the max
        if (tournamentSize < tournamentSizeEnd) {

            super.updateTournamentSize();
            //System.out.println("After: " + Integer.toString(tournamentSize));
        } else if (tournamentSize == tournamentSizeEnd) {
            //System.out.println("We are on max: " + Integer.toString(tournamentSize));
            if (currentStepsOnMax < stepsOnMaxSize) {
                // spend some time on the max
                currentStepsOnMax+= 1;
            } else {
                // Introduce shock
                reset();
                currentStepsOnMax = 0;
            }
        }
    }
}
