import java.util.ArrayList;

public class Result {
	int passKm;
	ArrayList<ArrayList<Passenger>> seats;
	
	public Result(int passKm, ArrayList<ArrayList<Passenger>> seats) {
		this.passKm = passKm;
		this.seats = seats;
	}
}