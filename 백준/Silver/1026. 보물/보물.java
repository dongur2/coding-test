import java.util.*;
import java.io.*;

public class Main {
    //S의 최솟값
    //A, B 각 배열의 같은 자리에 있는 수를 곱한 값의 합
    //B는 고정, A는 재배열 가능
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      
      List<Integer> a = new ArrayList<>();
      List<Integer> b = new ArrayList<>();
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
        int m = Integer.parseInt(st.nextToken());
        a.add(m);
      }
      
      //A의 원소는 작은 순서대로 인덱스 지정 
      a.sort((a1, a2) -> a1 - a2);
      
      //B의 원소를 큰 순서대로 인덱스 지정
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
        int m = Integer.parseInt(st.nextToken());
        b.add(m);
      }
      
      b.sort((b1, b2) -> b2 - b1);
  
      //b의 큰 순서대로 a의 작은 순서 요소를 가져다 곱하기
      int sum = 0;
      for(int i=0; i<n; i++) {
        sum += a.get(i) * b.get(i);
      }
      
      System.out.println(sum);
  }
}