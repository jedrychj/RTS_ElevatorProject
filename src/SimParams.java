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

    // domyślny czas jest określony dzięki pomiarom czasu przejazdu windy w GEC oraz unormowane tak, aby
    // czas przyspieszenia był równy 4, wtedy jeden cykl to około 0,22 s
    static int deLowPassTime = 341; // 5 min
    static int deMediumPassTime = 137; // 2 min
    static int deHighPassTime = 68; // 1 min

    static int dePassChance = 25; // %

    static int deSimTime = 49160; // 3 h
    static int deAccelTime = 4; // 0,88 s
    static int deDoorTime = 32; // 7 s
    static int deRegularTime = 15; // 3,2 s

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
