package codes.balan;

import java.util.*;

public class ComvertIntIntoNR {

    static List<Tupple> er = new ArrayList<>();
    static {
        er.add(new Tupple("I", 1));
        er.add(new Tupple("IV", 4));
        er.add(new Tupple("V", 5));
        er.add(new Tupple("IX", 9));
        er.add(new Tupple("X", 10));
        er.add(new Tupple("XL", 40));
        er.add(new Tupple("L", 50));
        er.add(new Tupple("XC", 90));
        er.add(new Tupple("C", 100));
        er.add(new Tupple("CD", 400));
        er.add(new Tupple("D", 500));
        er.add(new Tupple("CM", 900));
        er.add(new Tupple("M", 1000));
    }

    public static void main(String[] args) {
        List<String> romanos = arabicToRomans(Arrays.asList(1, 45, 786, 432, 75, 40, 39, 53));
        System.out.println(romanos);
    }



    public static List<String> arabicToRomans(List<Integer> numbers) {
        Collections.sort(er);
        List<String> romanos = new ArrayList<>();
        for (Integer num : numbers) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i< er.size(); i++){
                if (num>= er.get(i).value){
                    while(num>= er.get(i).value){
                        num=num-er.get(i).value;
                        sb.append(er.get(i).key);
                    }
                }
            }
            romanos.add(sb.toString());
        }
        return romanos;
    }



    public static class Tupple implements Comparable {
        public final String key;
        public final int value;

        public Tupple(String key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(((Tupple)o).value, this.value);
        }

        @Override
        public String toString() {
            return key + " -> " + + value;
        }
    }
}
