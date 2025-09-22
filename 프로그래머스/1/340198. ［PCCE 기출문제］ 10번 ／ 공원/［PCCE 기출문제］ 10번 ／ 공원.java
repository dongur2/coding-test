//깔 수 있는 가장 큰 돗자리 (한 변 5/3/2)
class Solution {
    static int answer = -1;
    
    public int solution(int[] mats, String[][] park) {
        for(int x=0; x<park.length; x++) {
            for(int y=0; y<park[0].length; y++) {
                if(park[x][y].equals("-1")) check(mats, park, x, y);
            }
        }
        
        return answer;
    }
    
    static void check(int[] mats, String[][] park, int cx, int cy) {
        for(int mat : mats) {
            int mx = cx + mat; int my = cy + mat;
            
            if(mx > park.length || my > park[0].length) continue;
            
            int cnt = 0;
            for(int x=cx; x<mx; x++) {
                for(int y=cy; y<my; y++) {
                    if(park[x][y].equals("-1")) cnt++;
                }
            }
            
            if(cnt == mat * mat) answer = Math.max(mat, answer);
        }
    }
}