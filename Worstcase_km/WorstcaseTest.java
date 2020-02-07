import java.util.ArrayList;

public class WorstcaseTest {
		public static void main(String[] args) {
		int stations = 24;
		int capacity = 4;
		SemiOnline semiOnline = new SemiOnline(capacity, stations);
		Online online = new Online(capacity, stations);
		ArrayList<Passenger> passengers = Worstcase.get(capacity, stations);

		online.call(passengers);
		semiOnline.call(passengers);
	}
}