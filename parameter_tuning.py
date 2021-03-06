# %%
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

selected_evaluation_function = b
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
population_sizes = [100, 200, 250]

# Recombination
## Choose blend alphas

recombination_type = 'discrete' # blend

if recombination_type == 'blend':
    blend_alpha = [0.3, 0.5, 0.7]

## Choose sigma threshold
sigma_thresholds = [0.0001, 0.001, 0.005, 0.01]

# ParentSelection
## Tournament
tournament_size_starts_ends = [(2, 12),
                               (2, 20),
                               (2, 30)]



### TODO need to calculate these somehow
generations_in_which_tournament_size_becomes_max = [50, 500, 5000]

# %%


testable_parameter_collection = [
                            [use_shocking_for_tournament],
                            [use_shocking_for_mutation],
                            population_sizes,
                            sigma_thresholds,
                            generations_in_which_tournament_size_becomes_max,]
parameter_names = [
                    'useShockingForTournament',
                    'useShockingForMutation',
                    'populationSize',
                    'sigmaThreshold',
                    'tournamentGenerations',
                    ]

if recombination_type == 'blend':
    testable_parameter_collection.append(blend_alpha)
    parameter_names.append(blend_alpha)

if use_shocking_for_mutation or use_shocking_for_tournament:
    testable_parameter_collection.append(shock_intervals)
    parameter_names.append('shockInterval')



testable_parameter_collection.append(tournament_size_starts_ends)
parameter_names.extend(['tournamentSizeStart',
                        'tournamentSizeEnd'])

parameters_values = itertools.product(
                            *testable_parameter_collection
                            )

# %%

parameters_values = [rest + [t_s_e[0], t_s_e[1]] for *rest, t_s_e in parameters_values]

parameter_names = [''.join(['-D', i]) for i in parameter_names]

# %%

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

# %%

individual_run_count = 8

results = []
i = 0
for command in commands:
    print(str(i) + '/' + str(len(commands)))
    i += 1

    individual_results = []
    for j in range(individual_run_count):
        print('\t' + str(j))
        result = subprocess.run(command, stdout=subprocess.PIPE, cwd='out/production/assignment/')
        result = ' '.join(command), parse_result(result.stdout.decode("utf-8"))
        individual_results.append(result)

    results.append(individual_results)

import pickle

with open(selected_evaluation_function + '.p', 'wb') as f:
    pickle.dump(results, f)


def dont_run_this():
    results

    ' '.join(commands[2])

    results[0][1]['data']

    results[0][0]
    command = results[0][0]
    result = subprocess.run(command.split(), stdout=subprocess.PIPE, cwd='out/production/assignment/')
    result = ' '.join(command), parse_result(result.stdout.decode("utf-8"))

    result.stdout

    import pandas as pd
    from io import StringIO

    a = pd.read_csv(StringIO(results[0][1]['data']))

    a.plot()

    dataframes = [pd.read_csv(StringIO(result[1]['data'])) for result in results]

    merged = pd.concat(dataframes, keys=range(len(dataframes)), axis=1)

    plot = merged.plot()
    fig = plot.get_figure()
    fig.savefig('10.png')
    # Find best score:
