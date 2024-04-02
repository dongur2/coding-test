class Solution {
    private String myCode(String my_string) { // 74.5MB, 0.06ms
        String[] vowels = {"a", "e", "i", "o", "u"};
        
        String answer = my_string;
        for(String vowel : vowels) {
            answer = answer.replace(vowel, ""); // replace(String, String) 
        }
        return answer;
    }
    
    private String useRegex(String my_string) { // 76.4MB, 0.15ms
        return my_string.replaceAll("[aeiou]", "");
    }
    
    public String solution(String my_string) {
        return myCode(my_string);
    }
}