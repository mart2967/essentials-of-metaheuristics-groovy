package experiments

import applications.robocode.RobocodeProblem;
import populationMethods.MuPlusLambdaES

class RoboCodeExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 10) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0..<numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p)
                    println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"
                }
            }
        }
    }

    static main(args) {
        def searchers = [
            new MuPlusLambdaES()
        ]
        def problems = [
            new RobocodeProblem()
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems)
    }

}