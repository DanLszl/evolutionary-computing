package algorithm.shocking;

public class ShockedAdaptiveTournamentSurvivalSelection extends ShockedAdaptiveTournamentParentSelection {
    public ShockedAdaptiveTournamentSurvivalSelection(int tournamentSizeStart, int tournamentSizeEnd, int tournamentSizeGenerations, int numberOfShocks, int stepsOnMaxSize) {
        super(tournamentSizeStart, tournamentSizeEnd, tournamentSizeGenerations, numberOfShocks, stepsOnMaxSize);
    }
}
