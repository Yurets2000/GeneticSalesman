package algorithms;

import java.util.Arrays;
import java.util.Random;

public class Population {

    private Individual[] population;
    private double populationFitness = -1;

    public Population(int populationSize) {
        population = new Individual[populationSize];
    }

    public Population(int populationSize, int chromosomeLength) {
        this(populationSize);
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(chromosomeLength);
            population[i] = individual;
        }
    }

    public Individual[] getIndividuals() {
        return population;
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.population, (Individual o1, Individual o2) -> {
            if (o1.getFitness() > o2.getFitness()) {
                return -1;
            } else if (o1.getFitness() < o2.getFitness()) {
                return 1;
            }
            return 0;
        });
        return this.population[offset];
    }

    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    public double getPopulationFitness() {
        return populationFitness;
    }

    public int size() {
        return population.length;
    }

    public void setIndividual(int offset, Individual individual) {
        population[offset] = individual;
    }

    public Individual getIndividual(int offset) {
        return population[offset];
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Individual individual = population[index];
            population[index] = population[i];
            population[i] = individual;
        }
    }

}

