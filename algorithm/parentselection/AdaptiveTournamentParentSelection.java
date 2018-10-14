package algorithm.parentselection;

import algorithm.Population;

public class AdaptiveTournamentParentSelection extends TournamentParentSelection implements AdaptiveSelection {

    int tournamentSizeStart;
    int tournamentSizeEnd;
    int tournamentSizeGenerations;
    int generations = 0;


    public AdaptiveTournamentParentSelection(int tournamentSizeStart, int tournamentSizeEnd, int tournamentSizeGenerations) {
        super(tournamentSizeStart);
        this.tournamentSizeStart = tournamentSizeStart;
        this.tournamentSizeEnd = tournamentSizeEnd;
        this.tournamentSizeGenerations = tournamentSizeGenerations;

    }

    @Override
    public Population selectParents(Population previousPopulation) {
        updateTournamentSize();
        return super.selectParents(previousPopulation);
    }



    protected void updateTournamentSize() {
        tournamentSize = (generations  * (tournamentSizeEnd - tournamentSizeStart)) / tournamentSizeGenerations + tournamentSizeStart;

        if (tournamentSize > tournamentSizeEnd) {
            tournamentSize = tournamentSizeEnd;
        }
        generations += 1;

    }

    @Override
    public void reset() {
        tournamentSize = tournamentSizeStart;
        generations = 0;
        // System.out.println("Resetting, generations:" + Integer.toString(generations) + "Tournament size: " + Integer.toString(tournamentSizeEnd));
    }
}
