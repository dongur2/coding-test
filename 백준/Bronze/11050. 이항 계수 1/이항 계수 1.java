import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] comb = new int[11][11];   
   
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    System.out.println(makeComb(comb, n, k));
  }
  
  public static int makeComb(int[][] comb, int n, int r) {
    if (n < r) return 0;
    if(n == r || r == 0) return 1;
    if(comb[n][r] > 0) return comb[n][r];
    
    return comb[n][r] = makeComb(comb, n-1, r-1) + makeComb(comb, n-1, r);
  }
}