package rouletteGame;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class RouletteSimulation {
    private GameResult gameResult;
    @Value("${roulette.totalExperiments}")
    private int totalExperiments;
    @Value("${roulette.useBarrelRotation}")
    private boolean useBarrelRotation;
    private static final double FIRST_SHOT_PROBABILITY = 2.0 / 6.0;
    private static final double SECOND_SHOT_PROBABILITY_WITH_ROTATE = 2.0 / 6.0; //Вероятность смертельного выстрела становится исходной при прокрутке
    private static final double SECOND_SHOT_PROBABILITY_WITHOUT_ROTATE = 1.0 / 4.0; //после первой попытки выстрелить, если не крутить барабан, получается один смертельный вариант из четырех

    public void startGame() {
        runSimulation();
        printResults();
    }

    private void runSimulation() {
        Random random = new Random();
        double firstPlayerSurvival = 0;
        double secondPlayerSurvivalRotate = 0;
        double secondPlayerSurvivalShoot = 0;

        for (int i = 0; i < totalExperiments; i++) {
            boolean firstSurvival = random.nextDouble() > FIRST_SHOT_PROBABILITY;  // Вероятность первого выжить
            boolean secondSurvivalWithRotate = random.nextDouble() > SECOND_SHOT_PROBABILITY_WITH_ROTATE;  // Вероятность второго выжить, если Крутим барабан
            boolean secondSurvival = random.nextDouble() > SECOND_SHOT_PROBABILITY_WITHOUT_ROTATE; // Вероятность второго выжить, если сразу стреляем

            if (firstSurvival) {
                firstPlayerSurvival++; // Первый игрок выжил
                if (useBarrelRotation && secondSurvivalWithRotate) {
                    secondPlayerSurvivalRotate++;
                } else if (!useBarrelRotation && secondSurvival) {
                    secondPlayerSurvivalShoot++;
                }
            }
        }
        double firstPlayerSurvivalPercentage = (firstPlayerSurvival / totalExperiments) * 100;
        double secondPlayerSurvivalPercentage;
        if (useBarrelRotation) {
            secondPlayerSurvivalPercentage = (secondPlayerSurvivalRotate / totalExperiments) * 100;
        } else {
            secondPlayerSurvivalPercentage = (secondPlayerSurvivalShoot / totalExperiments) * 100;
        }

        gameResult = new GameResult(firstPlayerSurvivalPercentage, secondPlayerSurvivalPercentage);
    }

    private void printResults() {
        System.out.println("Total experiments: " + totalExperiments);
        System.out.println("First Player Survival Percentage: " + gameResult.getFirstPlayerSurvivalPercentage() + "%");
        if (useBarrelRotation) {
            System.out.println("Second Player Survival Percentage (Rotate): " + gameResult.getSecondPlayerSurvivalPercentage() + "%");
        } else {
            System.out.println("Second Player Survival Percentage (Shoot): " + gameResult.getSecondPlayerSurvivalPercentage() + "%");
        }
    }
}
