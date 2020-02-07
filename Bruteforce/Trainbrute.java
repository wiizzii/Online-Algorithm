import java.util.ArrayList;

public class Trainbrute{
int counter = 0;
//int number = 50;
//int max = 14;
int passKm;
int change;
int bestCount;
public ArrayList<Passenger> List1;
public ArrayList<Passenger> best;
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
			List1 = new ArrayList<Passenger>();
			bestCount = 0;
			counter = 0;
			Chain(0, endstation);
			System.out.println("Seat nr: " + i);
			for(int l = 0; l < best.size(); l++){
				System.out.println("Passenger " + best.get(l));
				delete(best.get(l).name);	
			}
			passKm = bestCount+passKm;
			System.out.println("Passagerkilometer på sæde: "+bestCount);
			System.out.println("");
		}
		System.out.println("Passagerkilometer i alt: "+passKm);
		System.out.println("Følgende passagerer kom ikke med:");
		for(int i=0; i<passengerlist.size(); i++) {
			System.out.println("Passenger " + passengerlist.get(i));
		}
	}
	
	public void Chain(int end, int max) {
		if (end < max){
			while(end != max) {
				for (int i = 0; i < passengerlist.size(); i++) {
					if (passengerlist.get(i).start == end) {
						List1.add(passengerlist.get(i));
						counter = counter + passengerlist.get(i).distance;
						Chain(passengerlist.get(i).end, max);
						List1.remove(List1.size()-1);
						counter = counter - passengerlist.get(i).distance;
					}
				}
				end++;
			}
		}else {
			if (counter > bestCount) {
				bestCount = counter;
				best = new ArrayList<Passenger>(List1);
			}else if (counter == bestCount && List1.size() < best.size()) {
				bestCount = counter;
				best = new ArrayList<Passenger>(List1);
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
