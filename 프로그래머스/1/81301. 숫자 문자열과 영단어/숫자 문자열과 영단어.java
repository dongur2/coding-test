class Solution {
    private String useStringBuilder(String s, String[] words) {
        StringBuilder res = new StringBuilder(s);
        for(int i=0; i<words.length; i++) {
            while(res.indexOf(words[i]) > -1) {
                res.replace(res.indexOf(words[i]), res.indexOf(words[i])+words[i].length(), i+"");
            }
        }
        return res.toString();
    }
    
    private String useString(String s, String[] words) {
        for(int i=0; i<words.length; i++) {
            s = s.replaceAll(words[i], i+"");
        }
        return s;
    }
    
    public int solution(String s) {
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        return Integer.parseInt(useString(s, words));        
    }
}