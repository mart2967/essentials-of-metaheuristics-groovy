package populationMethods
import utility.GenomeFitnessPair

class MuPlusLambdaES {
    Integer numParents = 2
    Integer numChildren = 6
    
    def maximize(problem){
        assert numParents > 0 && numChildren > 0, "Number of parents and children must be positive"
        assert numChildren % numParents == 0, "Number of children must be a multiple of the number of parents"
        
        def individualArr = []

        numChildren.times {
            def p = problem.random()
            def q = problem.quality(p)
            individualArr.add(new GenomeFitnessPair(genome: p, fitness: q))
        }

        def best = individualArr[0]

        while(!problem.terminate(best) ){
            for (individual in individualArr) {
                if (individual.compareTo(best) > 0) {
                    best = individual
                }
            }

            individualArr = individualArr.sort{it.fitness}.reverse()[0..<numParents]

            for (i in 0..<numParents) {
                for (j in 0..<(numChildren / numParents)) {
                    def newProblem = problem.tweak(problem.copy(individualArr.get(i).genome))
                    individualArr.add( new GenomeFitnessPair(genome: newProblem, fitness: problem.quality(newProblem) ) )
                }
            }

        }
        return best
    }

    String toString() {
        "MuPlusLambdaES_" + numParents + "_" + numChildren
    }
}
