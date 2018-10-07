import algorithm.initialization.Initialization;
import algorithm.Population;
import algorithm.initialization.RandomInitialization;
import algorithm.mutation.UniformMutation;
import algorithm.mutation.NonUniformMutation;
import algorithm.parentselection.ParentSelection;
import algorithm.parentselection.TournamentParentSelection;
import algorithm.survivalselection.ReplaceAllSurvivalSelection;
import algorithm.survivalselection.SurvivorSelection;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;
import algorithm.recombination.DiscreteRecombination;
import algorithm.recombination.Recombination;
import algorithm.terminationcriteria.NoTerminationCriteria;
import algorithm.terminationcriteria.TerminationCriteria;
import algorithm.statistics.OnlineFitnessStatisticsPrinter;

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

		//OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter = new OnlineFitnessStatisticsPrinter();

		int populationSize = 100;

		//Mutation parameters
		double probabilityOfMutation = 0.5;
		double lowerBoundary = -5.0;
		double upperBoundary = 5.0;
		double sigma = 0.5;

		Initialization initialization = new RandomInitialization(populationSize);
		ParentSelection parentSelection = new TournamentParentSelection(20);
		NonUniformMutation mutation = new NonUniformMutation(sigma, lowerBoundary, upperBoundary);
		//UniformMutation mutation = new UniformMutation(probabilityOfMutation, lowerBoundary, upperBoundary);
		Recombination recombination = new DiscreteRecombination();
		SurvivorSelection survivorSelection = new ReplaceAllSurvivalSelection();
		TerminationCriteria terminationCriteria = new NoTerminationCriteria();


		// init population
		Population previousGeneration = initialization.initialize();

		// evaluate initial population
		previousGeneration.evaluate(evaluation_);
		int evals = populationSize;

        while(evals < evaluations_limit_){
			//onlineFitnessStatisticsPrinter.printStats(previousGeneration);
        	// Select parents
			Population parents = parentSelection.selectParents(previousGeneration);

			// Apply crossover / mutation operators
			Population offspring = recombination.recombine(parents);

			Population mutatedOffspring = mutation.mutate(offspring);
			// Evaluate fitness of the offspring population
			mutatedOffspring.evaluate(evaluation_);

			// Select survivors
			Population nextGeneration = survivorSelection.selectSurvivors(previousGeneration, mutatedOffspring);


			evals += nextGeneration.size();

			previousGeneration = nextGeneration;

			if (terminationCriteria.shouldTerminate(nextGeneration)) {
				break;
			}

        }

	}
}
