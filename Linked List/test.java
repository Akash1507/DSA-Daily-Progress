import java.io.*;
import java.util.*;

public class test {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] chess = new int[n][n];
        String[][] result = new String[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result[i][j] = ".";
            }
        }
        ArrayList<ArrayList<Integer>> re = new ArrayList<ArrayList<Integer>>();
        printNQueens(chess,re,0,result);
        for(int i=0;i<n;i++){
            String st = "";
            for(int j=0;j<n;j++){
                st+=result[i][j];
            }
            System.out.println(st);
        }
    }

public static boolean isValid(int[][] chess,int row,int col){
    
        for(int i=row-1,j=col;i>=0;i--){
            if(chess[i][j]==1)
            return false;
        }

        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){
            if(chess[i][j]==1)
            return false;
        }

        for(int i=row-1,j=col+1;i>=0 && j<chess.length;i--,j++){
            if(chess[i][j]==1)
            return false;
        }
        return true;
    
    
}
    public static void printNQueens(int[][] chess, ArrayList<ArrayList<Integer>> re, int row,String[][] result) {
        if(row==chess[0].length){
            for(ArrayList<Integer> el:re){
                result[row][el.get(0)] = "Q";
            }
            return;
        }
        
        for(int c=0;c<chess.length;c++){
            
            if(isValid(chess,row,c)==true){
                chess[row][c] = 1;
                ArrayList<Integer> cres = new ArrayList<>();
                cres.add(c);
                re.add(cres);
                printNQueens(chess,re,row+1,result);
                chess[row][c] = 0;
            }
        }
        
        
    }
}



// 4
// [[0, 0], [1, 2], [1, 3], [2, 1], [0, 1], [1, 3], [2, 0], [3, 2]]
// [[0, 0], [1, 2], [1, 3], [2, 1], [0, 1], [1, 3], [2, 0], [3, 2], [0, 2], [1, 0], [2, 3], [3, 1]]


