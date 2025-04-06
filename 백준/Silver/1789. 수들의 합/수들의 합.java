import java.util.*;
import java.io.*;

public class Main {
    //주어진 숫자가 합이 되는 서로 다른 숫자로 이루어진 수열의 최대 길이
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      long answer = Long.MIN_VALUE;
      long s = Long.parseLong(br.readLine());
      
      long sum = 0, idx = 0;
      while(sum <= s) {
          idx++;
          sum += idx;
      }
      
      System.out.print(idx - 1);
  }
}