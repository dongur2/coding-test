class Solution {
    public String solution(String s) {
        String[] words = s.split(" ");

        StringBuilder answer = new StringBuilder();
        
        for(String word : words) {
            for(int i=0; i<word.length(); i++) {
                if(i % 2 == 0) answer.append(Character.toUpperCase(word.charAt(i)));
                else answer.append(Character.toLowerCase(word.charAt(i)));
            }
            answer.append(" ");
        }

        if(answer.length() < s.length()) answer.append(" ".repeat(s.length()-answer.length()));
        
        return (answer.length() > s.length())? 
            answer.deleteCharAt(answer.length()-1).toString() : answer.toString();
    }
}