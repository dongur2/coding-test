import java.util.StringTokenizer;
import java.io.*;

//제일 왼쪽 도시에서 제일 오른쪽 도시로 이동하는 최소 비용  
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    //도시 간 거리
    int[] len = new int[n-1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<n-1; i++) {
      len[i] = Integer.parseInt(st.nextToken());
    }
    
    //각 도시의 리터당 가격 
    int[] city = new int[n];
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      city[i] = Integer.parseInt(st.nextToken());
    }
    
    int totalCost = 0;
    int minPrice = city[0]; //가장 싼 가격 

    for (int i=0; i<n-1; i++) {
        if (city[i] < minPrice) minPrice = city[i]; //가격 업데이트 
        totalCost += minPrice * len[i]; //싼 가격으로 주유 
    }

    System.out.println(totalCost);
  }
}