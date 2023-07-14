package codes.balan;
//        SpiralTraverse
//        Write a function that takes in an n xm two-dimensional array (that can be square-shaped when nm)
//        and returns a one-dimensional array of all the array's elements in spiral order.
//        Spiral order starts at the top left comer of the two-dimensional array, goes to the right, and
//        proceeds in a spiral pattern all the way until every element has been visited.
//        1 import
//
//        Sample Input
//        array[ [1, 2, 3, 4],
//
//        [12, 13, 14, 5),
//
//        [11, 16, 15, 6), [10, 9, 8, 7],
//
//        Sample Output
//        [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
public class SpiralTraverse {
    public static void main(String[] args) {
//        {{1, 2, 3, 4},
//            {10, 11, 12, 5},
//            {9, 8, 7, 6}};
//        {{1, 2, 3,},
//            { 8, 9, 4},
//            { 7, 6, 5}};
        int[][] arr={{1, 2, 3, 4},
                  {10, 11, 12, 5},
                 {9, 8, 7, 6}};
        int delimitadorizquierdo=-1;
        int delimitadorderecho=arr[0].length;
        int delimitadorarriba=0;
        int delimitadorabajo=arr.length;
        int fila=0;
        int columna=0;
        boolean b=false;
        String s = "";
        while(true) {
            if (delimitadorizquierdo + 1 != delimitadorderecho) {
                for (fila = delimitadorizquierdo+1; fila < delimitadorderecho; fila++) {
                    s = s + "," + arr[columna][fila];
                }
                delimitadorderecho--;
                fila--;
            }else{
                break;
            }
            if (delimitadorarriba + 1 != delimitadorabajo) {
                for (columna = delimitadorarriba+1; columna < delimitadorabajo; columna++) {
                    s = s + "," + arr[columna][fila];
                }
                delimitadorabajo--;
                columna--;
            }else{
                break;
            }
            if (delimitadorderecho - 1 != delimitadorizquierdo) {
                for (fila = delimitadorderecho-1; fila > delimitadorizquierdo-1 && fila>=0; fila--) {
                    s = s + "," + arr[columna][fila];
                }
                delimitadorizquierdo++;
                fila++;
            }else{
                break;
            }
            if (delimitadorabajo - 1 != delimitadorarriba) {
                for (columna = delimitadorabajo-1; columna > delimitadorarriba && columna>=0; columna--) {
                    s = s + "," + arr[columna][fila];
                }
                delimitadorarriba++;
                columna++;
            }else{
                break;
            }
        }
        String[] arrfinal=s .split(",");
        for(int i=0;i<arrfinal.length;i++){
            System.out.print(arrfinal[i]+" ");
        }
    }
}
