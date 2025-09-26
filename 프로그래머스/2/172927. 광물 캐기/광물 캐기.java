import java.util.*;

//곡괭이 개수와 광물 순서가 주어졌을 때, 작업 종료까지 필요한 최소 피로도 
class Solution {
    //int[] picks = [다이아, 철, 돌] //곡괭이별 광물 채광 피로도
    int[][] table = new int[][] {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    Map<String, Integer> mineralMap = new HashMap<>();
    
    int answer = Integer.MAX_VALUE; //최소 피로도 

    public int solution(int[] picks, String[] minerals) {
        mineralMap.put("diamond", 0);
        mineralMap.put("iron", 1);
        mineralMap.put("stone", 2);
        
        int totalPicks = picks[0]+picks[1]+picks[2];
        int maxBlocks = (int)Math.ceil((double)minerals.length / 5);
        int maxIdx = Math.min(totalPicks, maxBlocks); //채굴 가능한 그룹 
        dfs(picks, minerals, 0, 0, maxIdx);
        
        return answer;
    }
    
    void dfs(int[] picks, String[] minerals, int blockIdx, int tired, int maxIdx) {
        //종료 시점
        if(blockIdx >= maxIdx) {
            answer = Math.min(answer, tired);
            return;
        }

        //각 곡괭이로 캤을 때 피로도
        int[] fatigue = new int[3];
        
        int start = blockIdx * 5;
        //다섯개 채광
        for(int i=0; i<5; i++) {
            int mIdx = start + i; //현재 광물 인덱스 
            if(mIdx >= minerals.length) break; //광물 끝
            
            int mKindIdx = mineralMap.get(minerals[mIdx]);
            
            fatigue[0] += table[0][mKindIdx];
            fatigue[1] += table[1][mKindIdx];
            fatigue[2] += table[2][mKindIdx];
        }
        
        //곡괭이 선택 
        for(int i=0; i<3; i++) {
            if(picks[i] > 0) {
                picks[i]--;
                dfs(picks, minerals, blockIdx+1, tired+fatigue[i], maxIdx);
                picks[i]++;
            }
        }
    }
    
}