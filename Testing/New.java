import java.util.ArrayList;

public class New {
	ArrayList<ArrayList<Passenger>> seats = new ArrayList<ArrayList<Passenger>>();
	int capacity;
	int stations;
	BestResult[] bestResults;
	int passKm = 0;
	
	public New(int capacity, int stations) {
		this.capacity = capacity;
		this.stations = stations;
		
		bestResults = new BestResult[stations+1];
		
		for(int i = 0; i < capacity; i++) {
			ArrayList<Passenger> pass = new ArrayList<Passenger>();
			seats.add(pass);
		}
	}
	
	public int call(ArrayList<Passenger> requests) {
		ArrayList<Passenger> sortedList = new ArrayList<Passenger>();
		// sort by end time	
		for(int i = 0; i <= stations; i++) {
			for(int j = 0; j < requests.size(); j++) {
				//System.out.println("St. "+i+" og end "+requests.get(j).end);
				if(requests.get(j).end == i) {
					sortedList.add(requests.get(j));
				}
			}
		}
		
		for(int k = 0; k < capacity; k++) {
			//System.out.println("Seat "+k);
			if(seats.size() == 0) {
				break;
			}
			
			for(int i = 0; i <= stations; i++) {
				BestResult best = new BestResult(0, null);
				bestResults[i] = best;
			}
			
			for(int i = 1; i <= stations; i++) {
				//System.out.println("Station "+i+": "+bestResults[i].bestKm);
				for(int j = 0; j < sortedList.size(); j++) {
					//System.out.println("Passenger "+j);
					if(sortedList.get(j).end == i) {
						if(sortedList.get(j).distance+bestResults[sortedList.get(j).start].bestKm > bestResults[i-1].bestKm) {
							bestResults[i].bestKm = sortedList.get(j).distance+bestResults[sortedList.get(j).start].bestKm;
							//System.out.println("Update to "+bestResults[i].bestKm);
							bestResults[i].bestPass = sortedList.get(j);
						}
						else if(bestResults[i].bestKm < bestResults[i-1].bestKm) {
							bestResults[i].bestKm = bestResults[i-1].bestKm;
							bestResults[i].bestPass = bestResults[i-1].bestPass;
							//System.out.println("Update to "+bestResults[i-1].bestKm);
						}
					}
					else if(bestResults[i].bestKm < bestResults[i-1].bestKm) {
						bestResults[i].bestKm = bestResults[i-1].bestKm;
						bestResults[i].bestPass = bestResults[i-1].bestPass;
						//System.out.println("Update to "+bestResults[i-1].bestKm);
					}
				}
			}
			
			/*
			for(int i=0; i < stations; i++) {
				System.out.print("["+i+"] ");
				System.out.println(bestResults[i].bestKm);
				System.out.println(bestResults[i].bestPass);
			}
			*/

			//System.out.println();

			ArrayList<Passenger> seat = backtrack(bestResults, sortedList);
			seats.get(k).addAll(seat);
		}
		return passKm;
	}
	
	public ArrayList<Passenger> backtrack(BestResult[] bestResults, ArrayList<Passenger> sortedList) {
		ArrayList<Passenger> seat = new ArrayList<Passenger>();
		int i = stations;
		while(i > 0) {
			if(bestResults[i].bestPass == null) {
				i = 0;
			}
			else {
				//System.out.println("removes "+bestResults[i].bestPass);
				passKm += bestResults[i].bestPass.distance;
				seat.add(bestResults[i].bestPass);
				sortedList.remove(bestResults[i].bestPass);
				i = bestResults[i].bestPass.start;
			}
		}
		
		for(i = 0; i < seat.size(); i++) {
			//System.out.println(seat.get(i));
		}
		return seat;
	}
	
	public ArrayList<ArrayList<Passenger>> getPassengers() {
		return seats;
	}
}

class BestResult {
	int bestKm;
	Passenger bestPass;
	
	public BestResult(int bestKm, Passenger bestPass) {
		this.bestKm = bestKm;
		this.bestPass = bestPass;
	}
}