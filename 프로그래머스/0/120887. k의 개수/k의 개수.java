class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        
        for(int num=i; num<=j; num++) {
            char[] nums = (num+"").toCharArray();
            for(char n : nums) {
                if(Character.getNumericValue(n) == k) answer++;
            }
        }
        
        return answer;
    }
}