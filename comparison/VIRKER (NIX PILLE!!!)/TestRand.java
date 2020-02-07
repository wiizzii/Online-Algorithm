import java.util.ArrayList;

public class TestRand {
	/* public static void main(String[] args) {
		ArrayList<Passenger> passengers = createRand(10, 3);
		for(int i=0; i<passengers.size(); i++) {
			System.out.println(passengers.get(i).name+": start "+passengers.get(i).start+", stop: "+passengers.get(i).end);
		}
	} */
	
	public static ArrayList<Passenger> createRand(int number, int stations) {
		ArrayList<Passenger> passengers = new ArrayList<>();
		for(int i=0; i<number; i++) {
			int start = (int)(Math.random()*stations);
			int stop = (int)(Math.random()*(stations-start)+start+1);
		
			Passenger passenger = new Passenger("passenger"+i, start, stop);
			passengers.add(passenger);
		}
		return passengers;
	}
}