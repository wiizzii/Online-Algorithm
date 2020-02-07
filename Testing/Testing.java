import java.util.ArrayList;
import java.util.Arrays;

public class Testing {
	public static void main(String[] args) {
		int capacity = Integer.parseInt(args[0]);
		int stations = Integer.parseInt(args[1]);
		int passengers = Integer.parseInt(args[2]);
		double loops = 999.0;
		
		int bruteforceKm = 0;
		int dynamicKm = 0;
		int fbKm = 0;
		int onlineKm = 0;
		int onOffKm = 0;
		
		int maxKm = 0;
		int worstOnlineKm = capacity*stations+1;
		int worstOnOffKm = worstOnlineKm;
		int worstFbKm = worstOnlineKm;
		int worstDynKm = worstOnlineKm;
		int worstBruteKm = worstOnlineKm;
		
		ArrayList<ArrayList<Passenger>> worstBruteSeats = null;
		ArrayList<ArrayList<Passenger>> worstDynSeats = null;
		ArrayList<ArrayList<Passenger>> worstFbSeats = null;
		ArrayList<ArrayList<Passenger>> worstOnOffSeats = null;
		ArrayList<ArrayList<Passenger>> worstOnlineSeats = null;
		
		int[] bruteKms = new int[(int)(loops)];
		int[] dynKms = new int[(int)(loops)];
		int[] fbKms = new int[(int)(loops)];
		int[] onOffKms = new int[(int)(loops)];
		int[] onlineKms = new int[(int)(loops)];
		
		for(int i = 0; i < loops; i++) {
			System.out.println("loop "+i);
			TestRand rand = new TestRand();
			ArrayList<Passenger> requests = rand.createRand(passengers, stations);

			maxKm += rand.maxKm;
			
			Bruteforce bruteforce = new Bruteforce(capacity, stations);
			Dynamic dynamic = new Dynamic(capacity, stations);
			FB fb = new FB(capacity, stations);
			Online online = new Online(capacity, stations);
			OnlineOff onlineoff = new OnlineOff(capacity, stations);
			
			/*
			int bruteNewKm = bruteforce.call(requests);
			bruteKms[i] = bruteNewKm;
			if(bruteNewKm < worstBruteKm) {
				worstBruteKm = bruteNewKm;
				worstBruteSeats = bruteforce.getPassengers();
			}
			bruteforceKm += bruteNewKm;
			*/
				
			int dynNewKm = dynamic.call(requests);
			dynKms[i] = dynNewKm;
			if(dynNewKm < worstDynKm) {
				worstDynKm = dynNewKm;
				worstDynSeats = dynamic.getPassengers();
			}
			dynamicKm += dynNewKm;
			
			int fbNewKm = fb.call(requests);
			fbKms[i] = fbNewKm;
			if(fbNewKm < worstFbKm) {
				worstFbKm = fbNewKm;
				worstFbSeats = fb.getPassengers();
			}
			fbKm += fbNewKm;
			
			int onlineNewKm = online.call(requests);
			onlineKms[i] = onlineNewKm;
			if(onlineNewKm < worstOnlineKm) {
				worstOnlineKm = onlineNewKm;
				worstOnlineSeats = online.getPassengers();
			}
			onlineKm += onlineNewKm;
			
			int onOffNewKm = onlineoff.call(requests);
			onOffKms[i] = onOffNewKm;
			if(onOffNewKm < worstOnOffKm) {
				worstOnOffKm = onOffNewKm;
				worstOnOffSeats = onlineoff.getPassengers();
			}
			onOffKm += onOffNewKm;
			Passenger.reset();
		}
		
		System.out.println("Kapacitet: "+capacity+", stationer: "+stations+", requests: "+passengers);
		System.out.println("Max km gennemsnit: "+maxKm/loops+" ("+stations*capacity+")");
		
		System.out.println("BRUTE FORCE:");
		System.out.println("Average: "+bruteforceKm/loops);
		
		System.out.println();
		
		System.out.println("DYNAMIC:");
		System.out.println("Average: "+dynamicKm/loops);
		
		printPassengers(worstDynSeats);
		
		System.out.println();
		
		System.out.println("FORWARD-BACKWARD:");
		System.out.println("Average: "+fbKm/loops);

		System.out.println();
		
        System.out.println("ONLINE OFFLINE:");
		System.out.println("Average: "+onOffKm/loops);
		
		System.out.println();
                
		System.out.println("ONLINE:");
		System.out.println("Average: "+onlineKm/loops);
		
		System.out.println();
		
		Arrays.sort(bruteKms);
		Arrays.sort(dynKms);
		Arrays.sort(fbKms);
		Arrays.sort(onOffKms);
		Arrays.sort(onlineKms);
		
		System.out.println("BRUTE FORCE");
		System.out.println("Minimum: "+bruteKms[0]+" - Nedre kvartil: "+bruteKms[(((int)(loops)/2)-1)/2]+" - Median: "+bruteKms[(int)(loops)/2]+" - Øvre kvartil: "+bruteKms[((((int)(loops)/2)-1)/2)+(int)(loops)/2]+" - Maksimum: "+bruteKms[(int)(loops)-1]);
		
		System.out.println("DYNAMIC");
		System.out.println("Minimum: "+dynKms[0]+" - Nedre kvartil: "+dynKms[(((int)(loops)/2)-1)/2]+" - Median: "+dynKms[(int)(loops)/2]+" - Øvre kvartil: "+dynKms[((((int)(loops)/2)-1)/2)+(int)(loops)/2]+" - Maksimum: "+dynKms[(int)(loops)-1]);
		
		System.out.println("FORWARD-BACKWARD");
		System.out.println("Minimum: "+fbKms[0]+" - Nedre kvartil: "+fbKms[(((int)(loops)/2)-1)/2]+" - Median: "+fbKms[(int)(loops)/2]+" - Øvre kvartil: "+fbKms[((((int)(loops)/2)-1)/2)+(int)(loops)/2]+" - Maksimum: "+fbKms[(int)(loops)-1]);
		
		System.out.println("ONLINE OFFLINE");
		System.out.println("Minimum: "+onOffKms[0]+" - Nedre kvartil: "+onOffKms[(((int)(loops)/2)-1)/2]+" - Median: "+onOffKms[(int)(loops)/2]+" - Øvre kvartil: "+onOffKms[((((int)(loops)/2)-1)/2)+(int)(loops)/2]+" - Maksimum: "+onOffKms[(int)(loops)-1]);
		
		System.out.println("ONLINE");
		System.out.println("Minimum: "+onlineKms[0]+" - Nedre kvartil: "+onlineKms[(((int)(loops)/2)-1)/2]+" - Median: "+onlineKms[(int)(loops)/2]+" - Øvre kvartil: "+onlineKms[((((int)(loops)/2)-1)/2)+(int)(loops)/2]+" - Maksimum: "+onlineKms[(int)(loops)-1]);
	}
	
	public static void printPassengers(ArrayList<ArrayList<Passenger>> seats) {
		for(int i = 0; i < seats.size(); i++) {
			System.out.println("Seat "+i+":");
			for(int j = 0; j < seats.get(i).size(); j++) {
				System.out.println(seats.get(i).get(j));
			}
		}
	}
}