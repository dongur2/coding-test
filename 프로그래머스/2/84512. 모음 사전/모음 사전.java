/*
    주어진 단어가 사전에서 몇 번째 단어인지?
    
    [사전]
    - 길이 5 이하
    - AEIOU만 사용
    - 짧은 길이 우선, 내림차순
*/
class Solution {
    int answer = 0;
    
    String[] arr = new String[]{"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        make(word, "");
        return answer;
    }
    
    boolean make(String word, String str) {
        if(word.equals(str)) return true;
        
        if(str.length() == 5) return false;
            
        for(int i=0; i<5; i++) {
            answer++;
            boolean isDone = make(word, str+arr[i]);
            if(isDone) return true;
        }
        
        return false;
    }
}