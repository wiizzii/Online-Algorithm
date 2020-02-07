public class Passenger{
	public int name;
	public int start;
	public int end;
	public int distance;
	
	public Passenger(int name,int i, int o) {
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
