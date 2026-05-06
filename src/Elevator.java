import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Elevator {
    int defaultFloor;
    int capacity;
    int travelTime;
    int accelTime;
    int doorTime;

    int pos;

    boolean direction; // true - up; false - down
    boolean standby;
    //int travelling; // 0 - stoi, 1 - ruch bez zatrzymania, 2 - ruch z postoju, 3 - ruch z zatrzymaniem, 4 - ruch z postoju i z zatrzymaniem
    boolean travelling;
    boolean stopping; // jeżeli winda ma się zatrzymać na następnym piętrze
    boolean doors; // jak winda otwiera drzwi
    int currentTravelTime;
    int currentDoorTime;

    ArrayList<Passenger> passengersIn;
    Set<Integer> calls;

    public Elevator(SimParams P){
        defaultFloor = P.defaultFloor;
        capacity = P.elevatorCapacity;
        travelTime = P.regularTime;
        accelTime = P.accelTime;
        doorTime = P.doorTime;

        pos = P.defaultFloor;

        standby = true;
        direction = true;
        travelling = false;
        stopping = false;

        currentTravelTime = 0;
        currentDoorTime = 0;

        passengersIn = new ArrayList<>();
        calls = new HashSet<>();
    }

    public void AddCall(Passenger P){
        this.calls.add(P.fromFloor);
    }

    public void checkDirection(){
        if(this.direction){
            for (int c : this.calls){
                if (c>this.pos)
                    return;
            }
            this.direction=false;
        } else {
            for (int c : this.calls){
                if (c<this.pos)
                    return;
            }
            this.direction=true;
        }
    }

    public void Depart(){
        this.checkDirection();

        if(this.stopping)
            this.currentTravelTime = this.accelTime;

        if(this.direction){
            if(this.calls.contains(this.pos+1)){
                this.currentTravelTime += this.travelTime + this.accelTime;
                this.stopping = true;
            } else {
                this.currentTravelTime += this.travelTime;
                this.stopping = false;
            }
        } else {
            if(this.calls.contains(this.pos-1)){
                this.currentTravelTime += this.travelTime + this.accelTime;
                this.stopping = true;
            } else {
                this.currentTravelTime = this.travelTime;
                this.stopping = false;
            }
        }
        this.travelling = true;
    }

    public void checkForStop(){
        if(this.direction){
            if(this.calls.contains(this.pos+1)){
                this.currentTravelTime += this.accelTime;
                this.stopping = true;
            }
        } else {
            if(this.calls.contains(this.pos-1)){
                this.currentTravelTime += this.accelTime;
                this.stopping = true;
            }
        }
    }

    public void Ride(){
        if (!this.stopping)
            this.checkForStop();

        this.currentTravelTime--;
    }


}