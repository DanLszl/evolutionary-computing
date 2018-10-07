package algorithm.parentselection;


import algorithm.Population;

public class AllParentSelection implements ParentSelection {

    @Override
    public Population selectParents(Population population) {
        return population.clone();
    }

}
