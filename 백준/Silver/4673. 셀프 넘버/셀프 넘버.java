import java.util.*;
import java.io.*;

//10000이하 셀프 넘버 출력 
public class Main {
  //셀프 넘버: 생성자가 없는 숫자
  //양의 정수 N -> d(N) = N + N의 각 자리수 => "N"은 d(N)의 "생성자"
  //d(33) = 33+3+3 = 39, d(39) = 39+3+9 = 51
  public static void main(String[] args) throws IOException{
      //1+1 = 2, 2+2=4, 3+3=6, 4+4=8, 5+5=10, 10+1+0=11, 6+6=12, 7+7=14, 8+8=16, 16+1+6=23...
      int answer = 1;
      
      //생성자가 되는 수를 모두 저장
      Set<Integer> set = new HashSet<>();
      while(answer < 10001) {
        set.add(maker(answer++));
      }
      
      //생성자인 수를 제외하고 출력
      for(int num=1; num<10001; num++) {
        if(!set.contains(num)) System.out.println(num);
      }
  }
  
  //한자리 수일 경우 return n+ n(n%10)
  //두자리 수일 경우 return n + n%10... 
  public static int maker(int n) {
    int answer = n;
    
    while(n > 0) {
      answer += n % 10;
      n /= 10;
    }
    
    return answer;
  }
}