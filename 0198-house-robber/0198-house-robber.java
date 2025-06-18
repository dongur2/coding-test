import java.util.stream.*;

//붙어있는 집을 방문하지 않고 최대로 털 수 있는 금액
class Solution {
    public int rob(int[] nums) {
        int cnt = nums.length; //집 개수 
        
        int[] res = new int[cnt]; //그 집까지 오는 데 모은 최대 금액 저장
        Arrays.fill(res, Integer.MIN_VALUE);

        for(int i=0; i<cnt; i++) {
            if(i == 0 || i == 1) res[i] = nums[i]; //0번째, 1번째는 시작점이 되므로 해당 금액만 저장  
            else if(i == 2) res[i] = Math.max(res[i-2] + nums[i], nums[i]); //2번째는 0->2, 또는 2에서 시작만 가능
            
            else res[i] = Math.max(res[i-2], res[i-3]) + nums[i]; //그 후로는 2번째 전, 3번째 전과 비교 
        }

        return Arrays.stream(res).max().getAsInt();
    }
}