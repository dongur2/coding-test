import java.util.*;
import java.io.*;

//번역기가 인식할 수 있는 '최대 문자열의 길이'
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine()); //인식가능한 알파벳 종류 최대 개수
    String string = br.readLine(); //문자열 (소문자만 포함) <= 10^5
    
    //문자열 중 최대 n 종류의 알파벳을 가진 <연속된 문자열>만 인식 
    //연속된 문자열에 속한 알파벳 종류가 n개 이하면 인식 
    
    int maxLength = 0, first = 0;
    Set<Character> set = new HashSet<>(); //포함된 알파벳 
    
    //10^5
    for(int end = first; end<string.length(); end++) {
      set.add(string.charAt(end)); //글자 추가 
      
      //최대 종류와 일치 -> 현재 길이를 저장된 길이와 비교해서 더 긴 길이를 저장 
      if(set.size() <= n) maxLength = Math.max(maxLength, end-first+1); 
      
      //최대 종류 초과 -> 출발 인덱스 뒤로, 해당하는 글자 확인해서 더 이상 없으면 삭제 
      if(set.size() > n) {
        if(string.substring(first+1, end+1).indexOf(string.charAt(first)) == -1) set.remove(string.charAt(first)); 
        first++;
      }
    }
    
    System.out.println(maxLength);
  }
}