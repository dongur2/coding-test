import java.util.*;

class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 2;
        
        for(String word : dic) {
            if(word.length() != spell.length) continue;
            
            for(int i=0; i<spell.length; i++) {
                if(word.indexOf(spell[i]) == -1 
                   || word.indexOf(spell[i]) != word.lastIndexOf(spell[i])) {
                    break;
                } else {
                    if(i == (spell.length-1)) {
                        answer = 1;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}