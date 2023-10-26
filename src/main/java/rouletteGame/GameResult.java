package rouletteGame;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameResult {
    private final double firstPlayerSurvivalPercentage;
    private final double secondPlayerSurvivalPercentage;
}
