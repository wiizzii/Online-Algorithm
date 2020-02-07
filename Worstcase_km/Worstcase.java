import java.util.ArrayList;

public class Worstcase {
	public static ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	public static void main(String[] args) {
	}
	
	public static ArrayList<Passenger> get(int capacity, int stations) {
			int n = capacity;
			int k = stations;
			for(int i=1; i <= n/2; i++) {
				Passenger pass = new Passenger(1, 2);
				passengers.add(pass);
			}
			for(int i=1; i <= n/2; i++) {
				Passenger pass = new Passenger(3, 4);
				passengers.add(pass);
			}
		
			for(int i=1; i <= n/2; i++) {
				Passenger pass = new Passenger(1, 3);
				passengers.add(pass);
			}
			
			for(int i=1; i <= n/2; i++) {
				Passenger pass = new Passenger(2, k);
				passengers.add(pass);
			}
			/*for(int i=0; i < passengers.size(); i++) {
				System.out.println(passengers.get(i));
			}*/
		return passengers;
	}
}