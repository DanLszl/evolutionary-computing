import algorithm.initialization.Initialization;
import algorithm.Population;
import algorithm.initialization.RandomInitialization;
import algorithm.mutation.NonUniformMutation;

import algorithm.mutation.SelfAdaptiveMutation;
import algorithm.mutation.UniformMutation;

import algorithm.parentselection.*;
import algorithm.shocking.ShockedAdaptiveTournamentParentSelection;
import algorithm.shocking.ShockedSelfAdaptiveMutation;
import algorithm.survivalselection.ReplaceAllSurvivalSelection;
import algorithm.survivalselection.SurvivorSelection;
import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;
import algorithm.recombination.BlendRecombination;
import algorithm.recombination.Recombination;
import algorithm.terminationcriteria.NoTerminationCriteria;
import algorithm.terminationcriteria.TerminationCriteria;
import algorithm.statistics.OnlineFitnessStatisticsPrinter;
import parametertuning.Parameters;


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


	// Run your algorithm here
	public void run()
	{
		/** Collect parameters */

		System.out.println("Evaluations limit: " + Integer.toString(evaluations_limit_));

        boolean printStatistics = false;
		OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter = new OnlineFitnessStatisticsPrinter();	// TODO refactor to csv

		int populationSize = Parameters.getpopulationSize() == null ? 100 : Parameters.getpopulationSize();
		double blendAlpha = Parameters.getblendAlpha() == null ? 0.3 : Parameters.getblendAlpha();

		//mutation parameters
		double probabilityOfMutation = 0.1;
		double sigma = 0.1;
		double lowerBoundary = -5.0;	// THIS IS NOT A PARAMETER!
		double upperBoundary = 5.0;		// THIS IS NOT A PARAMETER!
		double threshold = Parameters.getsigmaThreshold() == null ? 0.001 : Parameters.getsigmaThreshold();
		double hardness = 10.0;		// THIS IS NOT A PARAMETER!
        

        // Linear tournament size parameters
        int tournamentSizeStart = Parameters.gettournamentSizeStart() == null ? 5 : Parameters.gettournamentSizeStart();
        int tournamentSizeEnd = Parameters.gettournamentSizeEnd() == null ? 25 : Parameters.gettournamentSizeEnd();
        int tournamentSizeGenerations = Parameters.gettournamentGenerations() == null ? 200 : Parameters.gettournamentGenerations();
        int shockInterval = Parameters.getshockInterval() == null ? 40 : Parameters.getshockInterval();




        // Initializing the algorithms components
		Initialization initialization = new RandomInitialization(populationSize);

		ParentSelection parentSelection = null;

		Boolean flag = Parameters.getuseShockingForTournament();
        if (flag == null || flag == true) {
            parentSelection = new ShockedAdaptiveTournamentParentSelection(
                    tournamentSizeStart,
                    tournamentSizeEnd,
                    shockInterval
            );
        } else {
            parentSelection = new AdaptiveTournamentParentSelection(
                    tournamentSizeStart,
                    tournamentSizeEnd,
                    tournamentSizeGenerations
            );
        }



		// UniformMutation mutation = new UniformMutation(probabilityOfMutation,lowerBoundary,upperBoundary);
		// NonUniformMutation mutation = new NonUniformMutation(sigma,lowerBoundary,upperBoundary);
        SelfAdaptiveMutation mutation = null;
        flag = Parameters.getuseShockingForMutation();
        if (flag == null || flag == true) {
            mutation = new ShockedSelfAdaptiveMutation(
                    threshold,
                    hardness,
                    lowerBoundary,
                    upperBoundary,
                    shockInterval
            );
        } else {
            mutation = new SelfAdaptiveMutation(threshold,hardness,lowerBoundary,upperBoundary);
        }

		Recombination recombination = new BlendRecombination(blendAlpha);
		SurvivorSelection survivorSelection = new ReplaceAllSurvivalSelection();
		TerminationCriteria terminationCriteria = new NoTerminationCriteria();



		/** The algorithm itself */

		// initial population
		Population previousGeneration = initialization.initialize();

		// evaluate initial population
		previousGeneration.evaluate(evaluation_);
		int evals = populationSize;

        int generation = 0;
        while(evals < evaluations_limit_){
            // System.out.println(generation++);

			// onlineFitnessStatisticsPrinter.printStats(previousGeneration);

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
