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
        //['id' : individualCount,
        //    'dangerZone' : 3 + random.nextGaussian()/4]
        
        ['id' : individualCount,
            'ramDistance' : 121.1,
            'offsetModifier': 0.8632457928499246,
            'bulletP': 4.50313077384111,
            'gunAdjust': 0.0005,
            'tempIndexMod': 11 ]
        /*
        ['id' : individualCount,
            'ramDistance' : 100 + random.nextGaussian()*30,
            'offsetModifier': 0.8 + random.nextGaussian()/2,
            'bulletP': 4 + random.nextGaussian()/2]
        */
    }
    
    def quality(individual) {
        evalCount++
        robotBuilder.buildJarFile(individual)
        battleRunner.buildBattleFile(individual['id'])
        def score = battleRunner.runBattle(individual['id'])
        println "quality of individual ${individual['id']}: ${score} data: " + individual
        return score
    }
    
    def copy(individual) {
        return individual.clone()
    }
    
    def tweak(individual) {
        individualCount++
        ['id' : individualCount,
            'ramDistance': individual['ramDistance'],
            'offsetModifier': individual['offsetModifier'],// + random.nextGaussian()/8,
            'bulletP': individual['bulletP'],
            'gunAdjust': individual['gunAdjust'] + random.nextGaussian()/100,
            'tempIndexMod': individual['tempIndexMod'] + random.nextGaussian()
        ]
        
    }
    
    def terminate(bestIndividualGFPair) {
        evalCount >= maxIterations
    }
    
    String toString() {
        return "RobocodeProblem"
        
    }
    
}
