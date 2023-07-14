package codes.balan;

public class DescifradoFBI {
    public static void main(String[] args) {
         String word ="bvqmjhgghjmqvbiqzjugthwmdv";
         System.out.println(decrypt(word));

    }

    static String decrypt(String word){
        int SMALL_A = 97;
        StringBuilder decrypted = new StringBuilder();
        int prev=1;
        for(char i : word.toCharArray()){
            int curr = i-prev;
            while(curr < SMALL_A){
                curr=curr+26;
            }
            decrypted.append((char)curr);
            prev = curr+prev;
        }
        return decrypted.toString();
    }

//    static String decrypt(String word){
//        byte[] bytes = word.getBytes();
//        int elementoAnterior=0;
//        int contador=0;
//        elementoAnterior=((int) (bytes[0]));
//        for(int i=1;i<bytes.length;i++){
//            int elementoActual = ((int) (bytes[i]));
//            elementoActual=elementoActual+(26*contador);
//            while(true){
//                contador++;
//                if(97<elementoActual-elementoAnterior)
//                    break;
//                elementoActual=elementoActual+26;
//            }
//            //
//            elementoActual= elementoActual-elementoAnterior;
//            bytes[i]= ((byte)elementoActual);
//            elementoAnterior =elementoActual+elementoAnterior;
//        }
//        elementoAnterior=((int) (bytes[0]))-1;
//        bytes[0]=((byte)elementoAnterior);
//        String newword = new String(bytes);
//        return newword;
//    }
//    static String encrypt(String word){
//        byte[] bytes = word.getBytes();
//        bytes[0]= (byte) (bytes[0]+1);
//        int[] ibytes = new int[bytes.length];
//        for(int i=0;i<ibytes.length;i++){
//            ibytes[i]=((int) (bytes[i]));
//        }
//        for(int i=1;i<ibytes.length;i++){
//            ibytes[i]+=ibytes[i-1];
//        }
//        for (int i=0; i<ibytes.length;i++){
//            while(ibytes[i]>122){
//                ibytes[i]= ibytes[i]-26;
//            }
//        }
//        for(int i=0;i<bytes.length;i++){
//            bytes[i]=((byte) (ibytes[i]));
//        }
//        String newword = new String(bytes);
//        return newword;
//    }

}
