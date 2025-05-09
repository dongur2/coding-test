import java.util.*;
import java.io.*;

//블루레이의 최소 길이 
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //n개의 강의를 m개 블루레이에 저장 
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    long total = 0L; //총 영상 길이의 합 
    long maxLen = Long.MIN_VALUE; //영상 중 가장 긴 영상 길이 
    
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      total += arr[i];
      maxLen = Math.max(maxLen, arr[i]);
    }
    
    long left = maxLen; //블루레이 길이 최소값 
    long right = total; //블루레이 길이 최대값 
    long answer = total; //정답(최소 길이)
    
    while(left<=right) {
      long mid = (left+right)/2; //중간 길이  
      
      int cnt = count(arr, n, mid); //사용한 개수 
      
      //사용한 블루레이 개수가 더 적거나 같으면, 길이를 줄여야 함 -> 왼쪽  
      if(cnt <= m) {
        answer = mid; //일단 저장하고
        right = mid-1; //길이를 더 줄일 수 있는지 확인
      }
      
      //사용한 블루레이 개수가 더 많으면, 길이를 늘려야 함 -> 오른쪽 
      else left = mid+1;
    }
    
    //최소
    System.out.println(answer);
  }
  
  //길이가 len일 때 필요한 블루레이 수 
  public static int count(int[] arr, int n, long len) {
    int cnt = 1;
    
    int l=0;
    long sum = 0L;
    
    for(int i=0; i<n; i++) {
      if (sum + arr[i] > len) {
        cnt++; //새 블루레이
        sum = 0; //리셋
      }
      sum += arr[i]; //저장
    }
    
    return cnt;
  }
}