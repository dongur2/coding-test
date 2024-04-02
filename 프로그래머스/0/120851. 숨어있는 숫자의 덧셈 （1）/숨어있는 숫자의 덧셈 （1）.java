class Solution {
    private int myCode(String my_string) {
        int answer = 0;
        
        my_string = my_string.replaceAll("[A-Za-z]", "");
        for(int i=0; i<my_string.length(); i++) {
            answer += Integer.parseInt(my_string.charAt(i)+"");
        }
        
        return answer; 
    }
    
    private int referTo(String my_string) {
        int answer = 0;
        
        my_string = my_string.replaceAll("[^0-9]", "");
        for(char ch : my_string.toCharArray()) {
            answer += Character.getNumericValue(ch);
        }
        
        return answer;
    }
    
    public int solution(String my_string) {
        return referTo(my_string);
    }
}