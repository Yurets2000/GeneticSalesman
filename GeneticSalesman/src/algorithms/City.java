package algorithms;

public class City {
	private int x, y;
	
	public City(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double distanceFrom(City city) { 
		double deltaXSq = Math.pow((city.getX() - this.getX()), 2); 
		double deltaYSq = Math.pow((city.getY() - this.getY()), 2);
		double distance = Math.sqrt(Math.abs(deltaXSq + deltaYSq)); 
		return distance;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
