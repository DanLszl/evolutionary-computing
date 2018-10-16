# Generate these somehow

-evaluation=SchaffersEvaluation -seed=1
program_parameters = dict()

parameters = [dict(dummy1=123, dummy2=456),
			  dict(dummy1=987, dummy2=654),
              ]


commands = [' '.join(['-D' + str(k) + '=' + str(v) for k, v in parameter.items()]) for parameter in parameters]

commands = [' '.join(['java', command]) for command in commands]

commands = [' '.join(['java', command]) for command in commands]

