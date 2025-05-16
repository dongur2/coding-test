import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.io.*;

//저울을 한 번만 사용해 원하는 무게의 물을 그릇에 담기
//1~모든무게추의합(s)사이 모든 정수에 대응하는 물을 그릇에 담기 시도 - 한번에 담을 수 없는 수??
public class Main {
  static int k;
  static int[] weights;
  
  //한번에 만들 수 있는 무게 
  static Set<Integer> possibleWeights = new HashSet<>();
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //추 개수 <= 13
    k = Integer.parseInt(br.readLine());
    weights = new int[k];
    
    //모든 추의 합
    int s = 0;
    
    //각 추의 무게 
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<k; i++) {
      weights[i] = Integer.parseInt(st.nextToken());
      s += weights[i];
    }
    
    //저울을 한 번만 사용해서 만들 수 있는 모든 물의 무게 저장 
    dfs(0, 0, 0);
    
    int count = 0;
    for (int i=1; i<=s; i++) {
        if (!possibleWeights.contains(i)) count++;
    }
    System.out.println(count);
  }
  
  //[왼쪽]무게추 : [오른쪽]무게추 (+물)
  public static void dfs(int idx, int left, int right) {
    //무게추를 모두 사용했을 경우 
    if (idx == k) {
      int water = Math.abs(left - right); //왼쪽 무게에서 오른쪽 무게를 뺀 게 물의 무게
      if (water > 0) possibleWeights.add(water);
      return;
    }
    
    //idx번째 추를 왼쪽에 올림
    dfs(idx+1, left+weights[idx], right);

    //오른쪽에 올림
    dfs(idx+1, left, right+weights[idx]);

    //사용하지 않음
    dfs(idx+1, left, right);
  }
}