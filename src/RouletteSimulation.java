import java.util.Random;

public class RouletteSimulation {
    private int totalExperiments;
    private double firstPlayerSurvival;
    private double secondPlayerSurvivalRotate;
    private double secondPlayerSurvivalShoot;

    public RouletteSimulation(int totalExperiments) {
        this.totalExperiments = totalExperiments;
    }

    public void runSimulation() {
        Random random = new Random();
        double shotProbabilityWithRotate = 2.0 / 6.0;
        double shotProbability = 1.0 / 4.0;

        for (int i = 0; i < totalExperiments; i++) {
            boolean firstSurvival = random.nextDouble() > shotProbabilityWithRotate;
            boolean secondSurvivalWithRotate = random.nextDouble() > shotProbabilityWithRotate;
            boolean secondSurvival = random.nextDouble() > shotProbability;

            if (firstSurvival) {
                firstPlayerSurvival++;
                if (secondSurvivalWithRotate) {
                    secondPlayerSurvivalRotate++;
                }
                if (secondSurvival) {
                    secondPlayerSurvivalShoot++;
                }
            }
        }
    }

    public void printResults() {
        double firstPlayerSurvivalPercentage = (firstPlayerSurvival / totalExperiments) * 100;
        double secondPlayerSurvivalRotatePercentage = (secondPlayerSurvivalRotate / totalExperiments) * 100;
        double secondPlayerSurvivalShootPercentage = (secondPlayerSurvivalShoot / totalExperiments) * 100;

        System.out.println("Total experiments: " + totalExperiments);
        System.out.println("First Player Survival Percentage: " + firstPlayerSurvivalPercentage + "%");
        System.out.println("Second Player Survival Percentage (Rotate): " + secondPlayerSurvivalRotatePercentage + "%");
        System.out.println("Second Player Survival Percentage (Shoot): " + secondPlayerSurvivalShootPercentage + "%");
    }
}
