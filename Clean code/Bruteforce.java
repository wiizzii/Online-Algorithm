import java.util.ArrayList;

public class Bruteforce {
int counter = 0;
int passKm = 0;
int people = 0;
int min = 0;
int capacity = 0;
int stations = 0;
ArrayList<Passenger> passengerlist;
int localCount;
ArrayList<ArrayList<Passenger>> seats = new ArrayList<ArrayList<Passenger>>();
public ArrayList<Passenger> List1;
public ArrayList<Passenger> localBest;

public Bruteforce(int capacity, int stations) {
    this.capacity = capacity;
    this.stations = stations;
    for(int i = 0; i < capacity; i++){
        ArrayList<Passenger> newSeat = new ArrayList<Passenger>();
        seats.add(newSeat);
    }
}

public int call(ArrayList<Passenger> requests){
    ArrayList<Passenger> requests2 = new ArrayList<Passenger>();
    requests2.addAll(requests);
    this.passengerlist = requests2;
    for (int i = 0; i < capacity; i++) {
        List1 = new ArrayList<Passenger>();
        localCount = 0;
        counter = 0;
        Chain(0, stations, passengerlist);
        people += localBest.size();
        seats.get(i).addAll(localBest);
        for(int l = 0; l < localBest.size(); l++){
            delete(localBest.get(l).name, passengerlist);	
        }
        passKm = localCount+passKm;
    }
    return passKm;
}

public void Chain(int end, int max, ArrayList<Passenger> passengerlist) {
    if (end < max){
        while(end != max) {
            for (int i = 0; i < passengerlist.size(); i++) {
                if (passengerlist.get(i).start == end) {
                    List1.add(passengerlist.get(i));
                    counter = counter + passengerlist.get(i).distance;
                    Chain(passengerlist.get(i).end, max, passengerlist);
                    List1.remove(List1.size()-1);   // we remove the passenger to move back to where that passenger started
                    counter = counter - passengerlist.get(i).distance;  // we remove passenger.distance because conter is not a local variable
                }
            }
            end++;
        }
    }else {
        if (counter > localCount) {
            localCount = counter;
            localBest = new ArrayList<Passenger>(List1);
        }
        else if (counter == localCount && List1.size() < localBest.size()) {
            localCount = counter;
            localBest = new ArrayList<Passenger>(List1);
        }
    }
}

public void delete(int seat, ArrayList<Passenger> passengerlist){
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

public ArrayList<ArrayList<Passenger>> getPassengers(){
    return seats;
}
}
