package algorithm.mutation;


import algorithm.Population;

public interface Mutation {
    // Overwrites the population
    Population mutate(Population individual);
}
