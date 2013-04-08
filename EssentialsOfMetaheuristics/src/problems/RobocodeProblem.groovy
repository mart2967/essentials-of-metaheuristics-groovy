package problems

class RobocodeProblem {
    
    protected random = new java.util.Random()
    Integer individualCount = 0
    Integer evalCount = 0
    Integer maxIterations = 1000
    
    def create() {
        return random()
    }
    
    def random() {
        individualCount++
        []
    }
    
    def quality(individual) {
        return 0
    }
    
    def copy(individual) {
        return individual.clone()
    }
    
    def tweak(individual) {
        return individual
    }
    
    def terminate(bestIndividual, bestQuality) {
        evalCount >= maxIterations
    }
    
}