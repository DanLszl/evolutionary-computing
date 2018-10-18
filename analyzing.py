import pickle

import pandas as pd


with open('bentcigar.p', 'rb') as f:
    raw_results = pickle.load(f)
len(raw_results)

len(raw_results[0])

# This is one parameter with 8 runs
raw_results[0]

# This is one run
raw_results[0][0]

# This is the command for this run
raw_results[0][0][0]

# This is the data for it
raw_results[0][0][1]

#
raw_results[0][0][1]['score']

# Average the scores
## Collect the scores
scores = [eight_runs[] for eight_runs in raw_results]

import numpy as np

# %%
score_avgs = []
score_std_devs = []
for eight_runs in raw_results:
    score_sum = 0
    scores = []
    score_count = len(eight_runs)
    for one_run in eight_runs:
        score = one_run[1]['score']
        scores.append(score)

    score_avg = sum(scores) / score_count
    score_std_dev = np.std(scores)

    score_avgs.append(score_avg)
    score_std_devs.append(score_std_dev)

# %%
score_avgs
score_std_devs


import matplotlib.pyplot as plt
%matplotlib inline

plt.plot(score_avgs, score_std_devs, 'o')



score_avgs = np.array(score_avgs)
score_std_devs = np.array(score_std_devs)



ind_avgs = np.argpartition(score_avgs, -20)[-20:]
ind_std_devs = np.argpartition(score_std_devs, 20)[:20]

ind_avgs
ind_std_devs

ind = np.intersect1d(ind_avgs, ind_std_devs)



ind
len(ind)

plt.plot(score_avgs[ind], score_std_devs[ind], 'o')


sorted_ind = np.argsort(score_avgs[ind])


plt.plot(score_avgs[sorted_ind], score_std_devs[sorted_ind])

sorted_ind[-1]
score_avgs[sorted_ind[-1]]
score_avgs[ind][0]





score_avgs[ind]

score_std_devs[ind]

>>> ind = np.argpartition(a, -4)[-4:]
>>> ind
array([1, 5, 8, 0])
>>> a[ind]
array([4, 9, 6, 9])
