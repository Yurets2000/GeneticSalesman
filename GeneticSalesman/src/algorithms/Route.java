package algorithms;

public class Route {
	
	private City route[];
	private double distance = 0;
	
	public Route(Individual individual, City cities[]) {
		int chromosome[] = individual.getChromosome();
		route = new City[cities.length];
		for (int geneIndex = 0; geneIndex < chromosome.length; geneIndex++) {
			route[geneIndex] = cities[chromosome[geneIndex]];
		}
	}

	public double getDistance() {
		if (distance > 0) {
			return distance;
		}
		double totalDistance = 0;
		for (int cityIndex = 0; cityIndex + 1 < route.length; cityIndex++) {
			totalDistance += route[cityIndex].distanceFrom(route[cityIndex + 1]);
		}
		totalDistance += route[route.length - 1].distanceFrom(route[0]);
		distance = totalDistance;
		return totalDistance;
	}


}
