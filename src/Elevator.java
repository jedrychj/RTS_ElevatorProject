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
    boolean finishStandby;
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
        finishStandby = true;
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

    public void ActivateStandby(){
        this.standby = true;
        this.stopping = true;
        if (this.pos == this.defaultFloor)
            return;
        this.currentTravelTime = this.accelTime + this.travelTime;
        if (this.pos > this.defaultFloor){
            // w dół
            this.direction = false;
            if (this.defaultFloor == this.pos - 1)
                this.currentTravelTime += accelTime;
            else
                this.stopping = false;
        }
        else {
            // w górę
            this.direction = true;
            if (this.defaultFloor == this.pos + 1)
                this.currentTravelTime += accelTime;
            else
                this.stopping = false;
        }
    }

    public boolean CheckStandby(){
        if (!this.calls.isEmpty() && this.finishStandby){
            this.standby = false;
            this.stopping = true;
            this.Depart();
            return false;
        }
        if (!this.calls.isEmpty()){
            this.standby = false;
            this.travelling = true;
            if(this.direction) {
                if (this.calls.contains(this.pos+1)){
                    if (!this.stopping)
                        this.currentTravelTime += this.accelTime;
                    this.stopping = true;
                    return false;
                }
                for (int c : this.calls){
                    if (c>this.pos) {
                        if (this.stopping)
                            this.currentTravelTime -= this.accelTime;
                        this.stopping = false;
                        return false;
                    }
                }
            }
            else {
                if (this.calls.contains(this.pos-1)){
                    if (!this.stopping)
                        this.currentTravelTime += this.accelTime;
                    this.stopping = true;
                    return false;
                }
                for (int c : this.calls){
                    if (c<this.pos) {
                        if (this.stopping)
                            this.currentTravelTime -= this.accelTime;
                        this.stopping = false;
                        return false;
                    }
                }
            }
            this.currentTravelTime = this.ReverseDirection();
            return false;
        }
        return true;
    }

    public int ReverseDirection(){
        int t = this.travelTime - this.currentTravelTime + 2*this.accelTime;
        if(this.stopping)
            t += this.accelTime;
        if (this.calls.contains(this.pos)) { // jeżeli musi tylko zawrócić na piętro, przy którym tuż wcześniej było
            this.stopping = true;
            t += this.accelTime;
        }
        this.direction = !this.direction;
        return t;
    }

    public void ContinueStandby(){
        this.currentTravelTime = this.travelTime;

        if(this.direction){
            if(this.defaultFloor == this.pos+1){
                this.currentTravelTime += this.accelTime;
                this.stopping = true;
            } else {
                this.stopping = false;
            }
        } else {
            if(this.defaultFloor == this.pos-1){
                this.currentTravelTime += this.accelTime;
                this.stopping = true;
            } else {
                this.stopping = false;
            }
        }
    }

}