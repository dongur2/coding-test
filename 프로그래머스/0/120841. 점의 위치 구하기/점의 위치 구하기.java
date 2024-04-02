class Solution {
    public int solution(int[] dot) {
        int mult = dot[0] * dot[1];
        if(mult > 0) {
            return (dot[0] > 0 && dot[1] > 0)? 1:3;
        } else {
            return (dot[0] < 0 && dot[1] > 0)? 2:4;
        }
    }
}