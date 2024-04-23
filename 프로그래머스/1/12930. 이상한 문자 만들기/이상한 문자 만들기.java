class Solution {
    private String my(String s) {
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
    
    private String useChar(String s) {
        char[] chars = s.toCharArray();
        
        int idx = 0;
        for(int i=0; i<chars.length; i++) {
            idx = (chars[i] == ' ')? 0 : idx+1;
            chars[i] = (idx % 2 == 0)? Character.toLowerCase(chars[i]) : Character.toUpperCase(chars[i]);
        }
        
        return String.valueOf(chars);
    }    
    public String solution(String s) {
        return useChar(s);
    }
}