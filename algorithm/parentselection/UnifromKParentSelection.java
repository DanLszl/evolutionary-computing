package algorithm.parentselection;

import algorithm.Population;

import java.util.Random;

public class UnifromKParentSelection implements ParentSelection {

    @Override
    public Population selectParents(Population population) {

        Random rand = new Random();
        Population parents = new Population(0);

        for (int i = 0; i < 50; i++) {
            int idx = rand.nextInt(population.size());
            System.out.println(idx);
            parents.add(population.getIndividual(idx));
        }
        
        return parents;
    }

}
