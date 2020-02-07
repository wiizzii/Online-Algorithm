import java.util.ArrayList;

public class FB {
int counter = 0;
//int number = 100000;
//int endstation = 24;
//int min = 0;
int allKm = 0;
int people = 0;
int min = 0;
public ArrayList<Passenger> List1;
public ArrayList<Passenger> List2;
public ArrayList<Passenger> bestList;
public ArrayList<Passenger> specialList;
public ArrayList<Passenger> passengerlist = new ArrayList<>();
boolean check = false;
Passenger tempKald = null;

	public FB(ArrayList<Passenger> requests) {
		this.passengerlist = requests;
	}

	public void call(int seats, int endstation){
		for (int i = 0; i < seats; i++) {
			int passKm = 0;
			specialList = new ArrayList<>();
			counter = 0;
			Chain(0, min, endstation);
			//System.out.println("Seat nr: " + i);
			people = specialList.size();
			for(int l = 0; l < specialList.size(); l++){
				//System.out.println("Passenger " + specialList.get(l));	
				passKm = passKm + specialList.get(l).distance;
				delete(specialList.get(l).name);
			}
			allKm = passKm+allKm;
			people = specialList.size();
			//System.out.println("Passagerkilometer på sæde: "+passKm);
			//System.out.println("");
		}
		System.out.println("Passagerkilometer: "+allKm);
		System.out.println("Personer: "+people);
		//System.out.println("Følgende passagerer kom ikke med:");
		for(int i=0; i<passengerlist.size(); i++) {
			//System.out.println("Passenger " + passengerlist.get(i));
		}
	}
	
	public void Chain(int end, int minimum, int max) {
		List1 = new ArrayList<Passenger>();
		List2 = new ArrayList<Passenger>();
		int countForward = 0;
		int countBackward = 0;
		int countBest = 0;
		bestList = null;
		Passenger temp = null;
		while(end < max) { // create chain, going forward
			for(int i=0; i<passengerlist.size(); i++) {
				if(passengerlist.get(i).start == end && passengerlist.get(i).end <= max) {
					if(counter < passengerlist.get(i).distance) {
						temp = passengerlist.get(i);
						counter = passengerlist.get(i).distance;
					}
				}
			}
			if(temp == null) {
				end++;
			}else {
				List1.add(temp);
				end = temp.end;
				countForward = countForward+temp.distance;
				temp = null;
			}
		}
		end = max;
		while(end > minimum) { // create chain, going Backward
			for(int i=0; i<passengerlist.size(); i++) {
				if(passengerlist.get(i).end == end && passengerlist.get(i).start >= minimum) {
					if(counter < passengerlist.get(i).distance) {
						temp = passengerlist.get(i);
						counter = passengerlist.get(i).distance;
					}
				}
			}
			if(temp == null) {
				end--;
			}else {
				List2.add(temp);
				end = temp.start;
				countBackward = countBackward+temp.distance;
				temp = null;
			}	
		}
		if(countForward > countBackward) {
			countBest = countForward;
			bestList =  new ArrayList<>(List1);
		}else if(countForward < countBackward) {
			countBest = countBackward;
			bestList = new ArrayList<>(List2);
		}else {
			if(List1.size() > List2.size()) {
				countBest = countBackward;
				bestList = new ArrayList<>(List2);
			}else {
				countBest = countForward;
				bestList = new ArrayList<>(List1);
			}
		}
		for(int i=0; i < passengerlist.size(); i++) {
			if(passengerlist.get(i).distance >= countBest && passengerlist.get(i).start >= minimum && passengerlist.get(i).end <= max) {
				bestList = new ArrayList<>();
				bestList.add(passengerlist.get(i));
				countBest = passengerlist.get(i).distance;
				tempKald = passengerlist.get(i);
				check = true;
			}
		}
		if (check) {
			check = false;
			for (int i = 0; i < bestList.size(); i++) {
				specialList.add(bestList.get(i));
			}
			for (int j = 0; j < specialList.size(); j++) {
				delete(specialList.get(j).name);
			}
			int minTemp = tempKald.start;
			int maxTemp = tempKald.end;
			Chain(minimum, minimum, minTemp);
			Chain(maxTemp, maxTemp, max);	
		}else {
			for (int i = 0; i < bestList.size(); i++) {
				specialList.add(bestList.get(i));
			}
		}
	}

	public void delete(int seat){
		int upper = passengerlist.size()-1;
		int lower = 0;
		int current = (upper + lower)/2;

		while(upper > lower && passengerlist.get(current).name != seat) {
			if(passengerlist.get(current).name > seat) {
				upper = current-1;
			} else if (passengerlist.get(current).name < seat) {
				lower = current+1;
			}
			current = (upper + lower)/2;
		}
		if (passengerlist.size() == 0) {
			//null
		}else if (passengerlist.get(current).name == seat) {
			passengerlist.remove(current);
		}
	}
}
