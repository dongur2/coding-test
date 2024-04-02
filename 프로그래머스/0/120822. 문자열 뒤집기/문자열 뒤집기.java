class Solution {
    public String solution(String my_string) {
        char[] chars = my_string.toCharArray();
        
        String answer = "";
        for(int i = chars.length - 1; i >= 0; i--) {
            answer += chars[i];
        }
        
        return answer;
    }
}