package aeds;

public class InsertSort {

    public static void main(String[] args) {
    int i = 0;
    int j = 1;
    int aux = 0;
    int v[] = {7,2,1,4,3};
     

    while(j < v.length){
      aux = v[j];
      i = j - 1;
      while((i >= 0 ) && (v[i] > aux)){
        v[i + 1] = v[i];
        i = i - 1;
      }
      v[i + 1] = aux;
      j = j + 1;
    }
        for (int k = 0; k < v.length; k++) {
            System.out.print(v[k]);          
        }
        System.out.println(" ");
  }
}   
   
