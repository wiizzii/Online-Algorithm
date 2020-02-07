import java.util.ArrayList;

public class Dynamic {
int passKm = 0;
int people = 0;
int min = 0;
int seats = 0;
int endstation = 0;
ArrayList<ArrayList<Passenger>> finalSeats = new ArrayList<ArrayList<Passenger>>();
public ArrayList<Passenger> passengerlist = new ArrayList<>();
ArrayList<ArrayList<Passenger>> sorted = new ArrayList<ArrayList<Passenger>>();

	public Dynamic(int seats, int endstation) {
		this.seats = seats;
		this.endstation = endstation;
		for(int i = 0; i < seats; i++){
			ArrayList<Passenger> newSeat = new ArrayList<Passenger>();
			finalSeats.add(newSeat);
		}
	}

	public int call(ArrayList<Passenger> requests){
		ArrayList<Passenger> requests2 = new ArrayList<Passenger>();
		requests2.addAll(requests);
		this.passengerlist = requests2;
		for (int i = 0; i <= endstation; i++) {
			sorted.add(new ArrayList<Passenger>());
		}
		for (int i = 0; i < passengerlist.size(); i++) {
			sorted.get(passengerlist.get(i).end).add(passengerlist.get(i));
		}
		for (int i = 0; i < seats; i++) {
			int counter = 0;
			ArrayList<ArrayList<Passenger>> best = new ArrayList<ArrayList<Passenger>>();
			for (int j = 0; j <= endstation; j++) {
				best.add(new ArrayList<Passenger>());
			}
			Chain(min, endstation, best);
			finalSeats.get(i).addAll(best.get(endstation));
			for(int j = 0; j < best.get(endstation).size(); j++) {
				counter = counter + best.get(endstation).get(j).distance;
				sorted.get(best.get(endstation).get(j).end).remove(best.get(endstation).get(j));	
			}
			passKm = counter+passKm;
		}
		return passKm;
	}
	
	public void Chain(int min, int max, ArrayList<ArrayList<Passenger>> best) {
		ArrayList<Passenger> localBest = new ArrayList<Passenger>();
		for (int i = 1; i <= max; i++) {
			boolean check = true;
			for (int j = 0; j < sorted.get(i).size(); j++) {
				check = false;
				if (sorted.get(i).get(j).start == 0) {
					localBest = new ArrayList<Passenger>();
					localBest.add(sorted.get(i).get(j));
				} else {
					int localDistance = 0;
					for (int k = 0; k < localBest.size(); k++) {
						localDistance = localDistance + localBest.get(k).distance;
					}
					int checkDistance = sorted.get(i).get(j).distance;
					for (int k = 0; k < best.get(sorted.get(i).get(j).start).size(); k++) {
						checkDistance = checkDistance + best.get(sorted.get(i).get(j).start).get(k).distance;
					}
					if (checkDistance > localDistance) {				//Here CompBest starts
						localBest = new ArrayList<Passenger>();
						localBest.add(sorted.get(i).get(j));
						localBest.addAll(best.get(sorted.get(i).get(j).start));
					} else if (checkDistance == localDistance) {
						if ((best.get(sorted.get(i).get(j).start).size()+1) < localBest.size()) {
							localBest = new ArrayList<Passenger>();
							localBest.add(sorted.get(i).get(j));
							localBest.addAll(best.get(sorted.get(i).get(j).start));
						}
					}								//Here CompBest ends
					localDistance = 0;
					for (int k = 0; k < localBest.size(); k++) {
						localDistance = localDistance + localBest.get(k).distance;
					}
					int prevDistance = 0;
					for (int k = 0; k < best.get(i-1).size(); k++) {
						prevDistance = prevDistance + best.get(i-1).get(k).distance;
					}
					if (prevDistance > localDistance) {				//Here CompPrev starts
						localBest = new ArrayList<Passenger>();
						localBest.addAll(best.get(i-1));
					}else if (prevDistance == localDistance && localBest.size() > best.get(i-1).size()) {
						localBest = new ArrayList<Passenger>();
						localBest.addAll(best.get(i-1));
					}								//Here CompPrev ends
				}	
			}
			if (check && i > 0) {
				best.get(i).addAll(best.get(i-1));
			}else {
				best.get(i).addAll(localBest);
			}
		}
	}

	public ArrayList<ArrayList<Passenger>> getPassengers(){
		return finalSeats;
	}
}
