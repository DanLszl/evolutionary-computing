package algorithm.parentselection;


import algorithm.Population;

public interface ParentSelection {
    Population selectParents(Population population);
}
