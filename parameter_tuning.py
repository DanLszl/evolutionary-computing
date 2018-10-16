import subprocess
import itertools


# Parameter tuning

# First select the function you want to run parameter tuning on (it can be more)

b = 'bentcigar'
s = 'schaffers'
k = 'katsuura'

selected_evaluation_functions = [b,s,k]

# For parameter tuning the seed is going to be always the same
seed = 1

# Run separately for non-shocking to get optimal parameters,
# and then try shocking with those
use_shocking_for_tournament = False
use_shocking_for_mutation = False

shock_intervals = [50, 100, 200]

# Population initialization
## Choose population sizes
population_sizes = [50, 100, 500]

# Recombination
## Choose blend alphas
blend_alpha = [0.3, 0.5, 0.7]

## Choose sigma threshold
sigma_thresholds = [0.001, 0.01, 0.1]

# ParentSelection
## Tournament
tournament_size_starts_ends =   [(3, 25),
                                 (2, 50),
                                 (10, 40)]

### TODO need to calculate these somehow
generations_in_which_tournament_size_becomes_max = [3000, 6000, 10000]



parameters = itertools.product([use_shocking_for_tournament],
                                  [use_shocking_for_mutation],
                                  shock_intervals,
                                  population_sizes,
                                  blend_alpha,
                                  sigma_thresholds,
                                  generations_in_which_tournament_size_becomes_max,
                                  tournament_size_starts_ends)

parameters = [rest + [t_s_e[0], t_s_e[1]] for *rest, t_s_e in parameters]

parameter_names = ['useShockingForTournament',
                   'useShockingForMutation',
                   'shockInterval',
                   'populationSize',
                   'blendAlpha',
                   'sigmaThreshold',
                   'tournamentGenerations',
                   'tournamentSizeStart',
                   'tournamentSizeEnd']

parameter_names = [''.join(['-D', i]) for i in parameter_names]

commands = []

for parameter in parameters:
    print(parameter)

    command = [''.join([i, '=', str(j)]) for i, j in zip(parameter_names, parameter)]
    print(command)
    commands.append(command)

commands = ['java -Ddummy=123 -Ddummy2=456 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=SchaffersEvaluation -seed=1'] # TODO

results = []

for command in commands:
    result = subprocess.run(command.split(), stdout=subprocess.PIPE, cwd='out/production/assignment/')
    results.append({command: result.stdout.decode("utf-8")})

print(results)


