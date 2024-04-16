import java.util.*;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        Map<Integer, Integer> validRank = new HashMap<>(); //등수:학생번호
        for(int i=0; i<rank.length; i++) {
            if(attendance[i] == true) validRank.put(rank[i], i);
        }
        
        List<Integer> ranks = new ArrayList<>();
        for(int order:validRank.keySet()) {
            ranks.add(order);
        }
        
        ranks.sort(Comparator.naturalOrder());
        
        int a = validRank.get(ranks.get(0)), b = validRank.get(ranks.get(1)), c = validRank.get(ranks.get(2));
        return 10000 * a + 100 * b + c;
    }
}