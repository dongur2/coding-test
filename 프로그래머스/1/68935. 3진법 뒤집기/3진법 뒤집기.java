class Solution {
    public int solution(int n) {
        StringBuilder converted = new StringBuilder();
        
        while(n > 0) {
            converted.append(n % 3);
            n /= 3;
        }
        
        System.out.println(converted.toString());
        
        return Integer.parseInt(converted.toString(), 3);
    }
}