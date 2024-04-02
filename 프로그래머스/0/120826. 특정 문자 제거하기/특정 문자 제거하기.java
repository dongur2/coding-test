class Solution {
    public String solution(String my_string, String letter) {
        StringBuilder answer = new StringBuilder(my_string);
        
        int index = -2;
        while(index != -1) {
            index = answer.indexOf(letter);
            
            if(index != -1) {
                answer.deleteCharAt(index);
            }
        }
        
        return answer.toString();
    }
}