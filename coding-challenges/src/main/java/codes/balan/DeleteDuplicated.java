package codes.balan;

import java.util.Arrays;

public class DeleteDuplicated {
    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 3, 3, 4, 5, 6, 7, 8, 9, 9, 9};
       //ordenar
        for(int i=0; i<arr.length-1;i++){
            for(int e=i+1; e<arr.length;e++){
                if(arr[i]>arr[e]){
                    int temp=arr[i];
                    arr[i]=arr[e];
                    arr[e]=temp;
                }
            }
        }
        //quitar repetidos
        int x = 1;
        int[] arr2= new int[arr.length];
        arr2[0]=arr[0];
        for(int i=0; i<arr.length;i++){
            if(arr2[x-1]==arr[i]){
             continue;
            }
            arr2[x]=arr[i];
            x++;
        }

        arr2 = Arrays.copyOf(arr2, x);


//solo imprimir
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));

    }

//    private static int[] quitarDuplicados(int[] arre){
//        for(int i=0; i<arre.length;i++){
//            for(int e=1; e<arre.length-1;e++){
//                if(arre[e]<arre[e+1]){
//                    int temp=arre[e];
//                    arre[e]=arre[e+1];
//                    arre[e+1]=temp;
//                }
//            }
//        }
//        return arre;
//    }
}
