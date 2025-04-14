import java.util.*;
import java.io.*;

//주어진 숫자 M개 중 숫자 카드 N개에서 보유 여부 
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      Set<Integer> set = new HashSet<>();
      
      //가지고있는 카드 
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
        int card = Integer.parseInt(st.nextToken());
        set.add(card);
      }
      
      StringBuilder sb = new StringBuilder();
      
      //주어진 숫자 
      int m = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<m; i++) {
        int num = Integer.parseInt(st.nextToken());
        int result = set.contains(num) ? 1:0;
        sb.append(result).append(" ");
      }
      
      System.out.println(sb);
  }
}