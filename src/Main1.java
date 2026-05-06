import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

// ===================================================
// to jest tylko testowy plik
// ===================================================


void main() {

    Set<Integer> s = new HashSet<Integer>();
    int b=2;
    s.add(b);
    s.add(3);
    s.add(9);
    s.add(10);
    int a=2;
    if (s.contains(a)){
        System.out.println("jest");
    }
    s.remove(1);
    if (s.contains(a)){
        System.out.println("jest2");
    }
    Set<Integer> s2 = new HashSet<Integer>();
    for (int i : s2){
        System.out.println(i);
    }

    //Object[] A = {1,2,3};

    /*
    int[] arr = {1,2,3};
    System.out.println(arr.length);*/

    /*<Integer> B = new ArrayList<>();
    B.add(1);
    B.add(3);
    B.add(2);

    System.out.println(B);

    B.add(1, 4);

    System.out.println(B);
    System.out.println(B.size());
    for (int i : B){
        System.out.println(i);
    }*/
}