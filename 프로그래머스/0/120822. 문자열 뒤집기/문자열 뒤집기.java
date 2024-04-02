class Solution {
    private String myCodeWithString(String my_string) {
        String answer = "";
        for(int i = my_string.length() - 1; i >= 0; i--) {
            answer += my_string.charAt(i);
        }
        return answer;
    }
    
    private String useStringBuilder(String my_string) {
        StringBuilder sb = new StringBuilder();
        sb.append(my_string);
        return sb.reverse().toString();
        
        // return new StringBuilder(my_string).reverse().toString();
    }
    
    public String solution(String my_string) {
        return useStringBuilder(my_string);
    }
}