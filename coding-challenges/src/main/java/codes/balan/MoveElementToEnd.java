package codes.balan;

import java.util.Arrays;

//        Move Element To End
//        You're given an array of integers and an integer. Write a function that moves all instances of that integer
//        in the array to the end of the array and returns the array.
//        The function should perform this in place (i.e., it should mutate the input array) and doesn't need to maintain
//        the order of the other integers.
//
//        Sample Input
//        array [2, 1, 2, 2, 2, 3, 4, 2]
//        toMove = 2
//
//        Sample Output
//        [1, 3, 4, 2, 2, 2, 2, 2] // the numbers 1, 3, and 4 could be ordered different!

public class MoveElementToEnd {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveElementToEnd(new int[]{2,1,2,2,2,3,4,2}, 2)));
    }

    private static int[] moveElementToEnd(int[] arr, int toMove){
        int x = arr.length-1;
        for(int i=arr.length-1 ; i>=0 ; i--){
            if(arr[i]==toMove){
                swap(arr, x, i);
                x--;
            }
        }
        return arr;
    }
    private static void swap(int[] arr, int x, int i) {
        int temporal;
        temporal = arr[x];
        arr[x] = arr[i];
        arr[i] = temporal;
    }
}
