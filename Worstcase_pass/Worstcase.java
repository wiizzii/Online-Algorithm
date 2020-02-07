import java.util.ArrayList;

public class Worstcase {
	public static ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	public static void main(String[] args) {
	}
	
	public static ArrayList<Passenger> get() {
			for(int i=1; i <= 3; i++) {
				Passenger pass = new Passenger(1, 2);
				passengers.add(pass);
			}
			for(int s=0; s <= 2; s++) {
				Passenger pass = new Passenger(6*s+4, 6*s+8);
				for(int i=1; i <= 3; i++) {
					passengers.add(pass);
				}
			}
			for(int i=1; i <= 3; i++) {
				Passenger pass = new Passenger(1, 4);
				passengers.add(pass);
			}
			for(int s=1; s <= 3; s++) {
				Passenger pass = new Passenger(6*s, 6*s+4);
				for(int i=1; i <=3; i++) {
					passengers.add(pass);
				}
			}
			for(int s=0; s <= 2; s++) {
				Passenger pass = new Passenger(6*s+2, 6*s+6);
				for(int i=1; i <=3; i++) {
					passengers.add(pass);
				}
			}
			for(int s=0; s <= 7; s++) {
				Passenger pass = new Passenger(2*s+1, 2*s+3);
				for(int i=1; i <=3; i++) {
					passengers.add(pass);
				}
			}

			/*for(int i=0; i < passengers.size(); i++) {
				System.out.println(passengers.get(i));
			}*/
		return passengers;
	}
}