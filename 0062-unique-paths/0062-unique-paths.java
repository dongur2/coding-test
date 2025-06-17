//왼쪽 위 -> 오른쪽 아래까지 갈 수 있는 경로 개수  
//아래, 오른쪽으로만 이동 가능
class Solution {
    static int[][] map;

    public int uniquePaths(int m, int n) {
        map = new int[m][n];
        return countWays(m, n);        
    }

    //노드 도달 방법 개수 = 위 노드 + 왼쪽 노드 
    public static int countWays(int m, int n) {
        //각 노드에 도착할 수 있는 방법 개수 저장
        //0번째 행과 열은 1 고정 
        for(int r=0; r<m; r++) {
            //첫행일 경우 오른쪽으로만 이동 
            if(r == 0) {
                for(int c=0; c<n; c++) {
                    map[r][c] = 1;
                }
            }

            else {
                for(int c=0; c<n; c++) {
                    //첫열일 경우 아래로만 이동 
                    if(c == 0) map[r][c] = 1; 
                    //첫열이 아닐 경우: 위 노드 + 왼쪽 노드 
                    else map[r][c] = map[r-1][c] + map[r][c-1];
                }
            }
        }

        return map[m-1][n-1];
    }
}