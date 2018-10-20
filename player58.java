import algorithm.diversity.PopulationDiversity;
import algorithm.initialization.Initialization;
import algorithm.Population;
import algorithm.initialization.RandomInitialization;
import algorithm.mutation.NonUniformMutation;

import algorithm.mutation.SelfAdaptiveMutation;
import algorithm.mutation.UniformMutation;

import algorithm.parentselection.*;
import algorithm.recombination.DiscreteRecombination;
import algorithm.shocking.*;
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



    // Parameters
	boolean useShockingForTournament;
	boolean useShockingForMutation;
	int populationSize;
	double sigmaThreshold;
	int tournamentGenerations;
	int tournamentSizeStart;
	int tournamentSizeEnd;
	int patience;


	public void setSchaffersParameters() {
		useShockingForTournament = true;
		useShockingForMutation = true;
		populationSize = 250;
		sigmaThreshold = 0.01;
		tournamentGenerations = 50;
		tournamentSizeStart = 2;
		tournamentSizeEnd = 20;
		patience = 50;
	}

	public void setKatsuuraParameters() {
		useShockingForTournament = true;
		useShockingForMutation = true;
		populationSize = 100;
		sigmaThreshold = 0.001;
		tournamentGenerations = 500;
		tournamentSizeStart = 2;
		tournamentSizeEnd = 20;
		patience = 750;
	}


	public void setBentCigarParameters() {
		useShockingForTournament = false;
		useShockingForMutation = false;
		populationSize = 200;
		sigmaThreshold = 0.0001;
		tournamentGenerations = 50;
		tournamentSizeStart = 2;
		tournamentSizeEnd = 20;
		patience = 1;
	}

	public void setDefaultParameters() {

	}

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
		System.out.println(isMultimodal);
		System.out.println(hasStructure);
		System.out.println(isSeparable);

		if (!isMultimodal && !hasStructure && !isSeparable) {
            setBentCigarParameters();
		} else if (isMultimodal && hasStructure && !isSeparable) {
			setSchaffersParameters();
		} else if (isMultimodal && !hasStructure && !isSeparable) {
            setKatsuuraParameters();
		} else {
            System.out.println("Shouldnt arrive here");
        }
    }


	// Run your algorithm here
	public void run()
	{
		/** Collect parameters */

		System.out.println("Evaluations limit: " + Integer.toString(evaluations_limit_));


		if (Parameters.getpopulationSize() != null) {
			populationSize = Parameters.getpopulationSize();
		}

		int generationCount = evaluations_limit_ / populationSize;
		int movingWindowSize = (int) (generationCount * 0.05);
        movingWindowSize = movingWindowSize < 5 ? 5 : movingWindowSize;



        boolean printStatistics = true;
        OnlineFitnessStatisticsPrinter onlineFitnessStatisticsPrinter = new OnlineFitnessStatisticsPrinter(printStatistics, movingWindowSize);

		double blendAlpha = Parameters.getblendAlpha() == null ? 0.3 : Parameters.getblendAlpha();

		//mutation parameters
		double probabilityOfMutation = 0.1;
		double sigma = 0.1;
		double lowerBoundary = -5.0;	// THIS IS NOT A PARAMETER!
		double upperBoundary = 5.0;		// THIS IS NOT A PARAMETER!

		if (Parameters.getsigmaThreshold() != null) {
			sigmaThreshold = Parameters.getsigmaThreshold();
		}
		double hardness = 10.0;		// THIS IS NOT A PARAMETER!


        // Linear tournament size parameters
		if (Parameters.gettournamentSizeStart() != null) {
			tournamentSizeStart = Parameters.gettournamentSizeStart();
		}

		if (Parameters.gettournamentSizeEnd() != null) {
			tournamentSizeEnd = Parameters.gettournamentSizeEnd();
		}

		if (Parameters.gettournamentGenerations() != null) {
			tournamentGenerations = Parameters.gettournamentGenerations();
		}

        int shockInterval = Parameters.getshockInterval() == null ? 1000 : Parameters.getshockInterval();




        // Initializing the algorithms components
		Initialization initialization = new RandomInitialization(populationSize);

		ParentSelection parentSelection = null;



        double plateauThreshold = 0.001;

        if(Parameters.getpatience() != null) {
			patience = Parameters.getpatience();
		}


        // Katsuura
//        int patience = 750;
//        patience = 75;
        // Schaffers
//        patience = 50;
        // Bentcigar
//        patience = 1;

        ShockChecker shockChecker = new ShockChecker(onlineFitnessStatisticsPrinter, plateauThreshold, patience);

		if (Parameters.getuseShockingForTournament() != null) {
			useShockingForTournament = Parameters.getuseShockingForTournament();
		}

		if (Parameters.getuseShockingForMutation() != null) {
			useShockingForMutation = Parameters.getuseShockingForMutation();
		}



        if (useShockingForTournament) {

//            System.out.println("HERE!!!");
            parentSelection = new DiversityBasedShockedTournamentSelection(
                    tournamentSizeStart,
                    tournamentSizeEnd,
                    tournamentGenerations,
                    shockChecker);

//            parentSelection = new ShockedAdaptiveTournamentParentSelection(
//                    tournamentSizeStart,
//                    tournamentSizeEnd,
//                    shockInterval
//            );
        } else {
            //System.out.println("No shocking");
            parentSelection = new AdaptiveTournamentParentSelection(
                    tournamentSizeStart,
                    tournamentSizeEnd,
                    tournamentGenerations
            );
        }


        SelfAdaptiveMutation mutation = null;

        if (useShockingForMutation) {

            mutation = new DiversityBasedShockedSelfAdaptiveMutation(
                    sigmaThreshold,
                    hardness,
                    lowerBoundary,
                    upperBoundary,
                    shockChecker);
//            mutation = new ShockedSelfAdaptiveMutation(threshold, hardness, lowerBoundary, upperBoundary, shockInterval);
        } else {
            //System.out.println("No shocking");
            mutation = new SelfAdaptiveMutation(sigmaThreshold,hardness,lowerBoundary,upperBoundary);
        }

        Recombination recombination = new DiscreteRecombination();
//		Recombination recombination = new BlendRecombination(blendAlpha);
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
            //"std dev: " +
//            System.out.print(PopulationDiversity.fitnessBasedDiversity(previousGeneration));
//            System.out.print(Double.toString(PopulationDiversity.SPD(previousGeneration)));
//            System.out.print(',');
//            System.out.print(onlineFitnessStatisticsPrinter.getFitnessStdDevDelta());
//            System.out.print(onlineFitnessStatisticsPrinter.getSPDDelta());
//            System.out.println(',');

//            System.out.println(generation++);
//
            onlineFitnessStatisticsPrinter.collectStats(previousGeneration);

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
