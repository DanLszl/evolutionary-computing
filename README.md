# Evolutionary Computing Assignment

### NOTE TO TAs -- For evaluation testing please follow these steps:

### Important: this project was tested on linux and mac only with IntelliJ IDEA.

## Steps to run the code:
1. Install JDK
2. Install IntelliJ IDEA
3. Clone this repo
4. Open IDEA
5. In IDEA: (File &rarr;) Open &rarr; browse to the repo's folder (`ec-idea`) &rarr; OK
6. In IDEA: Build &rarr; Build Project
7. Navigate to the project-path/out/production/assignment/ folder in terminal
8. Run the following commands:

Bentcigar without shock:
```
java -DuseShockingForTournament=False -DuseShockingForMutation=False -DpopulationSize=200 -DsigmaThreshold=0.0001 -DtournamentGenerations=50 -DtournamentSizeStart=2 -DtournamentSizeEnd=20 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=BentCigarFunction -seed=1
```

Bentcigar with shock:
```
java -DuseShockingForTournament=True -DuseShockingForMutation=True -DpopulationSize=200 -DsigmaThreshold=0.0001 -DtournamentGenerations=50 -DshockPatience=1 -DtournamentSizeStart=2 -DtournamentSizeEnd=20 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=BentCigarFunction -seed=1
```

katsuura without shock:
```
java -DuseShockingForTournament=False -DuseShockingForMutation=False -DpopulationSize=100 -DsigmaThreshold=0.001 -DtournamentGenerations=500 -DtournamentSizeStart=2 -DtournamentSizeEnd=20 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=KatsuuraEvaluation -seed=1
```

katsuura with shock:
```
java -DuseShockingForTournament=True -DuseShockingForMutation=True -DpopulationSize=100 -DsigmaThreshold=0.001 -DtournamentGenerations=500 -DshockPatience=750 -DtournamentSizeStart=2 -DtournamentSizeEnd=20 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=KatsuuraEvaluation -seed=1
```

schaffers without shock:
```
java -DuseShockingForTournament=False -DuseShockingForMutation=False -DpopulationSize=250 -DsigmaThreshold=0.01 -DtournamentGenerations=50 -DtournamentSizeStart=2 -DtournamentSizeEnd=20 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=SchaffersEvaluation -seed=1
```

schaffers with shock:
```
java -DuseShockingForTournament=True -DuseShockingForMutation=True -DpopulationSize=250 -DsigmaThreshold=0.01 -DtournamentGenerations=50 -DshockPatience=50 -DtournamentSizeStart=2 -DtournamentSizeEnd=20 -Djava.library.path=. -jar testrun.jar -submission=player58 -evaluation=SchaffersEvaluation -seed=1
```
