class Solution {
    private String myCode(String my_string) { // 75.9MB, 0.05ms
        String[] vowels = {"a", "e", "i", "o", "u"};
        
        String answer = my_string;
        for(String vowel : vowels) {
            answer = answer.replace(vowel, ""); // replace(String, String) 
        }
        return answer;
    }
    
    private String useRegex(String my_string) {
        return my_string.replaceAll("[aeiou]", "");
    }
    
    public String solution(String my_string) {
        return useRegex(my_string);
    }
}