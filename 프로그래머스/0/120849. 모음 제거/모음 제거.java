class Solution {
    private String myCode(String my_string) { // 76.5MB, 0.11ms
        String[] vowels = {"a", "e", "i", "o", "u"};
        
        String answer = my_string;
        for(String vowel : vowels) {
            answer = answer.replace(vowel, "");
        }
        return answer;
    }
    
    private String useRegex(String my_string) {
        return my_string.replaceAll("[aeiou]", "");
    }
    
    public String solution(String my_string) {
        return myCode(my_string);
    }
}