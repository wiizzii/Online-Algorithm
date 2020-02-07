public class Passenger{
	public int name;
	public int start;
	public int end;
	public int distance;
	public static int count = 0;
	
	public Passenger(int start, int end) {
		count++;
		this.name = count;
		this.start = start;
		this.end = end;
		this.distance = end-start;
	}
	
	public String toString() {
		String string = "Passenger "+name+ ": "+start+", "+end;
		return string;
	}
	
	public static void reset() {
		count = 0;
	}
}
