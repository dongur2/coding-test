import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        
        for(int i=0; i<4; i++) {
            for(int j=0; j<2; j++) {
                if(j==0 && !x.contains(dots[i][j])) x.add(dots[i][j]);
                if(j==1 && !y.contains(dots[i][j])) y.add(dots[i][j]);
            }
        }
        
        int width = Math.max(x.get(0), x.get(1)) - Math.min(x.get(0), x.get(1));
        int height = Math.max(y.get(0), y.get(1)) - Math.min(y.get(0), y.get(1));
        
        return width*height;
    }
}