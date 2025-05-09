import java.util.*;
import java.io.*;

//B배열의 k번째 수 출력 
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //배열 크기 n (10^5)
    //k (10^10)
    //A배열을 모두 만들면 초과 
    
    int n = Integer.parseInt(br.readLine());
    long k = Long.parseLong(br.readLine());
    
    //A배열의 i*j값을 오름차순으로 정렬한게 B배열 
    //그 B배열 중 k번째 값을 리턴 

    //최소값 1, 최대값 (i*j) - 정렬했을 때 k번째 수는 k보다 클 수 없으므로 k
    long left = 1, right = k;
    long result = 0;
    
    while (left <= right) {
      long mid = (left + right) / 2; //중간값 
      long cnt = count(mid, n);

      //k개보다 개수가 많으면 mid는 더 작아야 함 -> 왼쪽 범위로
      if (cnt >= k) {
        result = mid;
        right = mid - 1;
      //k보다 개수가 적으면 mid는 더 커야함 -> 오른쪽 범위로 
      } else left = mid + 1;
    }
    
    System.out.println(result);
  }
  
  //mid 이하 숫자 개수 
  public static long count(long mid, int n) {
      long cnt = 0;
      for (int i = 1; i <= n; i++) {
          cnt += Math.min(mid / i, n); // mid는 i*j인 값이니까, 나온 j -> j개가 mid 이하 개수  (오름차순) 
      }
      return cnt;
  }
  
}