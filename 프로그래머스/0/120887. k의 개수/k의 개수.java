class Solution {
    private int useChar(int i, int j, int k) {
        int answer = 0;
        
        for(int num=i; num<=j; num++) {
            char[] nums = (num+"").toCharArray();
            for(char n : nums) {
                if(Character.getNumericValue(n) == k) answer++;
            }
        }
        
        return answer;
    }
    
    private int useMath(int i, int j, int k) {
        int answer = 0;
        
        for(int num=i; num<=j; num++) {
            int tempNum = num;
            while(tempNum > 0) {
                if(tempNum % 10 == k) answer++;
                tempNum /= 10;
            }
        }
        
        return answer;
    }
    
    public int solution(int i, int j, int k) {
        return useMath(i, j, k);
    }
}