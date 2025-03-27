import java.util.*;

//곡괭이를 다 쓰거나 모든 광물을 캘 때까지 필요한 최소 피로도
//빨리 캐는게 아니고 최소 피로도니까 dfs
class Solution {
    //각 곡괭이별 피로도 테이블
    int[][] fatigues = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    //최소 피로도
    int minFatigue = Integer.MAX_VALUE;
    int mineralCnt;
    
    public int solution(int[] picks, String[] minerals) {
        mineralCnt = minerals.length;
        
        dfs(picks, minerals, 0, 0);
        return minFatigue;
    }
    
    void dfs(int[] picks, String[] minerals, int idx, int fatigue) {
        //stop: 남은 광물/곡괭이가 모두 없을 경우 중지
        if(idx >= mineralCnt || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
            minFatigue = Math.min(minFatigue, fatigue);
            return;
        }
        
        //recursive call: 곡괭이 차례대로 선택해서 채광
        for(int i=0; i<3; i++) {
            //남은 곡괭이 있으면 선택
            if(picks[i] > 0) {
                picks[i]--;
                
                //채광시작 - 연속 5회 
                int newFatigue = 0, newIdx = idx;
                for(int cnt=0; cnt<5; cnt++) {
                    //남은 광물이 있을 경우 채광 진행
                    if(newIdx < mineralCnt) {
                        int mineral = convertToIdx(minerals[newIdx]);
                        newFatigue += fatigues[i][mineral];
                        newIdx++;
                    }
                }
                
                dfs(picks, minerals, idx + 5, fatigue + newFatigue);
                picks[i]++;
            }
        }
    }
    
    int convertToIdx(String name) {
        if(name.equals("diamond")) return 0;
        else if(name.equals("iron")) return 1;
        else return 2;
    }
}