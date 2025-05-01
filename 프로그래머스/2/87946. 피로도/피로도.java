//탐험 가능한 최대 던전 개수
/*
던전 순서를 바꿔서 탐험 [필요, 소모]
- 유저 >= 최소 필요 피로도 -> 탐험 가능 -> 탐험 후 소모 피로도만큼 유저의 피로도 감소
- 순서 의미 있는 -- "순열"
*/
class Solution {
    static boolean[] visited;
    static int answer = 0, n = 0;
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        visited = new boolean[n];
        
        explore(k, dungeons, 0);
        
        return answer;
    }
    
    public static void explore(int k, int[][] dungeons, int cnt) {
        for(int i=0; i<n; i++) {
            if(visited[i]) continue; //이미 탐험한 던전은 무시
            
            int[] d = dungeons[i]; //현재 던전
            
            //최소 필요 피로도 > 유저 피로도: 불가
            if(d[0] > k) continue;
            
            //최소 필요 피로도 <= 유저 피로도: 탐험 진행
            else if(d[0] <= k) {
                visited[i] = true;
                explore(k-d[1], dungeons, cnt+1);
                visited[i] = false;
            }
        }
        
        //최대 탐험 개수 업데이트
        answer = Math.max(answer, cnt);
    }
}