import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Simulation {
    Random randomGenerator;
    SimParams P;
    Elevator E;
    SimResults R;
    ArrayList<Passenger> passengersOut;

    public Simulation(SimParams Params){
        P = Params;
        E = new Elevator(Params);
        R = new SimResults();
        randomGenerator = new Random();

        passengersOut = new ArrayList<>();

        System.out.println("Symulacja");

        System.out.println(P.passTime);
    }

    public void SimStart(){
        int i;
        for (i=0; i<this.P.simTime; i++){
            this.trySpawning(i);

            if(this.E.doors){ // obsługa odliczania czasu drzwi i zamknięcia
                if(this.E.currentDoorTime==0)
                    this.CloseDoors();
                this.E.currentDoorTime--;
            }

            if(this.E.travelling){ // obsługa jazdy
                if(this.E.currentTravelTime==0){ // co jeżeli skończy jazdę
                    this.E.travelling=false;
                    this.R.floorCount++;
                    if (this.E.direction)
                        this.E.pos++;
                    else
                        this.E.pos--;
                    if(this.E.stopping)
                        this.OpenDoors();
                    else
                        this.E.Depart();
                }
                this.E.Ride();
            }

//            if(this.E.standby){ // zwykły standby, że po prostu stoi i czeka
//                if (this.E.calls.contains(this.E.pos)){
//                    this.E.standby=false;
//                    this.OpenDoors();
//                }
//                else {
//                    if (!this.E.calls.isEmpty()) {
//                        this.E.standby=false;
//                        this.E.Depart();
//                    }
//                }
//            }

            if (this.E.standby) {
                if (this.E.CheckStandby()){ // od razu sprawdzanie czy nie należy zakończyć
                    if (!this.E.finishStandby) {
                        if (this.E.currentTravelTime == 0) { // co jeżeli skończy jazdę
                            this.R.floorCount++;
                            if (this.E.direction)
                                this.E.pos++;
                            else
                                this.E.pos--;
                            if (this.E.stopping)
                                this.E.finishStandby = true;
                            else
                                this.E.ContinueStandby();
                        }
                        this.E.currentTravelTime--;
                    }
                }
            }

            if (this.E.standby) { // standby z podjazdem do domyślnego piętra

            }

            this.UpdatePassengers();
        }

        System.out.println("Symuluje się");
    }

    public SimResults SimEnd(){
        this.R.passengerTime=this.R.passengerTime/this.R.allPassengers;

        System.out.println("Wszyscy pasażerowie" + R.allPassengers);
        System.out.println("Obsłużeni pasażerowie" + R.passengersCount);
        return this.R;
    }

    public void OpenDoors(){
        this.E.doors = true;
        this.E.currentDoorTime = this.E.doorTime;

        this.R.stopCount++;

        for (int j=0; j<this.E.passengersIn.size(); j++){
            if (this.E.passengersIn.get(j).toFloor==this.E.pos) {
                this.R.passengersCount++;
                this.R.passengerTime+=this.E.passengersIn.get(j).waitTime;
                this.E.passengersIn.remove(j);
                j--; // żeby nie skipnąć pasażera
            }
        }
    }

    public void CloseDoors(){
        this.E.doors = false;

        for (int j=0; j<this.passengersOut.size(); j++){
            if (this.passengersOut.get(j).fromFloor==this.E.pos){
                this.E.passengersIn.add(this.passengersOut.get(j));
                this.passengersOut.remove(j);
                j--;
            }
        }
        this.E.calls.remove(this.E.pos);

        if (this.E.calls.isEmpty())
            this.E.ActivateStandby(); // this.E.stanby = true <- w trybie zwykłego standby
        else
            this.E.Depart();
    }

    public void UpdatePassengers(){
        for (Passenger p : this.passengersOut)
            p.waitTime++;
        for (Passenger p : this.E.passengersIn)
            p.waitTime++;
    }

    public void trySpawning(int i){
        if (i%this.P.passTime == 0){
            while(this.randomGenerator.nextInt(100)<this.P.passChance){ // szansa na pojawienie się pasażera
                // spawning passengers
                this.passengersOut.add(new Passenger(this.P.mode, 0, this.P.maxFloor, this.randomGenerator));
                this.E.AddCall(this.passengersOut.getLast());

                this.R.allPassengers++;
            }
        }
    }
}
