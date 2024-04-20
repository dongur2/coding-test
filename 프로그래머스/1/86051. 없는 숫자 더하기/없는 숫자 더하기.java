class Solution {
    public int solution(int[] numbers) {
        int sum = 0;
        
        for(int i=1; i<=9; i++) {
            int idx = -1;
            for(int j=0; j<numbers.length; j++) {
                if(i == numbers[j]) {
                    idx = i; 
                    break;
                }
            }
            sum += (idx == -1)? i:0;
        }
        return sum;
    }
}