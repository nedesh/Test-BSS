import java.util.Scanner;

public class RouletteGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество экспериментов: ");
        int totalExperiments = scanner.nextInt();
        System.out.print("Крутить барабан (true/false): ");
        boolean useBarrelRotation = scanner.nextBoolean();
        RouletteSimulation simulation = new RouletteSimulation(totalExperiments);
        simulation.runSimulation(useBarrelRotation);
        simulation.printResults(useBarrelRotation);
    }
}