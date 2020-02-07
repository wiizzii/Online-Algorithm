import java.util.ArrayList;

public class Poster {
	public static void main(String[] args) {
		OnlineOff onoff = new OnlineOff(2,7);

		Passenger a = new Passenger(2,5);
		Passenger b = new Passenger(0,1);
		Passenger c = new Passenger(0,3);
		Passenger d = new Passenger(1,4);
		Passenger e = new Passenger(3,6);
		Passenger f = new Passenger(1,2);
		Passenger g = new Passenger(0,2);
		Passenger h = new Passenger(3,6);

		ArrayList<Passenger> req = new ArrayList<Passenger>();
		req.add(a);
		req.add(b);
		req.add(c);
		req.add(d);
		req.add(e);
		req.add(f);
		req.add(g);
		req.add(h);

		onoff.call(req);

		System.out.println(onoff.getPassengers());
	}
}