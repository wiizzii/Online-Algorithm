import java.util.ArrayList;

public class Trainbrute{
int counter = 0;
//int number = 50;
//int max = 14;
int passKm;
int change;
int List2Count;
public ArrayList<Passenger> List1;
public ArrayList<Passenger> List2;
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

	public void call(int seats, int min, int endstation, int number, ArrayList<Passenger> randList) {
		passengerlist.addAll(randList);

		for (int i = 0; i < seats; i++) {
			List1 = new ArrayList<Passenger>();
			List2Count = 0;
			counter = 0;
			Chain(0, min, endstation);
			//System.out.println("Seat nr: " + i);
			for(int l = 0; l < List2.size(); l++){
				//System.out.println(List2.get(l));
				for(int k = 0; k < passengerlist.size(); k++) {
					if (List2.get(l).name.equals(passengerlist.get(k).name)) {
						passengerlist.remove(k);
					}
				}	
			}
			passKm = List2Count+passKm;
			//System.out.println("Passagerkilometer på sæde: "+List2Count);
			//System.out.println("");
		}
		System.out.println("Passagerkilometer i alt: "+passKm);
		//System.out.println("Følgende passagerer kom ikke med:");
		for(int i=0; i<passengerlist.size(); i++) {
			//System.out.println(passengerlist.get(i));
		}
		int finalCount = randList.size()-passengerlist.size();
		System.out.println("Endeligt antal passagerer: "+finalCount);
	}
	
	public void Chain(int end, int minimum, int max) {
		if (end >= max){
			if (counter > List2Count) {
				List2Count = counter;
				List2 = new ArrayList<Passenger>(List1);
			}else if (counter == List2Count && List1.size() < List2.size()) {
				List2Count = counter;
				List2 = new ArrayList<Passenger>(List1);
			}
		}else {
			while(end != max) {
				for (int i = 0; i < passengerlist.size(); i++) {
					if (passengerlist.get(i).start == end) {
						List1.add(passengerlist.get(i));
						counter = counter + passengerlist.get(i).distance;
						Chain(passengerlist.get(i).end, minimum, max);
						List1.remove(List1.size()-1);
						counter = counter - passengerlist.get(i).distance;
					}
				}
				end++;
				if (end == max){
					Chain(end, minimum, max);
				}
			}
		}
	}
}
