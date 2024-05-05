import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] ans1 = {1, 2, 3, 4, 5};
        int[] ans2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] ans3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        Map<Integer, Integer> result = new HashMap<>();
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == ans1[i%5]) result.put(1, result.getOrDefault(1, 0)+1);
            if(answers[i] == ans2[i%8]) result.put(2, result.getOrDefault(2, 0)+1);
            if(answers[i] == ans3[i%10]) result.put(3, result.getOrDefault(3, 0)+1);
        }
        
        int highest = 0;
        for(int res:result.values()) {
            highest = (res > highest)? res:highest;
        }
        
        List<Integer> answer = new ArrayList<>();
        for(int ans:result.keySet()) {
            if(result.get(ans) == highest) answer.add(ans);
        }
        
        return answer.stream().mapToInt(a->a).toArray();
    }
}