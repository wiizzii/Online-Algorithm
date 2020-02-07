import java.util.ArrayList;

public class Online {
	int min = 0;
	int capacity;
	int stations;
	int max;
	ArrayList<ArrayList<Passenger>> seats = new ArrayList<ArrayList<Passenger>>();
	
	public Online(int capacity, int stations) {
		this.capacity = capacity;
		this.stations = stations;
		this.max = stations;
		
		for(int i = 0; i < capacity; i++) {
			seats.add(new ArrayList<Passenger>());
		}
	}
	
	public void call(ArrayList<Passenger> passengers) {
		int count = 0;
		int i = 0;
		
		while(i < passengers.size()) {
			count++;
			
			System.out.println("Request: "+passengers.get(i));
			
			if(count == 1) {
				seats.get(0).add(passengers.get(i));
				System.out.println("- "+passengers.get(i) + " added to seat 0");
			}
			else {
				ArrayList<Integer> possSeats = getPossSeats(passengers.get(i));
				
				if(possSeats.size() == 0) {
					System.out.println("- No available seat for this passenger");
				}
				else {
					ArrayList<Integer> chainComp = new ArrayList<Integer>();

					/* for(int k = 0; k < possSeats.size(); k++) {
						for(int h = 0; h < seats.get(possSeats.get(k)).size(); h++) {
							if(seats.get(possSeats.get(k)).get(h).end == passengers.get(i).start || passengers.get(i).start == min) {
								chainComp.add(possSeats.get(k));
								System.out.println("Start compatible!");
							}
							else if(seats.get(possSeats.get(k)).get(h).start == passengers.get(i).end || passengers.get(i).start == max) {
								chainComp.add(possSeats.get(k));
								System.out.println("End compatible!");
							}
						}
						
						if(caseCount == 2) {
							seats.get(possSeats.get(k)).add(passengers.get(i));
							//System.out.println("- "+passengers.get(i) + " added to seat "+possSeats.get(k));
		
							added = true;
							break;
						}
					}
					if(!added && chainComp.size() > 0) {
						int minBreak = max;
						int minBreakSeat = chainComp.get(0);
						for(int h = 0; h < chainComp.size(); h++) {
							for(int k = 0; k < seats.get(chainComp.get(h)).size(); k++) {
								if(seats.get(chainComp.get(h)).get(k).start == passengers.get(i).end) {
									for(int p = 0; p < seats.get(chainComp.get(h)).size(); p++) {
										int diff = passengers.get(i).start-seats.get(chainComp.get(h)).get(p).end;
										if(diff < minBreak && diff > 0) {
											minBreak = diff;
											minBreakSeat = chainComp.get(h);
										}
									}
								}
								else if(seats.get(chainComp.get(h)).get(k).end == passengers.get(i).start) {
									for(int p = 0; p < seats.get(chainComp.get(h)).size(); p++) {
										int diff = seats.get(chainComp.get(h)).get(p).start-passengers.get(i).end;
										if(diff < minBreak && diff > 0) {
											minBreak = diff;
											minBreakSeat = chainComp.get(h);
										}
									}
								}
							}
						}
						
						seats.get(minBreakSeat).add(passengers.get(i));
						//System.out.println("- "+passengers.get(i) + " added to seat "+minBreakSeat);
					} */
						
						int left = passengers.get(i).start;
						int right = max-passengers.get(i).end;
						
						int minLeft = max;
						int minRight = max;
						int minSeat = possSeats.get(0);
						
						for(int h = 0; h < possSeats.size(); h++) {
							if(seats.get(possSeats.get(h)).size() == 0 && passengers.get(i).start < minLeft && max-passengers.get(i).end < minRight) {
								minLeft = passengers.get(i).start;
								minRight = max-passengers.get(i).end;
								break;
							}
							else {
								for(int k = 0; k < seats.get(possSeats.get(h)).size(); k++) {
									if(passengers.get(i).start > seats.get(possSeats.get(h)).get(k).end && passengers.get(i).start-seats.get(possSeats.get(h)).get(k).end < left) {
										left = passengers.get(i).start-seats.get(possSeats.get(h)).get(k).end;
										// find distance to passenger closest to the left on seat
									}
									if(passengers.get(i).end < seats.get(possSeats.get(h)).get(k).start && seats.get(possSeats.get(h)).get(k).start-passengers.get(i).end < right) {
										right = seats.get(possSeats.get(h)).get(k).start-passengers.get(i).end;
										// find distance to passenger closest to the right on seat
									}
								}
							}
							
							if(left == 0 && right == 0) {
								minSeat = possSeats.get(h);
								System.out.println("Perfect match");
								break;
							}
							
							if(left < right && minRight >= left && minLeft > right) {
								minRight = right;
								minLeft = left;
								minSeat = possSeats.get(h);
							}
							else if(minRight >= left && minLeft > right) {
								minRight = left;
								minLeft = right;
								minSeat = possSeats.get(h);
							}
						}
						
						seats.get(minSeat).add(passengers.get(i));
						System.out.println("- "+passengers.get(i) + " added to seat "+minSeat);
					
				}
			}
			i++;
		}
		
		System.out.println();
		System.out.println(getPassKm(seats));
	}

	// passenger.start < seats.get(i).get(j).end && passenger.end >= seats.get(i).get(j).start
	
	public ArrayList<Integer> getPossSeats(Passenger passenger) {
		ArrayList<Integer> possSeats = new ArrayList<Integer>();
		for(int i = 0; i < seats.size(); i++) {
			boolean isPoss = true;
			for(int j = 0; j < seats.get(i).size(); j++) {
				if(passenger.start < seats.get(i).get(j).end && passenger.end > seats.get(i).get(j).start) {
					isPoss = false;
				}
			}
			if(isPoss) {
				possSeats.add(i);
			}
		}
		return possSeats;
	}
	
	public int getPassKm(ArrayList<ArrayList<Passenger>> list) {
		int passKm = 0;
		for(int i=0; i < list.size(); i++) {
			for(int j=0; j < list.get(i).size(); j++) {
				passKm += list.get(i).get(j).distance;
			}
		}
		return passKm;
	}
}