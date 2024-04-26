class Solution {
    public int solution(String s) {
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        StringBuilder res = new StringBuilder(s);
        for(int i=0; i<words.length; i++) {
            while(res.indexOf(words[i]) > -1) {
                res.replace(res.indexOf(words[i]), res.indexOf(words[i])+words[i].length(), i+"");
            }
        }
        
        return Integer.parseInt(res.toString());
    }
}