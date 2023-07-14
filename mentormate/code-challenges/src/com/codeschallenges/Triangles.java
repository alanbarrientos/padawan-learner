package com.codeschallenges;

import java.util.Scanner;

//Add more spaces was suggested by Daniel Baykov to be more pretty
//All the changes are in comments
public class Triangles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a positive number less than 100:");
        boolean tryAgain;
        do {
            try{
                int n = Integer.parseInt(scanner.nextLine());
                printTwoTriangles(n);
                tryAgain = false;
            }catch (IllegalArgumentException e){
                tryAgain=true;
                System.out.println("Try Again please enter a positive number less than 100:");
            }
        }while (tryAgain);
    }
    public static void printTwoTriangles(int n){
        if(n>99 || n<0){
            throw new IllegalArgumentException();
        }
        int rowTriangleSize = n;
        StringBuilder spaces = new StringBuilder(" ");
        StringBuilder firstTriangle = new StringBuilder();
        for(int x=1; x<=rowTriangleSize; x++){
            if(x<10 && n>9){
//                firstTriangle.append(x + " ");    before
                firstTriangle.append(x + "  ");
            }else{
//                firstTriangle.append(x);          before
                firstTriangle.append(x + " ");
            }
        }
        StringBuilder secondTriangle = new StringBuilder(firstTriangle.toString());
        for(int i=0; i<n; i++){
            System.out.printf("%s%s%s\n", firstTriangle, spaces, secondTriangle);
            if(n>9){// This if is to fulfill the suggestion of Daniel
                firstTriangle.delete(firstTriangle.length()-3,firstTriangle.length());
                secondTriangle.delete(0,3);
                spaces.append("      ");
            }else {
                firstTriangle.delete(firstTriangle.length()-2,firstTriangle.length());
                secondTriangle.delete(0,2);
                spaces.append("    ");
            }
        }
    }
}
