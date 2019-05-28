package algorithms;

public class TSP {
	
	public static int maxGenerations = 3000;
	
	public static void main(String[] args) {
		int numCities = 100;
		City cities[] = new City[numCities];
		for (int cityIndex = 0; cityIndex < numCities; cityIndex++) {
			int xPos = (int) (100 * Math.random());
			int yPos = (int) (100 * Math.random());
			cities[cityIndex] = new City(xPos, yPos);
		}

		 GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.002, 0.9, 5, 10);
		 Population population = ga.initPopulation(cities.length);
		 
		 int generation = 1;
		 ga.evalPopulation(population, cities);
		 
		 while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
			// TODO: Print fittest individual from population
			Route fittest = new Route(population.getFittest(0), cities);
			System.out.println("Generation: " + generation + ". Minimal distance: " + fittest.getDistance() + ". Chromosome: " + population.getFittest(0).toString());
			// TODO: Apply crossover
			population = ga.crossoverPopulation(population);
			// TODO: Apply mutation
			population = ga.mutatePopulation(population);
			// TODO: Evaluate population
			ga.evalPopulation(population, cities);
			// Increment the current generation 
			generation++;
			
		 }
		 
	}

}
