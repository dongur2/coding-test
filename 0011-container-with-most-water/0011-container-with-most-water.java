/*
    길이가 n인 숫자 배열(높이)이 주어졌을 때, n개의 세로 그래프는 i번째 그래프는 (i,0), (i, height[i]) 점을 끝으로 한다.
    가장 많은 물을 담을 수 있는 두 축을 골라서 가장 많은 물의 양을 리턴한다.

    - 가로가 가장 긴 두 그래프
    - 세로가 가장 긴 두 그래프 
    - 가로*세로(두 그래프 높이 중 짧은 쪽)가 가장 큰 두 그래프 
 */
class Solution {
    public int maxArea(int[] height) {
        int maxWater = Integer.MIN_VALUE;

        int left = 0, right = height.length-1;
        
        while(left < right) {
            int w = right - left; //폭 
            int h = Math.min(height[left], height[right]); //두 그래프 중 짧은 쪽 
            int water = w * h; //담을 수 있는 물의 양
            maxWater = Math.max(maxWater, water);

            if(height[left] < height[right]) left++;
            else right--;
        }


        return maxWater;
    }
}