package codes.balan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//        Non-Constructive Change
//        Given an array of positive integers representing the values of coins in your
//        possession, write a function that returns the minimum amount of change (the
//        minimum sum of money) that you
//        Given an array of positive integers representing the values of coins in your
//        possession, write a function that returns the minimum amount of change (the
//        minimum sum of money) that you
//        For example if you're given  coin = [1,2,5] the minimum amount of change
//        that you can't create is 4 if you are given no coins the minimum amount of change is 1
//        coins = [5, 7, 1, 1, 2, 3, 22] then is 20

public class NonConstructiveChange {
    public static void main(String[] args) {
//        int result = getMinimunChangeSecondSolution(new int[]{1, 1, 3, 5, 7});
        int result = getMinimunChangeSecondSolution(new int[]{1, 1, 2, 3, 5, 7, 22});
//        int result = getMinimunChangeSecondSolution(new int[]{3, 6, 2, 5, 7});
//        int result = getMinimunChangeSecondSolutione(new int[]{3});

        System.out.println(result);
    }

    private static int getMinimunChangeSecondSolution(int[] coins) {
        List<Integer> coinsList = new ArrayList<Integer>();
        for (Integer coin: coins) {
            coinsList.add(coin);
        }
        coinsList = coinsList.stream().sorted().collect(Collectors.toList());

        int currentIndex = 0;
        int reverseIndex = 0;
        int sum = 0;
        int minimunChange = 1;

        while(currentIndex < coinsList.size()){
//            if the number is equal to the greates number of the array then we know that the lowest change is a number more than the sum of all coins
            if(minimunChange == coinsList.get(coins.length -1)){
                for (Integer coin : coinsList) {
                    minimunChange = minimunChange + coin;
                }
                minimunChange = minimunChange + 1;
                break;
            }

            if(coinsList.get(currentIndex) == minimunChange || sum == minimunChange){
                minimunChange++;
                reverseIndex = currentIndex;
                sum = 0;
                continue;
            }
            if(coinsList.size()>1){
                if(minimunChange >= coinsList.get(currentIndex+1)){
                    currentIndex++;
                    reverseIndex = currentIndex;
                    continue;
                }
            }
            if(reverseIndex < 0){
                break;
            }
            if(minimunChange >= sum + coinsList.get(reverseIndex)){
                sum = sum + coinsList.get(reverseIndex);
            }
                reverseIndex--;
        }

        return minimunChange;

    }
    private static int getMinimunChangeFirstSolution(int[] coins) {
        List<Integer> coinsList = new ArrayList<>();
        for (Integer coin: coins) {
            coinsList.add(coin);
        }
        List<Integer> indexes = new ArrayList();
        boolean flag = true;
        int currentIndex = 0;
        int lastIndex = coinsList.size();
        int minimunChange = 1;
        int sum = 0;
        coinsList = coinsList.stream().sorted().collect(Collectors.toList());
        if(!coinsList.contains(1)){
            return 1;
        }
        while(flag){
            if(minimunChange == coinsList.get(coins.length -1)){
                minimunChange = 1;
                for (Integer coin : coinsList) {
                    minimunChange = minimunChange + coin;
                }
                break;
            }
            if(lastIndex == 0){
                break;
            }
            sum = 0;
            for (Integer index: indexes) {
                sum = sum + coinsList.get(index);
            }
            if(minimunChange == sum + coinsList.get(currentIndex)){
                minimunChange++;
//                currentIndex = 0;
                lastIndex = coinsList.size();
                indexes.clear();
                continue;
            }

                if (coinsList.get(currentIndex) > minimunChange || coinsList.get(currentIndex) + sum > minimunChange) {
                    indexes.add(currentIndex - 1);
                    lastIndex = currentIndex - 1;
                    currentIndex = 0;
                    continue;
                }
                if (currentIndex == coinsList.size() - 1 || currentIndex == lastIndex - 1) {
                    indexes.add(currentIndex);
                    lastIndex = currentIndex;
                    currentIndex = 0;
                    continue;
                }
            currentIndex++;
        }
        return minimunChange;

    }



}
