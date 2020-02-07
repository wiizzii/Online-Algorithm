import java.util.ArrayList;

public class Metode1 {
int counter = 0;
int number = 10000;
int endstation = 24;
int min = 0;
int allKm;
int change;
boolean globCheck = true;
public ArrayList<Passenger> List1;
public ArrayList<Passenger> List2;
public ArrayList<Passenger> bestList;
public ArrayList<Passenger> specialList;
public ArrayList<Passenger> passengerlist = new ArrayList<>();
boolean check = false;
Passenger tempKald = null;

	public Metode1() {
		passengerlist = TestRand.createRand(number, endstation);
		/*
		Passenger passenger1 = new Passenger("a", 1, 11);
        passengerlist.add(passenger1);
        Passenger passenger2 = new Passenger("b", 11, 12);
        passengerlist.add(passenger2);
        Passenger passenger3 = new Passenger("c", 0, 3);
        passengerlist.add(passenger3);
        Passenger passenger4 = new Passenger("d", 10, 12);
        passengerlist.add(passenger4);
        Passenger passenger5 = new Passenger("e", 11, 13);
        passengerlist.add(passenger5);
        Passenger passenger6 = new Passenger("f", 10, 13);
        passengerlist.add(passenger6);
        Passenger passenger7 = new Passenger("g", 12, 23);
        passengerlist.add(passenger7);
        Passenger passenger8 = new Passenger("h", 22, 24);
        passengerlist.add(passenger8);
		
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
        
        Passenger passenger1 = new Passenger("a", 1, 9);
        passengerlist.add(passenger1);
        Passenger passenger2 = new Passenger("b", 0, 3);
        passengerlist.add(passenger2);
        Passenger passenger3 = new Passenger("c", 3, 4);
        passengerlist.add(passenger3);
        Passenger passenger4 = new Passenger("d", 9 ,10);
        passengerlist.add(passenger4);
        Passenger passenger5 = new Passenger("e", 7, 10);
        passengerlist.add(passenger5);
        /*
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

	public void call(int seats){
            for (int i = 0; i < seats; i++) {                                              
                int passKm = 0;                                                            
                specialList = new ArrayList<>();                                           
                int List2Count = 0;
                counter = 0;
                Chain(0, min, endstation);
                System.out.println("Seat nr: " + i);
                for(int l = 0; l < specialList.size(); l++){                           
                    System.out.println(specialList.get(l));                                 
                    passKm = passKm + specialList.get(l).distance;                          
                    for(int k = 0; k < passengerlist.size(); k++) {                         
                        if (specialList.get(l).name.equals(passengerlist.get(k).name)) {    
                            passengerlist.remove(k);                                        
                        }
                    }
                }
                allKm = passKm+allKm;
                System.out.println("Passagerkilometer på sæde: "+passKm);
                System.out.println("");
            }
            System.out.println("Passagerkilometer i alt: "+allKm);
            System.out.println("Følgende passagerer kom ikke med:");
            for(int i=0; i<passengerlist.size(); i++) {
                System.out.println(passengerlist.get(i));
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
                    bestList = List2;
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
                    for(int k = 0; k < passengerlist.size(); k++) {
                        if (specialList.get(j).name.equals(passengerlist.get(k).name)) {
                            passengerlist.remove(k);
                        }
                    }
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
}
