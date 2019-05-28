package algorithms;

import java.util.Arrays;

public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    protected int tournamentSize;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize) {
        this.tournamentSize = tournamentSize;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }

    public Population initPopulation(int chromosomeSize) {
        Population population = new Population(populationSize, chromosomeSize);
        return population;
    }

    
	public double calcFitness(Individual individual, City cities[]) { 
		Route route = new Route(individual,cities); 
		double fitness = 1 / route.getDistance();
		individual.setFitness(fitness); 
		return fitness;
	}
	
	public void evalPopulation(Population population, City cities[]) {
		double populationFitness = 0;
		for (Individual individual : population.getIndividuals()) {
			populationFitness += calcFitness(individual, cities);
		}
		double avgFitness = populationFitness / population.size();
		population.setPopulationFitness(avgFitness);
	}

    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }

    public Individual selectParent(Population population) {
        Population tournament = new Population(tournamentSize); 
        population.shuffle();
        for (int i = 0; i < tournamentSize; i++) {
            Individual tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i, tournamentIndividual);
        }
        return tournament.getFittest(0);
    }

    public Population crossoverPopulation(Population population) {
		Population newPopulation = new Population(population.size());
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual parent1 = population.getFittest(populationIndex);
			if (crossoverRate > Math.random() && populationIndex >= elitismCount) {
				Individual parent2 = selectParent(population);

				int offspringChromosome[] = new int[parent1.getChromosomeLength()];
				Arrays.fill(offspringChromosome, -1);
				Individual offspring = new Individual(offspringChromosome);
				
				int substrPos1 = (int) (Math.random() * parent1.getChromosomeLength());
				int substrPos2 = (int) (Math.random() * parent1.getChromosomeLength());
				final int startSubstr = Math.min(substrPos1, substrPos2);
				final int endSubstr = Math.max(substrPos1, substrPos2);

				for (int i = startSubstr; i < endSubstr; i++) {
					offspring.setGene(i, parent1.getGene(i));
				}
				
				for (int i = 0; i < parent2.getChromosomeLength(); i++) {
					int parent2Gene = i + endSubstr;
					if (parent2Gene >= parent2.getChromosomeLength()) {
						parent2Gene -= parent2.getChromosomeLength();
					}
					if (offspring.containsGene(parent2.getGene(parent2Gene)) == false) {
						for (int ii = 0; ii < offspring.getChromosomeLength(); ii++) {
							if (offspring.getGene(ii) == -1) {
								offspring.setGene(ii, parent2.getGene(parent2Gene));
								break;
							}
						}
					}
				}
				newPopulation.setIndividual(populationIndex, offspring); 
			}else {
				newPopulation.setIndividual(populationIndex, parent1); 
			}
		}
        return newPopulation;
    }

    public Population mutatePopulation(Population population){ 
		// Initialize new population
		Population newPopulation = new Population(populationSize);
		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual individual = population.getFittest(populationIndex);
			// Skip mutation if this is an elite individual
			if (populationIndex >= elitismCount) {
				// Loop over individual's genes
				for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
					// Does this gene need mutation?
					if (mutationRate > Math.random()) {
						int newGenePos = (int) (Math.random() * individual.getChromosomeLength());
						// Get genes to swap
						int gene1 = individual.getGene(newGenePos);
						int gene2 = individual.getGene(geneIndex);
						// Swap genes
						individual.setGene(geneIndex, gene1);
						individual.setGene(newGenePos, gene2);
					}
				}
			}
			// Add individual to population
			newPopulation.setIndividual(populationIndex, individual);
		}
		// Return mutated population
		return newPopulation;
	}
}
