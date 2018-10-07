import algorithm.Individual;
import algorithm.Initialization;
import algorithm.Population;
import algorithm.mutation.UniformMutation;
import algorithm.parentselection.AllParentSelection;
import algorithm.parentselection.FitnessProportionalParentSelection;
import algorithm.parentselection.ParentSelection;
import algorithm.survivalselection.ReplaceAllSurvivalSelection;
import algorithm.survivalselection.SurvivorSelection;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;
import algorithm.recombination.DiscreteRecombination;
import algorithm.recombination.Recombination;
import algorithm.terminationcriteria.NoTerminationCriteria;
import algorithm.terminationcriteria.TerminationCriteria;

import java.util.Map;
import java.util.Random;
import java.util.Properties;

public class player58 implements ContestSubmission
{
	Random rnd_;
	ContestEvaluation evaluation_;
    private int evaluations_limit_;

	public static void main(String[] args) {
		System.out.println("Test");
	}

	public player58()
	{
		rnd_ = new Random();
	}
	
	public void setSeed(long seed)
	{
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation)
	{
		// Set evaluation problem used in the run
		evaluation_ = evaluation;
		
		// Get evaluation properties
		Properties props = evaluation.getProperties();
        // Get evaluation limit
        evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
        }
    }
    
	public void run()
	{
		// Run your algorithm here

		System.out.println(evaluations_limit_);

		int populationSize = 100;
		double probabilityOfMutation = 0;

		ParentSelection parentSelection = new AllParentSelection();
		UniformMutation mutation = new UniformMutation(probabilityOfMutation);
		Recombination recombination = new DiscreteRecombination();
		SurvivorSelection survivorSelection = new ReplaceAllSurvivalSelection();
		TerminationCriteria terminationCriteria = new NoTerminationCriteria();


		// init population
		Population previousPopulation = Initialization.initializeA(populationSize);

		// calculate fitness
		Map<Individual, Double> previousGenerationFitness = previousPopulation.evaluatePopulation(evaluation_);
		int evals = populationSize;

        while(evals < evaluations_limit_){

        	// Select parents
			Population parents = parentSelection.selectParents(previousGenerationFitness);
			// System.out.println(parents);
			// Apply crossover / mutation operators
			Population offspring = recombination.recombine(parents);
			// System.out.println(Integer.toString(parents.size()) + " " + Integer.toString(offspring.size()));
			Population mutatedOffspring = mutation.mutate(offspring);
			// Check fitness of unknown fuction
			Map<Individual, Double> offspringFitness = mutatedOffspring.evaluatePopulation(evaluation_);

			// Select survivors
			Map<Individual, Double> nextGenerationFitness = survivorSelection.selectSurvivors(previousGenerationFitness, offspringFitness);



			evals += nextGenerationFitness.size();

			previousGenerationFitness = nextGenerationFitness;


			if (terminationCriteria.shouldTerminate(nextGenerationFitness)) {
				break;
			}
        }

	}
}
