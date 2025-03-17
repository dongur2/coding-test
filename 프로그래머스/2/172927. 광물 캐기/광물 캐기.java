import java.util.*;

/*
- "필요한 최소 피로도" 
- 광물 순서 고정
- 곡괭이는 끝까지 사용
*/
class Solution {
    //최소 피로도
    int minFatigue = Integer.MAX_VALUE;
    
    //피로도
    int[][] fatigueTable = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    public int solution(int[] picks, String[] minerals) {
        dfs(picks, minerals, 0, 0); //광물 순서, 피로도
        return minFatigue;
    }
    
    void dfs(int[] picks, String[] minerals, int m, int fatigue) {
        //모든 광물 채광 완료 또는 사용할 수 있는 곡괭이가 없을 경우
        if(m >= minerals.length || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            minFatigue = Math.min(minFatigue, fatigue);
            return;
        }
        
        for(int i=0; i<3; i++) {
            //곡괭이가 있으면 선택
            if(picks[i] > 0) {
                picks[i]--; //사용
                
                //5개 채광
                int newFatigue = fatigue;
                for(int cnt=0; cnt<5; cnt++) {
                    int idx = m + cnt;
                    
                    if(idx >= minerals.length) break;
                    
                    int mineralType = convertTypeToInt(minerals[idx]);
                    newFatigue += fatigueTable[i][mineralType];
                }
                
                dfs(picks, minerals, m+5, newFatigue);
                picks[i]++; //복구
            }
        }
    }
    
    int convertTypeToInt(String mineral) {
        if(mineral.equals("diamond")) return 0;
        else if(mineral.equals("iron")) return 1;
        else return 2;
    }
    
}