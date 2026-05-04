
public class Elevator {
    int defaultFloor;
    int capacity;
    int travelTime;
    int accelTime;
    int doorTime;

    int pos;

    boolean direction; // true - up; false - down
    boolean standby;

    public Elevator(SimParams P){
        defaultFloor = P.defaultFloor;
        capacity = P.elevatorCapacity;
        travelTime = P.regularTime;
        accelTime = P.accelTime;
        doorTime = P.doorTime;

        pos = P.defaultFloor;

        standby = true;
        direction = true;

    }
}
