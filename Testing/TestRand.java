import java.util.ArrayList;

public class TestRand {
	int maxKm = 0;
	
	public ArrayList<Passenger> createRand(int number, int stations) {
		ArrayList<Passenger> passengers = new ArrayList<>();
		for(int i=0; i<number; i++) {
			int start = (int)(Math.random()*stations);
			int stop = (int)(Math.random()*(stations-start)+start+1);
		
			Passenger passenger = new Passenger(start, stop);
			passengers.add(passenger);
			maxKm = maxKm+passenger.distance;
		}
		return passengers;
	}
}