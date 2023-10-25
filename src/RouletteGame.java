public class RouletteGame {
    public static void main(String[] args) {
        int totalExperiments = 1000000;
        RouletteSimulation simulation = new RouletteSimulation(totalExperiments);
        simulation.runSimulation();
        simulation.printResults();
    }
}