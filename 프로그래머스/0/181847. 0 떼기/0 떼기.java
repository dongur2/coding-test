class Solution {
    public String solution(String n_str) {
        char[] chars = n_str.toCharArray();
        
        for(int i=0; i<chars.length; i++) {
            if(chars[i] != '0') break;
            else chars[i] = ' ';
        }
        
        return String.valueOf(chars).trim();
    }
}