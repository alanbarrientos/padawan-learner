package com.codeschallenges;

import java.util.Arrays;
import java.util.Scanner;

//The names of the varibles was suggested by Alexandr Parpulanschi to be more descriptive
//All the changes are in comments
public class CheckInterval {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number:");
//        int p = scanner.nextInt(); before
        int firstEntry = scanner.nextInt();
        System.out.print("Enter second number:");
//        int q = scanner.nextInt(); before
        int secondEntry = scanner.nextInt();

        checkInterval(firstEntry, secondEntry);

    }
//        private static void checkInterval(int p, int q ){ before
    private static void checkInterval(int firstEntry, int secondEntry ){
//        int[] temporal = new int[2]; before
        int[] interval = new int[2];
//        interval[0]=p; before
        interval[0]=firstEntry;
//        interval[1]=q; before
        interval[1]=secondEntry;
        if(interval[0]<=interval[1]){
            System.out.println("You enter then number with correct interval: " + Arrays.toString(interval));
        }else{
            System.out.println("You enter: " + Arrays.toString(interval));
//            interval[0]=q;
            interval[0]=secondEntry;
//            interval[1]=p; before
            interval[1]=firstEntry;
            System.out.println("The correct interval is: " + Arrays.toString(interval));
        }
    }
}
