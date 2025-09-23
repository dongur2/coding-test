import java.util.Queue; import java.util.PriorityQueue;
import java.io.*;

//로프를 이용해서 들 수 있는 물체의 최대 중량 
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    int n = Integer.parseInt(br.readLine());
    
    int cnt = 0;
    Queue<Integer> pq = new PriorityQueue<>();
    
    for(int i=0; i<n; i++) {
      int r = Integer.parseInt(br.readLine());
      pq.offer(r);
      cnt++;
    }
    
    int answer = 0;
    
    while(!pq.isEmpty()) {
      int weight = pq.poll() * cnt;
      answer = Math.max(answer, weight);
      cnt--;
    }
    
    System.out.println(answer);
  }
}