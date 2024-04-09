class Solution {
    public String solution(String polynomial) {
        int x = 0;
        int n = 0;
        
        String[] nums = polynomial.split(" ");
        for(String num : nums) {
            if(num.contains("x")) {
                if(num.charAt(0) == 'x') x++;
                else x += Integer.parseInt(num.substring(0, num.length()-1));
                
            } else if (!num.contains("+")) {
                n += Integer.parseInt(num);
            }
        }
        
        StringBuilder answer = new StringBuilder();
        if(x > 0) {
            if(x > 1) answer.append(x);
            
            answer.append("x");
            if(n > 0) answer.append(" + ");
        }
        
        if(n > 0) {
            answer.append(n);
        }
        
        return answer.toString();
    }
}