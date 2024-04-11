class Solution {
    public int solution(int[] common) {
        int answer = 0;
        
        // 등차수열
        if(Math.abs(common[1]-common[0]) == Math.abs(common[2]-common[1])) {
            answer = common[common.length-1] + (common[2]-common[1]);
        }
        
        // 등비수열
        if(Math.abs((double)common[1]/common[0]) == Math.abs((double)common[2]/common[1])) {
            answer = (int)(common[common.length-1] * ((double)common[1]/common[0]));
        }
        
        return answer;
    }
}