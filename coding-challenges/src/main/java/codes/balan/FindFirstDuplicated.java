package codes.balan;

import java.util.HashSet;
import java.util.Set;

public class FindFirstDuplicated {
    public static void main(String[] args) {
        System.out.println(findFirstDuplicatedValue(new int[] {0,5,3,3,8,3,3,7}));
    }

    private static int findFirstDuplicatedValue(int[] arr){
        Set<Integer> seen = new HashSet<>();
        for (int i=0; i<arr.length; i++){
            if(seen.contains(arr[i])){
                return arr[i];
            }
            seen.add(arr[i]);
        }
        return -1;
    }

}
