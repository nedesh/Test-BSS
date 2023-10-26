package rouletteGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RouletteGame {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RouletteGame.class, args);
        RouletteConfig rouletteConfig = context.getBean(RouletteConfig.class);

        RouletteSimulation simulation = new RouletteSimulation(rouletteConfig.getTotalExperiments());
        simulation.runSimulation(rouletteConfig.isUseBarrelRotation());
        simulation.printResults(rouletteConfig.isUseBarrelRotation());

        context.close();
    }
}