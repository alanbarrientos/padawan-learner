package com.intermediate.practices.finalexercise;


public abstract class Person {
    private String name;
    private String nickname;
    private int yearOfBorn;
    private String expertIn;
    private Item[] items;
    public Person(String name, String nickname, int yearOfBorn, String expertIn, Item[] items) {
        this.name = name;
        this.nickname = nickname;
        this.yearOfBorn = yearOfBorn;
        this.expertIn = expertIn;
        this.items = items;
    }
    public void printBioData() {
        System.out.println("The name is: " + name + " nickname: " + nickname);
        System.out.println("Year of born: " + yearOfBorn);
        System.out.println("Its expert in: " + expertIn);
        System.out.println("Items that have:");
        for(int i=0 ; i<items.length; i++) {
            System.out.println("-" + items[i].getName());
        }
    }
    public String getName() {
        return name;
    }
    public String getNickname() {
        return nickname;
    }
}
