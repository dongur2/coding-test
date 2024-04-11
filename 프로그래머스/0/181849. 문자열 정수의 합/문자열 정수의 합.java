class Solution {
    public int solution(String num_str) {
        int answer = 0;
        
        char[] num = num_str.toCharArray();
        for(char n : num) {
            answer += Character.getNumericValue(n);
        }
        
        return answer;
    }
}