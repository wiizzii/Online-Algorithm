import java.util.ArrayList;

public class OnlineOff {
    ArrayList<Passenger> List1 = new ArrayList<>();
    private ArrayList<ArrayList<Passenger>> seats = new ArrayList<ArrayList<Passenger>>();
    boolean check;
    int min = 0;
    int passKm = 0;
    int people = 0;
    int capacity = 0;
    int stations = 0;

    public OnlineOff(int capacity, int stations) {
        this.capacity = capacity;
        this.stations = stations;

        for(int i = 0; i < capacity; i++) {
            ArrayList<Passenger> emptyList = new ArrayList<Passenger>();
            seats.add(emptyList);
        }
    }

    public int call(ArrayList<Passenger> passengerlist) {
        int[] density = new int[stations+1];
        for (int i = 0; i < passengerlist.size(); i++) {
            int k = passengerlist.get(i).start;
            check = true;
            while(k <= passengerlist.get(i).end-1 && check) {
                // check if there is available space for this passengers
                if (density[k] >= capacity) {
                    check = false;
                } 
                k++;
            }
            if (check) {
                List1.add(passengerlist.get(i));
                passKm += passengerlist.get(i).distance;
                people++;
                for (int j = passengerlist.get(i).start; j < passengerlist.get(i).end; j++) {
                    density[j] = density[j]+1;
                }
            }
        }		
        ArrayList<Passenger> sortedList = sortPassengers(List1);
        placePassengers(sortedList);
        return passKm;
    }

    public ArrayList<Passenger> sortPassengers(ArrayList<Passenger> passengers) {
        // sort passengers by non-decreasing start station
        ArrayList<Passenger> sortedList = new ArrayList<Passenger>();
        for(int i = 0; i < stations; i++) {
            for(int j = 0; j < passengers.size(); j++) {
                if(passengers.get(j).start == i) {
                    sortedList.add(passengers.get(j));
                }
            }
        }
        return sortedList;
    }

    public void placePassengers(ArrayList<Passenger> passengers) {
        // place passengers with First Fit
        for(int i = 0; i < passengers.size(); i++) {
            for(int j = 0; j < capacity; j++) {
                if(seats.get(j).size() == 0 || passengers.get(i).start >= seats.get(j).get(seats.get(j).size()-1).end) {
                    seats.get(j).add(passengers.get(i));
                    break;
                }
            }
        }
    }

    public ArrayList<ArrayList<Passenger>> getPassengers() {
        return seats;
    }
}
