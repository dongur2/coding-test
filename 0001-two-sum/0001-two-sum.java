class Solution {
    /*
    Q. 주어지는 숫자 배열 중 더하면 주어지는 숫자(target)가 되는 '두 숫자의 인덱스'를 포함한 배열을 반환
    - 같은 인덱스의 숫자를 두 번 더할 수는 없음
    - 더하면 target이 되는 숫자쌍은 오직 1개
    */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        
        //투포인터로 하나씩 확인
        for(int a=0; a<nums.length-1; a++) {
            for(int b=a+1; b<nums.length; b++) {
                if(nums[a] + nums[b] == target) {
                    result[0] = a; result[1] = b; break;
                }
            }
        }
        
        return result;
    }
}