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

    public SimParams()
    {
        mode = 0;
        maxFloor = 5;
        defaultFloor = 0;

        passTime = 4;
        passChance = 10;
        simTime = 10000;
        accelTime = 3;
        doorTime = 10;
        regularTime = 10;

        elevatorCapacity = 7;
    }
}
