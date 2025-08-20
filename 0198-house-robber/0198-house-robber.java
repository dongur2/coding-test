/*
    nums[i] = i번째 집에 있는 돈
    인접한 집은 털 수 없음
    >>> 하루에 털 수 있는 최대 액수
 */
class Solution {
    int result = 0;
    int[] pocket;

    public int rob(int[] nums) {
        pocket = new int[nums.length+1]; //시작점 추가 

        //시작점은 0아니면 1 (2가 되면 손해-0도 가능하니까..)
        //1,2번째는 최대값 고정
        //간격도 1아니면 2 (3 되면 중간에 하나 버리는거니까..)
        for(int i=1; i<=nums.length; i++) {
            if(i < 3) pocket[i] = nums[i-1];
            else pocket[i] = Math.max(pocket[i-2]+nums[i-1], pocket[i-3]+nums[i-1]);
            result = Math.max(pocket[i], result);
        }

        return result;
    }
}