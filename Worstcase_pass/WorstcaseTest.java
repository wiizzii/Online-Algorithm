import java.util.ArrayList;

public class WorstcaseTest {
		public static void main(String[] args) {
		int stations = 22;
		int capacity = 9;
		SemiOnline semiOnline = new SemiOnline(capacity, stations);
		Online online = new Online(capacity, stations);
		ArrayList<Passenger> passengers = Worstcase.get();

		online.call(passengers);
		semiOnline.call(passengers);
	}
}