import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String s = br.readLine();
    
    int[] cnt = new int[2];
    
    if(s.length() > 0) {
      if(s.charAt(0) == '0') cnt[0]++;
      else cnt[1]++;
      
      for(int i=1; i<s.length(); i++) {
        char prev = s.charAt(i-1);
        char curr = s.charAt(i);
        
        if(prev != curr) {
          if(curr == '0') cnt[0]++; 
          else cnt[1]++;
        }
      }
    }

    int min = Math.min(cnt[0], cnt[1]);
    System.out.println(min);
  }
}