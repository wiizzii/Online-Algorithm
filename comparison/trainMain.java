import java.util.ArrayList;

public class trainMain{
	public static void main(String[] args) {
		int requests = Integer.parseInt(args[0]);
		int seats = Integer.parseInt(args[1]);
		int stations = Integer.parseInt(args[2]);
	
		TestRand rand = new TestRand();
		ArrayList<Passenger> randList = rand.createRand(requests, stations);
		
                System.out.println("_____________________________________");
                /*System.out.printf("%-5s%-5s%5s%d%13s\n","|","Requests","=",requests,"|");
                System.out.printf("%-5s%-5s%8s%d%14s\n","|","Seats","=",seats,"|");
                System.out.printf("%-5s%-5s%5s%d%14s\n","|","Stations", "=",stations,"|");*/
                System.out.println("|       Requests    =   "+requests);
                System.out.println("|       Seats       =   "+seats);
                System.out.println("|       Stations    =   "+stations);
                System.out.println("|___________________________________|");
                
                System.out.println("| ONLINE                            |");
		System.out.println("|");
		Online online = new Online(seats, stations);
		online.call(randList);
		
                System.out.println("|___________________________________|");
		System.out.println("| SEMI                              |");
                System.out.println("|");
		Online1 online1 = new Online1();
		online1.call(seats, 0, stations, requests, randList);
		
		
		/*System.out.println("Brute force begins..");
		Trainbrute train = new Trainbrute();
		train.call(seats, 0, stations, requests, randList);
				
		System.out.println();*/
		
                System.out.println("|___________________________________|");
		System.out.println("| Forward-Backward                  |");
                System.out.println("|");
		Metode1 metode1 = new Metode1();
		metode1.call(seats, 0, stations, requests, randList);
                System.out.println("|___________________________________|");
	}

	// Problem: Semi-online gør det værre end online i et tilfælde?? Der må være en fejl.
}
