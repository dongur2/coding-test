class Solution {
    public String solution(String my_string) {
        StringBuilder answer = new StringBuilder(my_string);
        
        for(int i=0; i<my_string.length(); i++) {
            if((int)answer.charAt(i) >= 97) {
                answer.setCharAt(i, Character.toUpperCase(answer.charAt(i)));
            } else {
                answer.setCharAt(i, Character.toLowerCase(answer.charAt(i)));
            }
        }
        
        return answer.toString();
    }
}