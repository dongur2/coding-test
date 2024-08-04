import java.util.*;

class Solution {
    /*
    ** 탐험할 수 있는 최대 '던전 수'를 반환
    -- 탐험하는 던전 순서에 따라 탐험 가능한 던전 수가 달라짐
    ---> 같은 던전을 중복 탐험 불가능 && 순서에 의미를 부여 ==> 순열
    -- [최소 필요 피로도, 소모 피로도]: 던전에 방문했을 때, 유저의 피로도가 최소 필요 피로도 이상이어야 탐험 진행
    */
    
    boolean[] visited;
    int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        backtrack(k, dungeons, 0);
        
        return answer;
    }
    
    void backtrack(int k, int[][] dungeons, int cnt) {
        /* recursive call: 전체 던전을 순회
            현재 방문한 던전이 이전에 방문한 적 있다면(탐험 완료) 무시
            아니라면, 유저 피로도와 현재 방문한 던전의 최소 필요 피로도를 비교: 
            - 필요 피로도 이상이라면 방문 체크 & 탐험 카운트 & 그 다음 던전 탐색 (하청)
            - 하청 후 방문 체크 무효
            
            - 전역 변수로 저장한 탐험 횟수와 현재 탐험 횟수를 비교하여 최대값 저장
        */
        for(int i=0; i<dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                backtrack(k - dungeons[i][1], dungeons, cnt+1);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, cnt);
    }
}