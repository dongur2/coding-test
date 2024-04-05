class Solution {
    public int solution(int[] sides) {
        int longest = Math.max(sides[0], sides[1]);
        int twice = Math.min(sides[0], sides[1]);
        
        int count = 0;
        
        // 주어진 매개변수 중 긴 변을 가장 긴 변으로 삼을 경우
        for(int i=1; i<=longest; i++) {
            if(i+twice > longest) count++;
        }
        
        // 새로운 가장 긴 변을 찾을 경우
        for(int i=longest+1; i<twice+longest; i++) {
            count++;
        }
        
        return count;
    }
}