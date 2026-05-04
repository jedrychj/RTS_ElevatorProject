import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

void main() {
    /*
    Set<Integer> s = new HashSet<Integer>();
    int b=2;
    s.add(b);
    int a=2;
    if (s.contains(a)){
        System.out.println("jest");
    }
    s.remove(1);
    if (s.contains(a)){
        System.out.println("jest2");
    }*/

    //Object[] A = {1,2,3};

    /*
    int[] arr = {1,2,3};
    System.out.println(arr.length);*/

    ArrayList<Integer> B = new ArrayList<Integer>();
    B.add(1);
    B.add(3);
    B.add(2);

    System.out.println(B);

    B.add(1, 4);

    System.out.println(B);
    System.out.println(B.size());
    for (int i : B){
        System.out.println(i);
    }
}