import java.util.Random;

public class Passenger {
    int toFloor;
    int fromFloor;
    int waitTime;

    public Passenger(int mode, int minFloor, int maxFloor, Random gen)
    {
        waitTime = 0;
        if(mode == 0) {           // random mode
            fromFloor = gen.nextInt(minFloor, maxFloor+1);
            do {
                toFloor = gen.nextInt(minFloor, maxFloor+1);
            }while (toFloor != fromFloor);
        }
        else if (mode == 1) {     // go to work mode
            if (gen.nextInt(6)<4){
                toFloor = 0;
            } else {
                toFloor = gen.nextInt(minFloor, maxFloor+1);
            }
            do {
                fromFloor = gen.nextInt(minFloor, maxFloor+1);
            }while (toFloor != fromFloor);
        }
        else if (mode == 2) {     // coming back from work mode
            if (gen.nextInt(6)<4){
                fromFloor = 0;
            } else {
                fromFloor = gen.nextInt(minFloor, maxFloor+1);
            }
            do {
                toFloor = gen.nextInt(minFloor, maxFloor+1);
            }while (toFloor != fromFloor);
        }
    }


}
