package rouletteGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RouletteGame {
    public static void main(String[] args) {
        RouletteSimulation simulation = SpringApplication.run(RouletteGame.class, args).getBean(RouletteSimulation.class);
        simulation.startGame();
    }
}
