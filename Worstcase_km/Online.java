import java.util.ArrayList;

public class Online {
	int min = 0;
	int capacity;
	int stations;
	int max;
	ArrayList<ArrayList<Passenger>> seats = new ArrayList<ArrayList<Passenger>>();
	
	public Online(int capacity, int stations) {
		this.capacity = capacity;
		this.stations = stations;
		this.max = stations;
		
		for(int i = 0; i < capacity; i++) {
			seats.add(new ArrayList<Passenger>());
		}
	}
	
	public void call(ArrayList<Passenger> passengers) {
		int people = 0;
		for(int i=0; i < passengers.size(); i++){
			System.out.println("Request: "+passengers.get(i));
			ArrayList<Integer> possSeats = getPossSeats(passengers.get(i));
				
			if(possSeats.size() == 0) {
				System.out.println("- No available seat for this passenger");
			}
			else {
				int left = passengers.get(i).start;
				int right = max-passengers.get(i).end;
						
				int minRight = max;
				int minLeft = max;
				int minSeat = possSeats.get(0);
				
				System.out.println("Begynder");
						
				for(int h = 0; h < possSeats.size(); h++) {
				//&& passengers.get(i).start < minLeft && max-passengers.get(i).end < minRight
					if(seats.get(possSeats.get(h)).size() == 0) {
						left = passengers.get(i).start;
						right = max-passengers.get(i).end;
					}
					for(int k = 0; k < seats.get(possSeats.get(h)).size(); k++) {
						if(passengers.get(i).start >= seats.get(possSeats.get(h)).get(k).end && passengers.get(i).start-seats.get(possSeats.get(h)).get(k).end < left) {
							left = passengers.get(i).start-seats.get(possSeats.get(h)).get(k).end;
							// find distance to passenger closest to the left on seat
						}
						if(passengers.get(i).end <= seats.get(possSeats.get(h)).get(k).start && seats.get(possSeats.get(h)).get(k).start-passengers.get(i).end < right) {
							right = seats.get(possSeats.get(h)).get(k).start-passengers.get(i).end;
							// find distance to passenger closest to the right on seat
						}
					}
					
					//System.out.println("seat: "+possSeats.get(h)+", left: "+left+", right: "+right);
							
					if(left == 0 && right == 0) {
						minSeat = possSeats.get(h);
						System.out.println("Perfect match");
						break;
					}
					
					int minSpace = left;
					int maxSpace = right;
					if(left > right) {
						minSpace = right;
						maxSpace = left;
					}
					
					if(minSpace <= minLeft && minSpace <= minRight && (minLeft != left && minRight != right)) {
						minLeft = left;
						minRight = right;
						minSeat = possSeats.get(h);
					}
					
					/*		
					if((left <= minLeft && left <= minRight && right <= minRight) || (right <= minLeft && right <= minRight && left <= minLeft)) {
						if((left != minLeft || right != minRight) && (right != minLeft || left != minRight)) {
							minLeft = left;
							minRight = right;
							minSeat = possSeats.get(h);
						}
					}
					*/
					
					//System.out.println("seat: "+possSeats.get(h)+", minLeft: "+minLeft+", minRight: "+minRight);
				}
						
				seats.get(minSeat).add(passengers.get(i));
				System.out.println("- "+passengers.get(i) + " added to seat "+minSeat);
				people++;
			}
		}
			
		System.out.println();
		System.out.println("Passagerkilometer: "+getPassKm(seats)+", antal personer: "+people);
	}
	
	public ArrayList<Integer> getPossSeats(Passenger passenger) {
		ArrayList<Integer> possSeats = new ArrayList<Integer>();
		for(int i = 0; i < seats.size(); i++) {
			boolean isPoss = true;
			for(int j = 0; j < seats.get(i).size(); j++) {
				if(passenger.start < seats.get(i).get(j).end && passenger.end > seats.get(i).get(j).start) {
					isPoss = false;
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
}