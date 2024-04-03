class Solution {
    public String solution(String letter) {
        String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.",
        "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
        ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--","--.."};

        StringBuilder answer = new StringBuilder();
        
        String[] letterArr = letter.split(" ");
        for(String ch : letterArr) {
            for(int i=0; i<morseCode.length; i++) {
                if(ch.equals(morseCode[i])) {
                    answer.append((char)(i+97));
                    break;
                }
            }
        }
        
        return answer.toString();
    }
}