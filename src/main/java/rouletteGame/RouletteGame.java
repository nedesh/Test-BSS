package rouletteGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static rouletteGame.RouletteSimulation.startGame;

@SpringBootApplication
public class RouletteGame {
    public static void main(String[] args) {
        startGame(SpringApplication.run(RouletteGame.class, args).getBean(RouletteSimulation.class));
    }
}
