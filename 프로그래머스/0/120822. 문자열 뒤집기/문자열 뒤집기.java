class Solution {
    private String myCodeWithString(String my_string) {
        char[] chars = my_string.toCharArray();
        
        String answer = "";
        for(int i = chars.length - 1; i >= 0; i--) {
            answer += chars[i];
        }
        return answer;
    }
    
    private String useStringBuilder(String my_string) {
        StringBuilder sb = new StringBuilder();
        sb.append(my_string);
        return sb.reverse().toString();
    }
    
    public String solution(String my_string) {
        return useStringBuilder(my_string);
    }
}