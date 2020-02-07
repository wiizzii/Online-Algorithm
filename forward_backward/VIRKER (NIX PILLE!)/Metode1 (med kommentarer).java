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
            for (int i = 0; i < seats; i++) {                                               // Når vi kalder denne metode har vi taget som input, 
                int passKm = 0;                                                             // hvor mange siddepladser vi har i toget.
                specialList = new ArrayList<>();                                            // Så opretter vi en liste til en passagerliste på det enkelte sæde.
                int List2Count = 0;
                counter = 0;
                Chain(0, min, endstation);
                System.out.println("Seat nr: " + i);
                for(int l = 0; l < specialList.size(); l++){                                // Vi går den enkelte liste igennem
                    System.out.println(specialList.get(l));                                 // For at printe alle passagerne, der har fået en plads
                    passKm = passKm + specialList.get(l).distance;                          // opdatere passager kilometer
                    for(int k = 0; k < passengerlist.size(); k++) {                         
                        if (specialList.get(l).name.equals(passengerlist.get(k).name)) {    
                            passengerlist.remove(k);                                        // Sletter de passagere, der er blevet reserveret en plads.
                        }
                    }
                }
                allKm = passKm+allKm;                                                       //Opdatere passager kilometer
                System.out.println("Passagerkilometer på sæde: "+passKm);
                System.out.println("");
            }
            System.out.println("Passagerkilometer i alt: "+allKm);
            System.out.println("Følgende passagerer kom ikke med:");
            for(int i=0; i<passengerlist.size(); i++) {
                System.out.println(passengerlist.get(i));                                   //De passagere, der ikke var plads til i toget
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
            while(end < max) {                                                                          // Så længe den nuværende station ikke er noget til slut stationen
                for(int i=0; i<passengerlist.size(); i++) {                                             // Hvis den pågældende passager's start er lig  med den nuværende station og
                    if(passengerlist.get(i).start == end && passengerlist.get(i).end <= max) {          // den pågældende passager's slut  er under eller lig med slut stationen.
                        if(counter < passengerlist.get(i).distance) {                                   // Hvis en kæde af rejsende er mindre en en enkelt rejsende, skal
                            temp = passengerlist.get(i);                                                // vi gemme den enkelte rejsende. Det gør vi i temp.
                        }
                    }
                }
                if(temp == null) {                                                                      // Hvis der ikke er fundet nogen temp skal den gå en station længere frem.
                    end++;
                }else {
                    List1.add(temp);                                                                    // Vi tilføjer en person til det enkelte sæde rejse.
                    end = temp.end;                                                                     // Vi får en ny station
                    countForward = countForward+temp.distance;                                          // Vi tæller counteren.
                    temp = null;                                                                        // Vi tømmer temp
                }
            }
            end = max;                                                                                  // Vi sætter den nuværende station lig med max, da vi nu gør det samme 
            while(end > minimum) {                                                                      // som ovenover, bare fra slut stationen til start.                               
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
            if(countForward > countBackward) {                                                          // Var det den fremadgående eller bagudgående algoritme, der fik den længste
                countBest = countForward;                                                               // rejse? Det er den vi kopier over i Bestlist
                bestList =  new ArrayList<>(List1);
            }else if(countForward < countBackward) {
                countBest = countBackward;
                bestList = new ArrayList<>(List2);
            }else {                                                                                     // Hvis de er lige store, skal vi have den med færrest passagere, da
                if(List1.size() > List2.size()) {                                                       // vi så evt. kan bruge de mindre i en anden sammenhæng senere hen.
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
                    bestList.add(passengerlist.get(i));                                                 // Hvis der er en enkelt rejsende der er større end den bedste kæde, skal
                    countBest = passengerlist.get(i).distance;                                          // vi bruge den enkelte rejsende, men kan vi finde noget foran og bagved 
                    tempKald = passengerlist.get(i);                                                    // den enkelte rejsende distance, så vi kan lægge noget på den. Hvis vi
                    check = true;                                                                       // kan det er det vores bedste løsning. 
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
                Chain(minimum, minimum, minTemp);                                                     // Her kalder vi den chain rekursivt med henblik på det foran den enkelte rejse distance
                Chain(maxTemp, maxTemp, max);                                                         // det samme som ovenover - bare bagefter.
            }else {
                for (int i = 0; i < bestList.size(); i++) {
                    specialList.add(bestList.get(i));
                }
            }
	}
}
