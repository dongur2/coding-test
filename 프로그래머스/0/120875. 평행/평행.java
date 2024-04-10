class Solution {
    // 직선의 기울기: y2-y1 / x2-x1
    public int solution(int[][] dots) {
        int answer = 0;
        for(int i =0; i<dots.length;i++) {
            float line1 = gradient(dots[i], dots[(i+1)%4]); // [0][1] [1][2] [2][3] [3][0]
            float line2 = gradient(dots[(i+2)%4], dots[(i+3)%4]); // [2][3], [3][0], [0][1], [1][2]   

            if(line1==line2) answer = 1;
        }
        return answer;
    }
    
    public static float gradient(int[] a1,int[] a2) {
        float denom,mole;

        denom= a1[0]-a2[0];
        mole= a1[1]-a2[1];

        return mole/denom;
    }
}