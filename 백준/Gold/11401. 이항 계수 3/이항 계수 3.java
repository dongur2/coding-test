import java.io.*;
import java.util.*;

public class Main {
  static final int MOD = 1000000007;
  static long[] factorial;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    factorial = new long[n+1];
    /*
    nCr = n! / (n-r)!r!
    */
    //팩토리얼 계산
    //값이 너무 커지기 때문에 계속해서 MOD로 넘치는 값을 없애주기
    factorial[0] = 1;
    for(int i=1; i<=n; i++) {
      factorial[i] = factorial[i-1] * i % MOD;
    }
    
    //조합
    long top = factorial[n]; //n!
    long bottom = factorial[n-k] * factorial[k] % MOD; //(n-r)!k!
    long ans = (top * pow(bottom, MOD - 2)) % MOD; //(n-r)!k!을 역수로 
    System.out.println(ans);
  }
  
  public static long pow(long base, long exp) {
    long result = 1;
    while (exp > 0) {
      if (exp % 2 == 1) result = (result * base) % MOD;
      base = (base * base) % MOD;
      exp /= 2;
    }
    return result;
  }
}
