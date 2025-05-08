import java.util.*;
import java.io.*;


public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int t = Integer.parseInt(br.readLine());
    
    for(int i=0; i<t; i++) {
      long n = Long.parseLong(br.readLine());
      long exp = n * (n + 1) / 2;
      System.out.println(getLevel(exp));
    }
  }
  
  public static long getLevel(long exp) {
    //가능한 최대 레벨
    long left = 1;
    long right = (long)Math.sqrt(exp) + 2; //범위
    long result = 1;

    while (left <= right) {
        long mid = (left + right) / 2;
        long needExp = mid * (mid - 1); // mid레벨까지 필요한 누적 경험치

        if (needExp <= exp) {
            result = mid;      
            left = mid + 1; // 더 높은 레벨 시도
        } else right = mid - 1; // 경험치 부족
    }

    return result;
  }
}