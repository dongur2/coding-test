//택배 번호가 주어졌을 때, 꺼내야 하는 상자의 총개수(목표 택배 포함)
class Solution {
    public int solution(int n, int w, int num) {
        int height = (n % w == 0) ? n / w : n / w + 1;
        int[][] container = makeContainer(n, height, w); //순서대로 택배 쌓기

        //주어진 택배 번호가 포함된 열 찾기 
        for(int r=0; r<height; r++) {
            for(int c=0; c<w; c++) {
                //찾았으면 박수 개수 계산
                if(container[r][c] == num) return container[height-1][c] != 0 ? height-r : height-r-1; //현재 박스까지 포함하므로 
            }
        }
        
        return 0;
    }
    
    int[][] makeContainer(int n, int row, int w) {
        int[][] result = new int[row][w];
        
        int box = 1;
        for(int i=0; i<row; i++) {
            if(i % 2 == 0) {
                for(int j=0; j<w; j++) {
                    result[i][j] = box++; if(box > n) break;
                }
            } else {
                for(int j=w-1; j>=0; j--) {
                    result[i][j] = box++; if(box > n) break;
                }
            }
        }    
        
        return result;
    }
}