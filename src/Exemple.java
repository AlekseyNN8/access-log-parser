import java.util.*;

public class Exemple {
    public static <intArray> void main(String[] args) {

//        ArrayList<Integer> intList = new ArrayList<>();



//        public static void reverse(intArray) {
        int[] intArray = new int[10];
        for (int i = 0; i < 10; i++) {
            intArray[i] = i;
        }
        System.out.println(Arrays.toString(intArray));
            int n = intArray.length - 1;
            for (int i = 0; i < intArray.length / 2; i++) {
//                System.out.println("n " + n);
                int temp = intArray[i];
//                System.out.println("temp " + temp);
                intArray[i] = intArray[n - i];
//                System.out.println("intArray[i] " + intArray[i] + " intArray " + intArray[n-1]);
                intArray[n - i] = temp;
            }
//        System.out.println(Arrays.toString(intArray));
//        }

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(0);
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        System.out.println("intList " + intList);

        int v = intList.size()-1;
        for (int i = 0; i < intList.size()/2; i++) {
            int temp2 = intList.get(i);
            intList.set(i, intList.get(v - i));
            intList.set(v - i, temp2);

        }
        System.out.println(intList);


    }
}
