import java.util.*;

class Solution {
    /*
    Q. 탐험 가능한 최대 던전 개수를 반환
    - [최소 피로도, 소모 피로도]
    - k: 현재 피로도
    
    - 한번 방문한 던전은 재방문하지 않음 (중복 불가)
    - 순서 상관 있음 [2,3] [3,2] 둘 다 유효
      => 순열(permutation)
    */
    
    int res = -1;
    
    public int solution(int k, int[][] dungeons) {
        visit(k, dungeons, new boolean[dungeons.length], 0);
        return res;
    }
    
    private void visit(int k, int[][] dungeons, boolean[] visited, int cnt) {
        //주어진 던전을 순차적으로 순회
        for(int i=0; i<dungeons.length; i++) {
            //방문한 던전이거나, 현재 피로도 < 최소 피로도라면 무시
            if(visited[i] || k < dungeons[i][0]) continue;
            
            //던전을 방문: 피로도 감소 & 방문 처리 & 방문 카운트
            visited[i] = true;
            visit(k - dungeons[i][1], dungeons, visited, cnt+1);
            
            //이후 방문 처리 무효
            visited[i] = false;
        }
        
        //최대값 비교 후 바인딩
        res = Math.max(res, cnt);
    }
}