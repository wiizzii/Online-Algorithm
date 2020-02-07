import java.util.ArrayList;

public class Online1 {
	public ArrayList<Passenger> passengerlist = new ArrayList<>();
	public ArrayList<Passenger> List1 = new ArrayList<>();
	public ArrayList<Passenger> sortedList = new ArrayList<>();
	boolean check;
	public Online1() {
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
		*/	
	
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
		
		Passenger passenger = new Passenger();
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
            passengerlist = TestRand.createRand(number, endstation);                                    //Vi opretter number nye passager                  
            int[] seating = new int[endstation+1];                                                      // Opretter s�deliste
            for (int i = 0; i < passengerlist.size(); i++) {                                            
                int k = passengerlist.get(i).start;                                                     // k = start for den enkelte passager
                check = true;
                while(k <= passengerlist.get(i).end-1 && check) {                                       // S� l�nge k er mindre eller lig med den enkelte passager slut sted
                    if (seating[k] >= seats){                                                           // hvis sidepladsen p� den start er st�rre eller lig med antal pladser
                        check = false;
                    } 
                    k++;                                                                                //k++
                }
                if (check) {
                    List1.add(passengerlist.get(i));                                                    //Hvis check = true skal du add den enkelte passager til passager listen
                    for (int j = passengerlist.get(i).start; j < passengerlist.get(i).end; j++) {       // Tilf�her den enklete passagers distance p� s�det, s� s�det er optaget.
                        seating[j] = seating[j]+1;                                                      
                    }
                }
            }
            for (int i = 0; i < List1.size(); i++) {                                                    
                System.out.println(List1.get(i));                                                       // Printer listen med passagere p� det enkelte s�de.
            }

            for(int i = 0; i < endstation; i++) {  
                for(int j = 0; j < List1.size(); j++) {
                    if(List1.get(j).start == i) {
                        sortedList.add(List1.get(j));                                                   //Sortere listen, s� de passagere der skal p� f�rst ogs� st�r f�rst.
                    }
                }
            }

            /* System.out.println("Sorted list:");

            for(int i=0; i<sortedList.size(); i++) {
                    System.out.println(sortedList.get(i));
            }*/

            placePassengers(seats, endstation);                                                         // Nu kalder vi n�ste metode, hvor vi skal placere passagerne.
	}
	
	public void placePassengers(int seats, int endstation) {
            //ArrayList<ArrayList<Passenger>> seats2 = new ArrayList<ArrayList<Passenger>>();

            for(int i=0; i < seats; i++) {
                ArrayList<Passenger> temp = new ArrayList<Passenger>();                                 // Vi opretter en liste temp for hver s�de, hvor vi fylder passagerne ind p�.
                for(int j = 0; j < sortedList.size(); j++) {
                    if(temp.size() == 0) {
                        temp.add(sortedList.get(j));                                                    // Fra start tilf�jer vi bare den f�rste passager fra den sortede liste.
                    }
                    else if(sortedList.get(j).start >= temp.get(temp.size()-1).end) {                   // Ellers tilf�jer vi den f�rste passager i den sortede liste, hvor passagerens
                        temp.add(sortedList.get(j));                                                    // start er lig med hvor henne p� ruten vi er.
                    }
                }
                System.out.println("Seat no. "+i);
                for(int j = 0; j < temp.size(); j++) {
                    for(int k = 0; k < sortedList.size(); k++) {
                        if(temp.get(j) == sortedList.get(k)) {
                            sortedList.remove(k);                                                       // Sletter de passagere, der har reserveret plads inde i den sortede liste.
                        }
                    }
                    System.out.println(temp.get(j));                                                    // Printer de passagere ud, der er reserveret plads til.
                }
            }

            System.out.println("Passengers who did not get a seat (error):");
            if(sortedList.size() == 0) {
                System.out.println("Empty - no error");
            }
            else {
                for(int i=0; i < sortedList.size(); i++) {
                        System.out.println(sortedList.get(i));                                          // Printer den sortede liste ud, som s� m� v�re dem, der ikke har reserveret en plads.
                }
            }
	}
}
