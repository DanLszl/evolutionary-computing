package algorithm.parentselection;

import algorithm.Population;


public class AdaptiveTournamentParentSelection extends TournamentParentSelection {

    int tournamentSizeStart;
    protected int tournamentSizeEnd;
    protected int tournamentSizeGenerations;
    protected int generations = 0;


    public AdaptiveTournamentParentSelection(int tournamentSizeStart, int tournamentSizeEnd, int tournamentSizeGenerations) {
        super(tournamentSizeStart);
        this.tournamentSizeStart = tournamentSizeStart;
        this.tournamentSizeEnd = tournamentSizeEnd;
        this.tournamentSizeGenerations = tournamentSizeGenerations;

    }

    @Override
    public Population selectParents(Population previousPopulation) {
        updateTournamentSize();
        Population parents = super.selectParents(previousPopulation);
        return parents;
    }

    protected void updateTournamentSize() {
        tournamentSize = (generations * (tournamentSizeEnd-tournamentSizeStart)) / tournamentSizeGenerations + tournamentSizeStart;
        generations += 1;
        if (tournamentSize > tournamentSizeEnd) {
            tournamentSize = tournamentSizeEnd;
        }
    }

    public void reset() {
        tournamentSize = tournamentSizeStart;
        generations = 0;
    }
}
