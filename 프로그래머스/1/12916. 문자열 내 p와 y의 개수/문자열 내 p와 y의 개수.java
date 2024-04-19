class Solution {
    boolean solution(String s) {
        int p = 0, y = 0;
        
        for(String ch : s.toLowerCase().split("")) {
            if(ch.equals("p")) p++;
            if(ch.equals("y")) y++;
        }
        
        return (p == y)? true : false;
    }
}