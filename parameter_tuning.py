import subprocess
import itertools
from matplotlib import pyplot as plt

def parse_result(result : str):
    #result = 'Evaluations limit: 100000\nherecomesthedata\nanotherline\nScore: 9.967134458442498\nRuntime: 1005ms\n}]'
    headline_end = result.find('\n')

    headline = result[:headline_end]

    data_beginning = headline_end+1
    data_end = result.rfind('Score')

    data = result[data_beginning:data_end]

    score = result[data_end:]

    return {
        'eval_limit': int(headline[len('Evaluations limit: '):]),
        'data': data,
        'score': float(score[len('Score: '):score.find('\n')])
    }



# Parameter tuning

# First select the function you want to run parameter tuning on (it can be more)

b = 'BentCigarFunction'
s = 'SchaffersEvaluation'
k = 'KatsuuraEvaluation'

selected_evaluation_function = k
# For parameter tuning the seed is going to be always the same
seed = 1

# Run separately for non-shocking to get optimal parameters_values,
# and then try shocking with those
use_shocking_for_tournament = False
use_shocking_for_mutation = False

if use_shocking_for_mutation or use_shocking_for_tournament:
    shock_intervals = [200, 213, 1234]

# Population initialization
## Choose population sizes
population_sizes = [100, 200, 400]

# Recombination
## Choose blend alphas
blend_alpha = [0.3, 0.5, 0.7]

## Choose sigma threshold
sigma_thresholds = [0.01, 0.1, 0.15]

# ParentSelection
## Tournament
tournament_size_starts_ends = [(2, 6),
                               (2, 12),
                               (2, 20)]

### TODO need to calculate these somehow
generations_in_which_tournament_size_becomes_max = [1000, 2000, 5000]

if use_shocking_for_mutation or use_shocking_for_tournament:
    parameters_values = itertools.product(
                                [use_shocking_for_tournament],
                                [use_shocking_for_mutation],
                                shock_intervals,
                                population_sizes,
                                blend_alpha,
                                sigma_thresholds,
                                generations_in_which_tournament_size_becomes_max,
                                tournament_size_starts_ends
                                )
    parameter_names = [
                    'useShockingForTournament',
                    'useShockingForMutation',
                    'shockInterval',
                    'populationSize',
                    'blendAlpha',
                    'sigmaThreshold',
                    'tournamentGenerations',
                    'tournamentSizeStart',
                    'tournamentSizeEnd']
else:
    parameters_values = itertools.product([use_shocking_for_tournament],
                                  [use_shocking_for_mutation],
                                  #shock_intervals,
                                  population_sizes,
                                  blend_alpha,
                                  sigma_thresholds,
                                  generations_in_which_tournament_size_becomes_max,
                                  tournament_size_starts_ends)
    parameter_names = [
                    'useShockingForTournament',
                    'useShockingForMutation',
                    #'shockInterval',
                    'populationSize',
                    'blendAlpha',
                    'sigmaThreshold',
                    'tournamentGenerations',
                    'tournamentSizeStart',
                    'tournamentSizeEnd']


parameters_values = [rest + [t_s_e[0], t_s_e[1]] for *rest, t_s_e in parameters_values]

parameter_names = [''.join(['-D', i]) for i in parameter_names]

parameters = []

for parameters_value in parameters_values:
    parameter = [''.join([i, '=', str(j)]) for i, j in zip(parameter_names, parameters_value)]
    parameters.append(parameter)


commands = [['java'] +
            parameter +
            ['-Djava.library.path=.',
             '-jar', 'testrun.jar',
             '-submission=player58',
             '-evaluation=' + selected_evaluation_function,
             '-seed=1']
            for parameter in parameters]

#commands = ['java -Ddummy=123 -Ddummy2=456 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=SchaffersEvaluation -seed=1'] # TODO


results = []
i = 0
for command in commands[:10]:
    print(str(i) + '/' + str(len(commands)))
    i += 1
    result = subprocess.run(command, stdout=subprocess.PIPE, cwd='out/production/assignment/')
    results.append((' '.join(command), parse_result(result.stdout.decode("utf-8"))))


results[0][1]['data']

import pandas as pd
from io import StringIO

a = pd.read_csv(StringIO(results[0][1]['data']))



dataframes = [pd.read_csv(StringIO(result[1]['data'])) for result in results]

merged = pd.concat(dataframes, keys=range(len(dataframes)), axis=1)

plot = merged.plot()
fig = plot.get_figure()
fig.savefig('10.png')

# Find best score:
