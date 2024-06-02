
import java.util.*;


import static java.util.Collections.reverse;

public class Q1_D {

    static int dvirsMaxClique(int [][] G){
        int [] indexes = new int[G.length];
        int [] sumOf1 = new int[G.length];
        int counter= G.length-1;
        while (counter>=0) {
            for (int i = 0; i < G.length; i++) {
                sumOf1[i]*=10;
                sumOf1[i] =sumOf1[i]+G[counter][i];

            }
            counter--;
        }
        int []temp = sumOf1.clone();
        Arrays.sort(temp);

        counter=0;
            for(int i= G.length-1;i>0;i--){
                    for (int j=0;j< G.length;j++){
                        if(sumOf1[j] == temp[i]) {
                            indexes[counter] = i;
                            counter++;
                            break;
                    }
                }

        }
        int [][] newG = new int[G.length][G.length];
        for(int i=0;i< G.length;i++){
            for(int j=0;j< G.length;j++){
                newG[i][j] = G[indexes[i]][indexes[j]];
                if(i==j) newG[i][j]=1;
            }
        }
        for(int[] i :newG)
            System.out.println(Arrays.toString(i));

        int max=0;
        for (int i=0;i<newG.length;i++)
            if (newG[i][0]==0)
                break;
            else max++;
        int maxR=max;
        for(int i=1;i< maxR;i++) {
            for (int j = 0; j < maxR; j++) {
                if(newG[j][i]==0){
                    maxR = Math.max(i,j);
                }

            }
        }

        return maxR;
    }
    public static void main(String[] args){
        int [][][] allG = Q1_B.genAllMat(4);
        for (int[][] G : allG){
            System.out.println(dvirsMaxClique(G));
        }
    }
}
