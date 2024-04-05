class Solution {
    public long solution(String numbers) {
        String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i=0; i<nums.length; i++) {
            numbers = numbers.replace(nums[i], i+"");
        }
        
        return Long.parseLong(numbers);
    }
}