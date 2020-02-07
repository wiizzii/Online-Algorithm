import java.util.ArrayList;

public class Trainbrute{
int counter = 0;
int number = 50;
int max = 14;
int passKm;
int change;
int List2Count;
boolean globCheck = true;
public ArrayList<Passenger> List1;
public ArrayList<Passenger> List2 = new ArrayList<Passenger>();
public ArrayList<Passenger> passengerlist = new ArrayList<Passenger>();

	public Trainbrute() {
		passengerlist = TestRand.createRand(number, max);
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

	public void call(int seats){
            for (int i = 0; i < seats; i++) {                                           // Når vi kalder denne metode har vi taget som input, 
                List1 = new ArrayList<Passenger>();                                     // hvor mange siddepladser vi har i toget.
                List2Count = 0;                                                         // Så opretter vi en liste til en passagerliste på det enkelte sæde.
                counter = 0;
                Chain(0, List1);
                System.out.println("Seat nr: " + i);
                for(int l = 0; l < List2.size(); l++){
                    System.out.println(List2.get(l));                                   // Det er disse passagere, der er reserveret sæde til
                }
                for (int j = 0; j < List2.size(); j++) {                               
                    for(int k = 0; k < passengerlist.size(); k++) {                     
                        if (List2.get(j).name.equals(passengerlist.get(k).name)) {      // Her sletter vi de passagere fra listen, som vi har 
                            passengerlist.remove(k);                                    // reserveret sæde til i foregående liste.
                        }                                                               
                    }
                }
                passKm = List2Count+passKm;                                             // For at finde ud af hvor mange passager kilometer vi har opnået
                System.out.println("Passagerkilometer på sæde: "+List2Count);           // ligger vi hele tiden list2count til, som er det enkelte sædes
                System.out.println("");                                                 // passager kilometer.
            }
            System.out.println("Passagerkilometer i alt: "+passKm);
            System.out.println("Følgende passagerer kom ikke med:");
            for(int i=0; i<passengerlist.size(); i++) {                                 
                System.out.println(passengerlist.get(i));                               // Her printer vi de passagere, der ikke var plads til i toget.
            }
	}
	
	public void Chain(int end, ArrayList<Passenger> List1) {
            if (end >= max){                                                            // Er startpunktet for en ny passager lig med max (slut stationen)? 
                if (counter > List2Count) {                                             // Er antallet af stationer på den nye liste større end antallet af stationer på den gemte?
                    List2Count = counter;                                               // Hvis den er det, så skal list2Count overskrives med den nye information
                    List2 = new ArrayList<Passenger>(List1);                            // Her overfører vi den liste af passagere, der har fået reseveret et sæde i en ny ArrayList
                }else if (counter == List2Count && List1.size() < List2.size()) {       // Hvis antallet af stationer er lig med antallet af stationer på den gemte, men der er
                    List2Count = counter;                                               // færre passagere med, så er det den nye liste, der skal gemmes.
                    List2 = new ArrayList<Passenger>(List1);
                }
            }else {                                                                     // Hvis ikke startpunktet for en ny passager er lig med slut stationen skal den prøve at finde
                while(end != max) {                                                     // nogle passagere, sÃ¥ den kan komme til slut stationen.
                    for (int i = 0; i < passengerlist.size(); i++) {                    // Den kører nu igennem alle passagere, der har spurgt om de kan komme med toget.
                        if (passengerlist.get(i).start == end) {                        // Hvis den enkelte passager startpunkt er lig med det punkt på ruten vi er noget til,
                            List1.add(passengerlist.get(i));                            // skal passageren tilføjes til den liste, der er i gang med at blive oprettet.
                            counter = counter + passengerlist.get(i).distance;          // Counteren opdateres, så passagerens distance bliver lagt til.
                            Chain(passengerlist.get(i).end, List1);                     // Et rekursivt kald.
                            List1.remove(List1.size()-1);                               // Efter det rekursive kald skal den slette den passager, den lige har taget med, da det
                            counter = counter - passengerlist.get(i).distance;          // betyder det ikke kunne svare sig at tage ham med på dette tidspunkt.
                        }
                    }
                    end++;                                                              // Hvis der ikke var nogen passagere, der skulle med på det givne sted prøver vi med næste station.
                    if (end == max){                                                    // Hvis den har har kørt fra midten af ruten og til slut, uden at samle nogle passagere op, skal
                        Chain(end, List1);                                              // vi stadig fortælle programmet at det her er slut stationen. Det gør vi her. 
                    }
                }
            }
	}
}
