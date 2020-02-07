import java.util.ArrayList;

public class trainMain {
	public static void main(String[] args) {
		int seats = 3;
		int requests = 5;
		int stations = 8;
		
		Online online = new Online(seats, stations);
		TestRand rand = new TestRand();
		ArrayList<Passenger> requestList = rand.createRand(requests, stations);
		
		/* ArrayList<Passenger> requestList = new ArrayList<Passenger>();
		Passenger pass1 = new Passenger("a", 6, 8);
		Passenger pass2 = new Passenger("b", 0, 4);
		Passenger pass3 = new Passenger("c", 0, 6);
		Passenger pass4 = new Passenger("d", 4, 8);
		requestList.add(pass1);
		requestList.add(pass2);
		requestList.add(pass3);
		requestList.add(pass4); */
		
		System.out.println("Forespurgt antal passager-km: "+rand.maxKm);
		System.out.println();
		
		online.call(requestList);
	}
}