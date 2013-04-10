package applications.robocode


class RobocodeProblem {
    
    RobotBuilder robotBuilder = new RobotBuilder("templates/Toorkild.template")
    BattleRunner battleRunner = new BattleRunner("templates/battle.template")
    protected random = new java.util.Random()
    Integer individualCount = 0
    Integer evalCount = 0
    Integer maxIterations = 100
    public static final STDEV = 10
    
    def create() {
        return random()
    }
    
    def random() {
        individualCount++
        ['id' : individualCount,
            //'ramDistance' : 100 + random.nextGaussian()*STDEV,
            'offsetModifier': 0.8 + random.nextGaussian()]
    }
    
    def quality(individual) {
        evalCount++
        robotBuilder.buildJarFile(individual)
        battleRunner.buildBattleFile(individual['id'])
        def score = battleRunner.runBattle(individual['id'])
        println "quality of individual ${individual['id']}: ${score}" + individual
        return score
    }
    
    def copy(individual) {
        return individual.clone()
    }
    
    def tweak(individual) {
        individualCount++
        println 'tweaking'
        ['id' : individualCount,
            'offsetModifier': 0.8 + random.nextGaussian()]
    }
    
    def terminate(bestIndividualGFPair) {
        evalCount >= maxIterations
    }
    
    String toString() {
        return "RobocodeProblem"
        
    }
    
}