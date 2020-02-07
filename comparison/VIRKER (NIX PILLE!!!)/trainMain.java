import java.util.ArrayList;

public class trainMain{
	public static void main(String[] args) {
		int requests = 20;
		int seats = 6;
		int stations = 5;
                System.out.println("|--------------------------------|");
                System.out.println("|         Requests = "+requests+"          |");
                System.out.println("|            Seats = "+seats+"           |");
                System.out.println("|         Stations = "+stations+"           |");
	
		TestRand rand = new TestRand();
		ArrayList<Passenger> randList = rand.createRand(requests, stations);
	
                System.out.println("|--------------------------------|");
                System.out.println("| SEMI-ONLINE                    |");
                System.out.println("|                                |"); 
		Online1 online1 = new Online1();
		online1.call(seats, 0, stations, requests, randList);
                
                System.out.println("|--------------------------------|");
		System.out.println("| BRUTE-FORCE                    |");
                System.out.println("|                                |");
		Trainbrute train = new Trainbrute();
		train.call(seats, 0, stations, requests, randList);
		
                System.out.println("|--------------------------------|");
		System.out.println("| FORWARD-BACKWARD               |");
                System.out.println("|                                |");
		Metode1 metode1 = new Metode1();
		metode1.call(seats, 0, stations, requests, randList);
                System.out.println("|--------------------------------|");
	}
}
