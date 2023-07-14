package com.codeschallenges;

import java.util.Arrays;
import java.util.Scanner;

//Change from prinln to print in line 12 suggested by Daniel Baykov for be inline where you enter your entry
//All the changes are in comments
public class Fibonacci {
    public static void main(String[] args) {
//        System.out.println("Enter how much number of fibonacci sequence you want to see:"); Change by Daniel
        System.out.print("Enter how much number of fibonacci sequence you want to see:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        printFibonacciNumber(n);
    }
    public static void printFibonacciNumber(int n){
        if(n<=0){
            System.out.println("The number must be mayor than 0");
            return;
        }
        if(n == 1){
            System.out.println("0");
            return;
        }
        int[] fibonacciNumbers = new int[n];
        fibonacciNumbers[0]=0;
        fibonacciNumbers[1]=1;
        for(int i = 1; i<n-1; i++){
            fibonacciNumbers[i+1] = fibonacciNumbers[i] + fibonacciNumbers[i-1];
        }
        System.out.println(Arrays.toString(fibonacciNumbers));
    }
}
