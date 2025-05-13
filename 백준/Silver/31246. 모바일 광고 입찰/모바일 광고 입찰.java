import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //모로코가 입찰가를 일괄적으로 X만큼 올렸을 때, 최소 k개 이상 지면을 낙찰받는 최소 X 출력 
    //최고 입찰가 -> 해당 지면 낙찰 
    //인풋: 모로코입찰가 최고가 
    //제시된 가격이 같을 경우 모로코 낙찰 
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    int x = 0; //상승치
    int cnt = 0; //k이상이 되어야 만족 
    
    //모로코가 낙찰받지 못했을 경우 최고가와 차이가 적은 순서대로 정렬
    PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> {
      return Math.abs(a[1]-a[0]) - Math.abs(b[1]-b[0]);
    });
    
    //비교
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int o = Integer.parseInt(st.nextToken());
      
      //모로코가 더 높은 가격/같은 가격 제시 -> 낙찰
      if(m >= o) cnt++;
      
      //모로코가 더 낮으면 큐에 저장 
      else q.offer(new int[]{m, o});
    }
    
    //k개 이상이면 중지 
    if(cnt >= k) {
      System.out.println(x); return;
    }
    
    //k개 미만이면 가격 상승 필요
    while(cnt < k && !q.isEmpty()) {
      int[] curr = q.poll(); 
      cnt++;
      x = curr[1]-curr[0];
    }
    
    System.out.println(x);
  }
}