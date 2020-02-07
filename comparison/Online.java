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
		int passKm = 0;
		
		while(i < passengers.size()) {
			count++;
			
			//System.out.println("Request: "+passengers.get(i));
			
			if(count == 1) {
				seats.get(0).add(passengers.get(i));
				//System.out.println("- "+passengers.get(i) + " added to seat 0");
				passKm = passKm+passengers.get(i).distance;
			}
			else {
				ArrayList<Integer> possSeats = check(passengers.get(i));
				
				if(possSeats.size() == 0) {
					//System.out.println("- No available seat for this passenger");
					count--;
				}
				else {
					ArrayList<Integer> chainComp = new ArrayList<Integer>();
					boolean added = false;
					for(int k = 0; k < possSeats.size(); k++) {
						int caseCount = 0;
						
						if(passengers.get(i).start == min) {
							chainComp.add(possSeats.get(k));
							//System.out.println("Min match!");
							caseCount++;
						}
						if(passengers.get(i).end == max) {
							chainComp.add(possSeats.get(k));
							//System.out.println("Max match!");
							caseCount++;
						}
					
						for(int h = 0; h < seats.get(possSeats.get(k)).size(); h++) {
							if(seats.get(possSeats.get(k)).get(h).end == passengers.get(i).start) {
								chainComp.add(possSeats.get(k));
								//System.out.println("Start compatible!");
								caseCount++;
							}
							else if(seats.get(possSeats.get(k)).get(h).start == passengers.get(i).end) {
								chainComp.add(possSeats.get(k));
								//System.out.println("End compatible!");
								caseCount++;
							}
						}
						
						if(caseCount == 2) {
							seats.get(possSeats.get(k)).add(passengers.get(i));
							//System.out.println("- "+passengers.get(i) + " added to seat "+possSeats.get(k));
							passKm = passKm+passengers.get(i).distance;
							//System.out.println(passKm);
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
							
							/* if(seats.get(chainComp.get(h)).size() == 0 && (passengers.get(i).end == max || passengers.get(i).start == min)) {
								seats.get(chainComp.get(h)).add(passengers.get(i));
								System.out.println(passengers.get(i) + " added to seat "+chainComp.get(h));
								passKm = passKm+passengers.get(i).distance;
							} */
						}
						
						seats.get(minBreakSeat).add(passengers.get(i));
						//System.out.println("- "+passengers.get(i) + " added to seat "+minBreakSeat);
						passKm = passKm+passengers.get(i).distance;
						//System.out.println(passKm);
					}
					else if(!added) {
						// worst case
						
						//System.out.println("- No possible chains..");
						
						int minSpace = max+1;
						int minRest = max+1;
						int bestSeat = possSeats.get(0);
						
						int closestStart = passengers.get(i).start;
						int closestEnd = max-passengers.get(i).end;
						
						for(int h = 0; h < possSeats.size(); h++) {
							if(seats.get(possSeats.get(h)).size() == 0 && passengers.get(i).start < minSpace && max-passengers.get(i).end < minRest) {
								minSpace = passengers.get(i).start;
								minRest = max-passengers.get(i).end;
							}
							else {
								for(int k = 0; k < seats.get(possSeats.get(h)).size(); k++) {
									if(passengers.get(i).start > seats.get(possSeats.get(h)).get(k).end && passengers.get(i).start-seats.get(possSeats.get(h)).get(k).end < closestEnd) {
										closestEnd = passengers.get(i).start-seats.get(possSeats.get(h)).get(k).end;
									}
									if(passengers.get(i).end < seats.get(possSeats.get(h)).get(k).start && seats.get(possSeats.get(h)).get(k).start-passengers.get(i).end < closestStart) {
										closestStart = seats.get(possSeats.get(h)).get(k).start-passengers.get(i).end;
									}		
									
								}
							}
							if(closestEnd < closestStart) {
								if(minSpace >= closestEnd && minRest > closestStart) {
									minSpace = closestEnd;
									minRest = closestStart;
									bestSeat = possSeats.get(h);
								}
							}
							else {
								if(minSpace >= closestStart && minRest > closestEnd) {
									minSpace = closestStart;
									minRest = closestEnd;
									bestSeat = possSeats.get(h);
								}
							}
						}
						
						seats.get(bestSeat).add(passengers.get(i));
						//System.out.println("- "+passengers.get(i) + " added to seat "+bestSeat);
						passKm = passKm+passengers.get(i).distance;
						//System.out.println(passKm);
					}
				}
			}
			i++;
		}
		
		//System.out.println();
		System.out.println("|Passagerkilometer i alt:   "+passKm);
		System.out.println("|Endeligt antal passagerer: "+count);
	}

	// passenger.start < seats.get(i).get(j).end && passenger.end >= seats.get(i).get(j).start
	
	public ArrayList<Integer> check(Passenger passenger) {
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
}