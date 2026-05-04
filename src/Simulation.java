import java.util.Random;

public class Simulation {
    Random randomGenerator;
    SimParams P;
    Elevator E;
    SimResults R;

    int i;

    public Simulation(SimParams Params){
        P = Params;
        E = new Elevator(Params);
        R = new SimResults();
        randomGenerator = new Random();

        System.out.println("Symulacja");
    }

    public void SimStart(){
        for (i=0; i<P.simTime; i++){
            if (i%P.passTime == 0){
                // spawning passengers
            }
        }

        System.out.println("Symuluje się");
    }

    public SimResults SimEnd(){
        return R;
    }
}
