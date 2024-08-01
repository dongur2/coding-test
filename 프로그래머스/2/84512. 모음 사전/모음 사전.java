import java.util.*;

class Solution {
    /*
    문제: 주어진 단어 word가 사전에서 몇 번째 단어인지 리턴
    - 사전에는 A, E, I, O, U 만 사용하여 만들 수 있는 길이 5 이하 모든 단어 수록
    - 첫번째 단어: A, 두 번째: AA, 마지막: UUUUU
    */
    String[] arr;
    int order = 0, answer = 0;
    
    public int solution(String word) {
        arr = new String[]{"A", "E", "I", "O", "U"};
        
        countOrder(word, 1, new StringBuilder());
        
        return answer;
    }
    
    private void countOrder(String word, int len, StringBuilder w) {
        //현재 길이가 6이상이면 재탐색 중지하고 리턴
        if(len > 5) return;
        
        //모음 배열 원소 처음부터 끝까지 순회:
        for(String a:arr) {
            //현재 글자 추가 - 순서 카운트
            w.append(a); //A1 AA2 AAA3 AAAA4 AAAAA5 6AAAA AAAAE5 AAAAI5 AAAAO5 AAAAU5 
            order++;
            
            if(word.equals(w.toString())) answer = order;
            
            //재탐색 - 길이 + 1 매개변수로 전달
            countOrder(word, len+1, w);
	        //현재 글자 제거
            w.deleteCharAt(w.length()-1);
        }
    }
}