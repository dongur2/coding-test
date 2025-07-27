//t로부터 s를 만들 수 있는지 
class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0 && t.length() == 0) return true;
        else if(s.length() == 0) return true;
        else if(t.length() == 0) return false;

        int idx = 0; //s 인덱스 

        //t 문자를 앞에서부터 하나씩 탐색 - s 인덱스 문자와 같으면 s인덱스 추가 -> 다음 글자와 비교
        for(char c:t.toCharArray()) {
            if(idx == s.length()) return true;
            if(c == s.charAt(idx)) idx++;
        }
        
        //s문자 모두 찾았으면 true 
        return idx == s.length();
    }
}