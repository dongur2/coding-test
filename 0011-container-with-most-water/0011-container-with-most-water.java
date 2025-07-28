//가장 많이 담을 수 있는 물의 양 
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int water = (right - left) * Math.min(height[left], height[right]);

        while(left < right) {
            if(height[left] < height[right]) left++;
            else right--;

            water = Math.max(water, (right-left) * Math.min(height[left], height[right]));
        }

        return water;
    }
}