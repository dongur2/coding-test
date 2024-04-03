class Solution {
    private String myCode(int age) {
        String[] table = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

        StringBuilder answer = new StringBuilder();

        String stringAge = age+"";
        for(int i=0; i<stringAge.length(); i++) {
            String ch = table[(int)stringAge.charAt(i) - 48];
            answer.append(ch);
        }

        return answer.toString();
    }
    
    private String referTo(int age) {
        StringBuilder answer = new StringBuilder();
        
        while(age > 0) {
            answer.insert(0, (char)(age % 10 + 97));
            age /= 10;
        }
        
        return answer.toString();
    }
    
    public String solution(int age) {
        return referTo(age);
    }
}