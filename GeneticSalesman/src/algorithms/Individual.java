package algorithms;

public class Individual {
    
    private int[] chromosome;
    private double fitness = -1;

    public Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }
    
    public Individual(int chromosomeLength){
        chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            chromosome[gene] = gene;
        }
    }
    
	public boolean containsGene(int gene) {
		for (int i = 0; i < chromosome.length; i++) {
			if (chromosome[i] == gene) {
				return true;
			}
		}
		return false;
	}
    
    public int[] getChromosome(){
        return chromosome;
    }
    
    public int getChromosomeLength(){
        return chromosome.length;
    }
    
    public void setGene(int offset, int gene){
        chromosome[offset] = gene;
    }
    
    public int getGene(int offset){
        return chromosome[offset];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }
    
    @Override
    public String toString(){
        String output = "";
        for(int i = 0; i < chromosome.length; i++){
            output += chromosome[i] + "-";
        }
        return output;
    }
}

