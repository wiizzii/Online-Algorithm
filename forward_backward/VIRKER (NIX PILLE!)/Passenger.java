public class Passenger{
	public String name;
	public int start;
	public int end;
	public int distance;
	public Passenger(String name,int i, int o) {
		this.name = name;
		this.start = i;
		this.end = o;
		this.distance = o-i;
	}
	
	public String toString() {
		String string = name+ ": "+start+", "+end;
		return string;
	}
}
