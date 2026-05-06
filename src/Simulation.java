import java.util.ArrayList;
import java.util.Random;

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
    }

    public void SimStart(){
        int i;
        for (i=0; i<P.simTime; i++){
            if (i%P.passTime == 0){
                while(randomGenerator.nextInt(100)<P.passChance){ // szansa na pojawienie się pasażera
                    // spawning passengers
                    passengersOut.add(new Passenger(P.mode, 0, P.maxFloor, randomGenerator));
                    E.AddCall(passengersOut.getLast());
                }
            }

            if(E.standby){
                for (int c : E.calls){
                    if (c==E.pos){
                        //drzwi
                        break;
                    }
                    if (c>E.pos){
                        // do góry
                        break;
                    }
                    if (c<E.pos){
                        // w dół
                        break;
                    }
                }
            }

        }

        System.out.println("Symuluje się");
    }

    public SimResults SimEnd(){
        return R;
    }
}
