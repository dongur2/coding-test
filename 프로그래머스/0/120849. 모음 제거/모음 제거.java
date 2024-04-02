class Solution {
    public String solution(String my_string) {
        String[] vowels = {"a", "e", "i", "o", "u"};
        
        StringBuffer answer = new StringBuffer(my_string);
        for(String vowel : vowels) {
            while(answer.indexOf(vowel) != -1) {
                answer.deleteCharAt(answer.indexOf(vowel));
            }
        }
        
        return answer.toString();
    }
}