import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


//주어진 각 정수 m에 대해 가지고 있는 카드 개수
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      List<Integer> list = new ArrayList<>();
      
      //가지고 있는 카드 
      int n = Integer.parseInt(br.readLine());
      StringTokenizer cards = new StringTokenizer(br.readLine());
      
      //정수
      int m = Integer.parseInt(br.readLine());
      StringTokenizer nums = new StringTokenizer(br.readLine());
      int[] origin = new int[m];
      
      Map<Integer, Integer> map = new HashMap<>();
 
      //카드번호를 키에 저장
      //나중에 출력 순서 위해 원래 배열을 함께 저장
      for(int i=0; i<m; i++) {
        int num = Integer.parseInt(nums.nextToken());
        map.put(num, 0);
        origin[i] = num;
      }
      
      //카드에 들고있는 수 카운트 
      for(int i=0; i<n; i++) {
        int c = Integer.parseInt(cards.nextToken());
        if(map.containsKey(c)) map.put(c, map.get(c)+1);
      }
      
      StringBuilder sb = new StringBuilder();
      for(int k:origin) {
        sb.append(map.get(k)+" ");
      }
      System.out.println(sb);
  }
}