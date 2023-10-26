package rouletteGame;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RouletteConfig {
    @Value("${roulette.totalExperiments}")
    private int totalExperiments;
    @Value("${roulette.useBarrelRotation}")
    private boolean useBarrelRotation;
}
