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
        double firsShotProbability = 2.0 / 6.0;
        double secondShotProbabilityWithRotate = 2.0 / 6.0;
        double secondShotProbabilityWithoutRotate = 1.0 / 4.0; //после первой попытки выстрелить, если не крутить барабан, получается один смертельный вариант из четырех

        for (int i = 0; i < totalExperiments; i++) {
            boolean firstSurvival = random.nextDouble() > firsShotProbability;  // Вероятность первого выжить
            boolean secondSurvivalWithRotate = random.nextDouble() > secondShotProbabilityWithRotate;  // Вероятность второго выжить, если Крутим барабан
            boolean secondSurvival = random.nextDouble() > secondShotProbabilityWithoutRotate; // Вероятность второго выжить, если сразу стреляем

            if (firstSurvival) {
                firstPlayerSurvival++; // Первый игрок выжил
                if (secondSurvivalWithRotate) {
                    secondPlayerSurvivalRotate++; // Второй игрок выжил
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
