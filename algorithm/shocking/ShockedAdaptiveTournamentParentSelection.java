package algorithm.shocking;

import algorithm.parentselection.AdaptiveTournamentParentSelection;

public class ShockedAdaptiveTournamentParentSelection extends AdaptiveTournamentParentSelection {

    public ShockedAdaptiveTournamentParentSelection(
            int tournamentSizeStart,
            int tournamentSizeEnd,
            int shockInterval)
    {
        super(tournamentSizeStart, tournamentSizeEnd, shockInterval);
    }

    @Override
    protected void updateTournamentSize() {
        if (generations > tournamentSizeGenerations) {
            reset(); // Introducing shocks
            System.out.println("Reset");
        } else {
            super.updateTournamentSize();
        }
    }
}
