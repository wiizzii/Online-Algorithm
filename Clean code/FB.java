import java.util.ArrayList;

public class FB {
int counter = 0;
int allKm = 0;
int people = 0;
int min = 0;
int seats = 0;
int endstation = 0;
public ArrayList<Passenger> List1;
public ArrayList<Passenger> List2;
public ArrayList<Passenger> bestList;
public ArrayList<Passenger> specialList;
public ArrayList<Passenger> passengerlist;
public ArrayList<ArrayList<Passenger>> finalSeats = new ArrayList<ArrayList<Passenger>>();
boolean check = false;
Passenger tempKald = null;

public FB(int seats, int endstation) {
        this.seats = seats;
        this.endstation = endstation;
        for(int i = 0; i < seats; i++){
                ArrayList<Passenger> newSeat = new ArrayList<Passenger>();
                finalSeats.add(newSeat);
        }
}

public int call(ArrayList<Passenger> requests){
        ArrayList<Passenger> requests2 = new ArrayList<Passenger>();
        requests2.addAll(requests);
        this.passengerlist = requests2;
        for (int i = 0; i < seats; i++) {
                int passKm = 0;
                specialList = new ArrayList<>();
                counter = 0;
                Chain(0, min, endstation);
                people += specialList.size();
                finalSeats.get(i).addAll(specialList);
                for(int l = 0; l < specialList.size(); l++){	
                        passKm = passKm + specialList.get(l).distance;
                        delete(specialList.get(l).name);
                }
                allKm = passKm+allKm;
        }
        for(int i=0; i<passengerlist.size(); i++) {
        }

        return allKm;
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
        counter = 0;
        for(int i=0; i<passengerlist.size(); i++) {
            if(passengerlist.get(i).start == end && passengerlist.get(i).end <= max) {
                if(counter < passengerlist.get(i).distance) {
                    temp = passengerlist.get(i);
                    counter = passengerlist.get(i).distance;
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
        counter = 0;
        for(int i=0; i<passengerlist.size(); i++) {
            if(passengerlist.get(i).end == end && passengerlist.get(i).start >= minimum) {
                if(counter < passengerlist.get(i).distance) {
                    temp = passengerlist.get(i);
                    counter = passengerlist.get(i).distance;
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
    if(countForward > countBackward) { // comparison between the chains
        countBest = countForward;
        bestList =  new ArrayList<>(List1);
    }else if(countForward < countBackward) {
        countBest = countBackward;
        bestList = new ArrayList<>(List2);
    }else {
        if(List1.size() > List2.size()) {
            countBest = countBackward;
            bestList = new ArrayList<>(List2);
        }else {
            countBest = countForward;
            bestList = new ArrayList<>(List1);
        }
    }
    for(int i=0; i < passengerlist.size(); i++) { // check for specialcase
        if(passengerlist.get(i).distance >= countBest && passengerlist.get(i).start >= minimum && passengerlist.get(i).end <= max) {
            bestList = new ArrayList<>();
            bestList.add(passengerlist.get(i));
            countBest = passengerlist.get(i).distance;
            tempKald = passengerlist.get(i);
            check = true;
        }
    }
    if (check) { // specialcase
        check = false;
        for (int i = 0; i < bestList.size(); i++) {
            specialList.add(bestList.get(i));
        }
        for (int j = 0; j < specialList.size(); j++) {
            delete(specialList.get(j).name);
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

public void delete(int seat){
    int upper = passengerlist.size()-1;
    int lower = 0;
    int current = (upper + lower)/2;

    while(upper > lower && passengerlist.get(current).name != seat) {
        if(passengerlist.get(current).name > seat) {
            upper = current-1;
        } else if (passengerlist.get(current).name < seat) {
            lower = current+1;
        }
        current = (upper + lower)/2;
    }
    if (passengerlist.size() == 0) {
        //null
    }else if (passengerlist.get(current).name == seat) {
        passengerlist.remove(current);
    }
}

public ArrayList<ArrayList<Passenger>> getPassengers(){
    return finalSeats;
}
}
