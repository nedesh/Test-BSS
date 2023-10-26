package rouletteGame;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RouletteSimulation {
    private final RouletteConfig rouletteConfig;
    private double firstPlayerSurvival;
    private double secondPlayerSurvivalRotate;
    private double secondPlayerSurvivalShoot;

    private void runSimulation() {
        int totalExperiments = rouletteConfig.getTotalExperiments();
        boolean useBarrelRotation = rouletteConfig.isUseBarrelRotation();
        Random random = new Random();
        double firsShotProbability = 2.0 / 6.0;
        double secondShotProbabilityWithRotate = 2.0 / 6.0; //Вероятность смертельного выстрела становится исходной при прокрутке
        double secondShotProbabilityWithoutRotate = 1.0 / 4.0; //после первой попытки выстрелить, если не крутить барабан, получается один смертельный вариант из четырех

        for (int i = 0; i < totalExperiments; i++) {
            boolean firstSurvival = random.nextDouble() > firsShotProbability;  // Вероятность первого выжить
            boolean secondSurvivalWithRotate = random.nextDouble() > secondShotProbabilityWithRotate;  // Вероятность второго выжить, если Крутим барабан
            boolean secondSurvival = random.nextDouble() > secondShotProbabilityWithoutRotate; // Вероятность второго выжить, если сразу стреляем

            if (firstSurvival) {
                firstPlayerSurvival++; // Первый игрок выжил
                if (useBarrelRotation && secondSurvivalWithRotate){
                    secondPlayerSurvivalRotate++;
                } else if (!useBarrelRotation && secondSurvival) {
                    secondPlayerSurvivalShoot++;
                }
            }
        }
    }

    private void printResults() {
        int totalExperiments = rouletteConfig.getTotalExperiments();
        boolean useBarrelRotation = rouletteConfig.isUseBarrelRotation();

        System.out.println("Total experiments: " + totalExperiments);
        System.out.println("First Player Survival Percentage: " + (firstPlayerSurvival / totalExperiments) * 100 + "%");
        if(useBarrelRotation) {
            System.out.println("Second Player Survival Percentage (Rotate): " + (secondPlayerSurvivalRotate / totalExperiments) * 100 + "%");
        } else {
            System.out.println("Second Player Survival Percentage (Shoot): " + (secondPlayerSurvivalShoot / totalExperiments) * 100 + "%");
        }
    }

    public static void startGame(RouletteSimulation simulation) {
        simulation.runSimulation();
        simulation.printResults();
    }
}
