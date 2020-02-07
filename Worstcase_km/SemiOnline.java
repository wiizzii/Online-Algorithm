import java.util.ArrayList;

public class SemiOnline {
	public ArrayList<Passenger> List1 = new ArrayList<>();
	public ArrayList<Passenger> sortedList = new ArrayList<>();
	ArrayList<Passenger> passengerlist;
	int seats;
	int stations;
	boolean check;
	public SemiOnline(int seats, int stations) {
		this.seats = seats;
		this.stations = stations;
	}

	public void call(ArrayList<Passenger> passengerlist){
		int passKm = 0;
		int people = 0;
		int[] seating = new int[stations+1];
		for (int i = 0; i < passengerlist.size(); i++) {
			int k = passengerlist.get(i).start;
			check = true;
			while(k <= passengerlist.get(i).end-1 && check) {
				if (seating[k] >= seats){
					check = false;
				} 
				k++;
			}
			if (check) {
				List1.add(passengerlist.get(i));
				passKm += passengerlist.get(i).distance;
				people++;
				for (int j = passengerlist.get(i).start; j < passengerlist.get(i).end; j++) {
					seating[j] = seating[j]+1;
				}
			}
		}
		/*for (int i = 0; i < List1.size(); i++) {
			System.out.println(List1.get(i));
		}*/
		
		
		for(int i = 0; i < stations; i++) {
			for(int j = 0; j < List1.size(); j++) {
				if(List1.get(j).start == i) {
					sortedList.add(List1.get(j));
				}
			}
		}
		
		/* System.out.println("Sorted list:");
		
		for(int i=0; i<sortedList.size(); i++) {
			System.out.println(sortedList.get(i));
		}*/
		
		placePassengers(seats, stations);
		System.out.println("Passagerkilometer: "+passKm);
		System.out.println("Antal personer: "+people);
	}
	
	public void placePassengers(int seats, int endstation) {
		//ArrayList<ArrayList<Passenger>> seats2 = new ArrayList<ArrayList<Passenger>>();
		
		for(int i=0; i < seats; i++) {
			ArrayList<Passenger> temp = new ArrayList<Passenger>();
			for(int j = 0; j < sortedList.size(); j++) {
				if(temp.size() == 0) {
					temp.add(sortedList.get(j));
				}
				else if(sortedList.get(j).start >= temp.get(temp.size()-1).end) {
					temp.add(sortedList.get(j));
				}
			}
			System.out.println("Seat no. "+i);
			for(int j = 0; j < temp.size(); j++) {
				for(int k = 0; k < sortedList.size(); k++) {
					if(temp.get(j) == sortedList.get(k)) {
						sortedList.remove(k);
					}
				}
				System.out.println(temp.get(j));
			}
			
		}
		
		/*
		System.out.println("Passengers who did not get a seat (error):");
		if(sortedList.size() == 0) {
			System.out.println("Empty - no error");
		}
		else {
			for(int i=0; i < sortedList.size(); i++) {
				System.out.println(sortedList.get(i));
			}
		}
		*/
	}
}
