import java.util.*;

/*
- 최소 피로도: 최소 거리가 아니라 최소값이니까 dfs
- 곡괭이 1개로 광물 5개
- 모든 광물 채광 완료 또는 모든 곡괭이 사용
*/
class Solution {
    //곡괭이별 피로도
    int[][] fatigueTable = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    //최소 피로도
    int minFatigue = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        dfs(picks, minerals, 0, 0); //현재 광물 순서, 누적 피로도
        return minFatigue;
    }
    
    void dfs(int[] picks, String[] minerals, int m, int fatigue) {
        //stop: 남은 광물이 없거나 남은 곡괭이가 없을 경우
        if(minerals.length <= m || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            minFatigue = Math.min(minFatigue, fatigue);
            return;
        }
        
        //recursive call: 남은 곡괭이 중 선택해 5개 채광
        for(int i=0; i<3; i++) {
            //곡괭이 선택
            if(picks[i] > 0) {
                picks[i]--;
                
                //채광
                int newFatigue = fatigue;
                for(int cnt=0; cnt<5; cnt++) {
                    //남은 광물이 없으면 중지
                    if(m + cnt >= minerals.length) break;
                    
                    int mineralType = convertType(minerals[m+cnt]);
                    newFatigue += fatigueTable[i][mineralType];
                }
                
                dfs(picks, minerals, m + 5, newFatigue);
                picks[i]++; //사용했던 곡괭이 복구 (backtrack)
            }
        }
    }
    
    int convertType(String type) {
        if(type.equals("diamond")) return 0;
        else if(type.equals("iron")) return 1;
        else return 2;
    }
}