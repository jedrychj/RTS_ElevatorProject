public class SimParams {
    int mode;

    int maxFloor;
    int defaultFloor;

    int passTime;
    int passChance;

    int simTime;
    int accelTime;
    int doorTime;
    int regularTime;

    int elevatorCapacity;

    static int deMaxFloor = 5;
    static int deDefaultFloor = 0;

    static int deLowPassTime = 20;
    static int deMediumPassTime = 12;
    static int deHighPassTime = 4;

    static int dePassChance = 10;

    static int deSimTime = 10000;
    static int deAccelTime = 3;
    static int deDoorTime = 10;
    static int deRegularTime = 10;

    static int deElevatorCapacity = 7;

    public SimParams()
    {
        mode = 0;
        maxFloor = deMaxFloor;
        defaultFloor = deDefaultFloor;

        passTime = deLowPassTime;
        passChance = dePassChance;
        simTime = deSimTime;
        accelTime = deAccelTime;
        doorTime = deDoorTime;
        regularTime = deRegularTime;

        elevatorCapacity = deElevatorCapacity;
    }
}
