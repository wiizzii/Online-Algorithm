import java.util.ArrayList;

public class Online {
	int min = 0;
	int capacity = 0;
	int stations = 0;
	int max = 0;
	private ArrayList<ArrayList<Passenger>> seats = new ArrayList<ArrayList<Passenger>>();
	
	public Online(int capacity, int stations) {
		this.capacity = capacity;
		this.stations = stations;
		this.max = stations;
		
		for(int i = 0; i < capacity; i++) {
			seats.add(new ArrayList<Passenger>());
		}
	}
	
	public int call(ArrayList<Passenger> passengers) {
		for(int i=0; i < passengers.size(); i++){
			ArrayList<Integer> possSeats = getPossSeats(passengers.get(i));
				
			if(possSeats.size() == 0) {
				// No available seat
			}
			else {
				int start = passengers.get(i).start;
				int end = passengers.get(i).end;
						
				int minRight = max;
				int minLeft = max;
				int minSeat = possSeats.get(0);
						
				for(int h = 0; h < possSeats.size(); h++) { // for each possible seat
					int left = passengers.get(i).start;
					int right = max-passengers.get(i).end;
					
					int seat = possSeats.get(h);
					
					for(int k = 0; k < seats.get(seat).size(); k++) { // for each passenger on seat
						int pStart = seats.get(seat).get(k).start;
						int pEnd = seats.get(seat).get(k).end;

						if(start >= pEnd && start-pEnd < left) {
							left = start-pEnd;
							// find distance to passenger closest to the left on seat
						}
						if(end <= pStart && pStart-end < right) {
							right = pStart-end;
							// find distance to passenger closest to the right on seat
						}
						if(left == 0 && right == 0) {
							// perfect match
							break;
						}
					}
					
					int minSpace = left;
					int maxSpace = right;
					if(left > right) {
						minSpace = right;
						maxSpace = left;
					}
					
					// update min. values
					if((minSpace < minLeft && minSpace < minRight) || (minSpace == minLeft && maxSpace < minRight) || (minSpace == minRight && maxSpace < minLeft)) {
						minLeft = left;
						minRight = right;
						minSeat = seat;
					}
					
					if(minLeft == 0 && minRight == 0) {
						break;
					}
				}
						
				seats.get(minSeat).add(passengers.get(i));
				// add passenger to seat minSeat
			}
		}
		
		return getPassKm(seats);
	}
	
	public ArrayList<Integer> getPossSeats(Passenger passenger) {
		ArrayList<Integer> possSeats = new ArrayList<Integer>();
		for(int i = 0; i < seats.size(); i++) {
			boolean isPoss = true;
			for(int j = 0; j < seats.get(i).size(); j++) {
				if(!(seats.get(i).get(j).start >= passenger.end || seats.get(i).get(j).end <= passenger.start)) {
					isPoss = false;
					break;
				}
			}
			if(isPoss) {
				possSeats.add(i);
			}
		}
		return possSeats;
	}
	
	public int getPassKm(ArrayList<ArrayList<Passenger>> list) {
		int passKm = 0;
		for(int i=0; i < list.size(); i++) {
			for(int j=0; j < list.get(i).size(); j++) {
				passKm += list.get(i).get(j).distance;
			}
		}
		return passKm;
	}
	
	public ArrayList<ArrayList<Passenger>> getPassengers() {
		return seats;
	}
}