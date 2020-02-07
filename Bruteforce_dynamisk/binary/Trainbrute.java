import java.util.ArrayList;

public class Trainbrute{
int passKm;
public ArrayList<Passenger> passengerlist = new ArrayList<>();

	public Trainbrute() {
		/*Passenger passenger1 = new Passenger("a", 2, 5);
        passengerlist.add(passenger1);
        Passenger passenger2 = new Passenger("b", 0, 1);
        passengerlist.add(passenger2);
        Passenger passenger3 = new Passenger("c", 0, 3);
        passengerlist.add(passenger3);
        Passenger passenger4 = new Passenger("d", 1 ,4);
        passengerlist.add(passenger4);
        Passenger passenger5 = new Passenger("e", 3, 6);
        passengerlist.add(passenger5);
        Passenger passenger6 = new Passenger("f", 1, 2);
        passengerlist.add(passenger6);
        Passenger passenger7 = new Passenger("g", 0, 2);
        passengerlist.add(passenger7);
        Passenger passenger8 = new Passenger("h", 3, 6);
        passengerlist.add(passenger8);
        
		Passenger passenger1 = new Passenger("a", 8, 10);
		passengerlist.add(passenger1);
		Passenger passenger2 = new Passenger("b", 1, 4);
		passengerlist.add(passenger2);
		Passenger passenger3 = new Passenger("c", 0, 1);
		passengerlist.add(passenger3);
		Passenger passenger4 = new Passenger("d", 11 ,12);
		passengerlist.add(passenger4);
		Passenger passenger5 = new Passenger("e", 2, 9);
		passengerlist.add(passenger5);
		Passenger passenger6 = new Passenger("f", 4, 11);
		passengerlist.add(passenger6);
		Passenger passenger7 = new Passenger("g", 3, 8);
		passengerlist.add(passenger7);
		Passenger passenger8 = new Passenger("h", 5, 7);
		passengerlist.add(passenger8);
		Passenger passenger9 = new Passenger("i", 7, 12);
		passengerlist.add(passenger9);
		
		/*Passenger passenger = new Passenger();
		passengerlist.add(passenger);*/
		/*
		Passenger passenger1 = new Passenger("a", 4, 11);
		passengerlist.add(passenger1);
		Passenger passenger2 = new Passenger("b", 0, 2);
		passengerlist.add(passenger2);
		Passenger passenger3 = new Passenger("c", 2, 5);
		passengerlist.add(passenger3);
		Passenger passenger4 = new Passenger("d", 9, 14);
		passengerlist.add(passenger4);
		Passenger passenger5 = new Passenger("e", 5, 9);
		passengerlist.add(passenger5);
		Passenger passenger6 = new Passenger("f", 9, 10);
		passengerlist.add(passenger6);
		Passenger passenger7 = new Passenger("g", 10, 14);
		passengerlist.add(passenger7);
		*/
	}

	public void call(int seats, int min, int endstation, int number){
		passengerlist = TestRand.createRand(number, endstation);
		for (int i = 0; i < seats; i++) {
			int counter = 0;
			ArrayList<ArrayList<Passenger>> best = new ArrayList<ArrayList<Passenger>>();
			for (int j = 0; j <= endstation-1; j++) {
				best.add(new ArrayList<Passenger>());
			}
			Chain(min, endstation, best);
			System.out.println("Seat nr: " + i);
			for(int j = 0; j < best.get(best.size()-1).size(); j++) {
				System.out.println("Passager: " + best.get(best.size()-1).get(j));
				counter = counter + best.get(best.size()-1).get(j).distance;
				delete(best.get(best.size()-1).get(j).name);	
			}
			passKm = counter+passKm;
			System.out.println("Passagerkilometer på sæde: "+counter);
			System.out.println("");
		}
		System.out.println("Passagerkilometer i alt: "+passKm);
		System.out.println("Følgende passagerer kom ikke med:");
		for(int i=0; i<passengerlist.size(); i++) {
			System.out.println(passengerlist.get(i));
		}
	}
	
	public void Chain(int min, int max, ArrayList<ArrayList<Passenger>> best) {
		for (int i = max-1; i >= 0; i--) {
			ArrayList<Passenger> list1 = new ArrayList<>();
			Passenger passCheck = new Passenger(0, 0, 0);
			int length = 0;
			if ((max-i-1) != 0) {
				for (int k = 0; k < best.get(max-i-1-1).size(); k++) {
					length = length + best.get(max-i-1-1).get(k).distance;
				}
			}
			boolean check = true;
			for (int j = 0; j < passengerlist.size(); j++) {
				if (passengerlist.get(j).start == i) {
					check = false;
					passCheck = passengerlist.get(j);
					int lengthCheck = passCheck.distance;
					if (max-i-1-passCheck.distance >= 0){
						for (int k = 0; k < best.get(max-i-1-passCheck.distance).size(); k++) {
							lengthCheck = lengthCheck + best.get(max-i-1-passCheck.distance).get(k).distance;
						}
					}
					if (lengthCheck > length) {
						int size = 0;
						for (int k = 0; k < list1.size(); k++) {
							size = size + list1.get(k).distance;
						}
						if (size < lengthCheck) {
							if (max-i-1-passCheck.distance >= 0){
								list1 = new ArrayList(best.get(max-i-1-passCheck.distance));
							}else {
								list1 = new ArrayList();
							}
							list1.add(passCheck);
						}else if (size == lengthCheck && list1.size() > 1) {
							if(passCheck.end < max){
								list1 = new ArrayList(best.get(max-i-1-passCheck.distance));
							}else {
								list1 = new ArrayList();
							}
							list1.add(passCheck);
						}
					}else if (lengthCheck < length) {
						list1 = new ArrayList(best.get(max-i-1-1));
					}else {
						if (list1.size() == 0) {
							list1.add(passCheck);
							System.out.println("added: " + passCheck);
						} else if (list1.size() > 1){
							list1 = new ArrayList();
							list1.add(passCheck);
						}else if (list1.size() < best.get(max-i-1-1).size()) {
							list1 = new ArrayList(best.get(max-i-1-1));
						}
					}
				}
			}
			if (check) {
				if ((max-i-1) >= 0) {
					best.get(max-i-1).addAll(best.get(max-i-1-1));
				}
			}else {
				best.get(max-i-1).addAll(list1);
			}	
		}
	}

	public void delete(int seat){
		int upper = passengerlist.size()-1;
		int lower = 0;
		int current = (upper + lower)/2;

		while(upper > lower && passengerlist.get(current).name != seat){
			if(passengerlist.get(current).name > seat){
				upper = current-1;
			} else if (passengerlist.get(current).name < seat){
				lower = current+1;
			}
			current = (upper + lower)/2;
		}
		if (passengerlist.get(current).name == seat){
			passengerlist.remove(current);
		}
	}
}
