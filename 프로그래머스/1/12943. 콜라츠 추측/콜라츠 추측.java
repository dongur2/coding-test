class Solution {
    public int solution(int num) {
        if(num == 1) return 0;

        long res = num;
        for(int i=1; i<=500; i++) {
            if(res % 2 == 0) {
                res /= 2;
            } else {
                res = res * 3 + 1;
            }
            
            if(res == 1) return i;
        }
        return -1;
    }
}