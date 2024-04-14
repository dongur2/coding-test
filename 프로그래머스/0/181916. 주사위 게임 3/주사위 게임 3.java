import java.util.*;

class Solution {
    private int my(int a, int b, int c, int d) {
        Map<Integer, Integer> dice = new HashMap<>();
        
        // 주사위 값(key):횟수(value)
        for(int num : new int[] {a,b,c,d}) {
            dice.put(num, (dice.get(num) != null)? dice.get(num)+1 : 1);
        }
        
        int answer = 0;
        int cnt = dice.keySet().size();
        switch(cnt) {
            case 1: answer = 1111 * a; break;
            
            case 2: {
                int[] numbers = new int[2];
                int i=0;
                for(int num : dice.keySet()) {
                    numbers[i++] = num;
                }
                
                if(dice.values().contains(3)) {
                    int p = dice.get(numbers[0])==3? numbers[0]:numbers[1];
                    int q = dice.get(numbers[0])==3? numbers[1]:numbers[0];
                    answer = (int)Math.pow(10 * p + q, 2); break;
                }
                
                answer = (numbers[0] + numbers[1]) * Math.abs(numbers[0] - numbers[1]); break;
            }
                
            case 3: {
                int[] numbers = new int[2];
                int i=0;
                for(int num : dice.keySet()) {
                    if(dice.get(num) < 2) numbers[i++] = num;
                }
                
                answer = numbers[0] * numbers[1]; break;
            }
                
            case 4: {
                answer = 7;
                for(int num : dice.keySet()) {
                    answer = (answer > num)? num : answer;
                }
                break;
            }
        }
        
        return answer;
    }
    
    private int useSort(int a, int b, int c, int d) {
        int[] dice = {a, b, c, d};
        Arrays.sort(dice); // 오름차순 정렬
        
        // 모두 같은 경우 0000
        if(dice[0] == dice[3]) return 1111 * dice[0];
        
        // 3개가 같을 경우 0001, 0111
        if(dice[1] == dice[3]) return (int)Math.pow(10 * dice[1] + dice[0], 2);
        if(dice[0] == dice[2]) return (int)Math.pow(10 * dice[0] + dice[3], 2);
        
        // 2개씩 같을 경우 0011
        if(dice[0] == dice[1] && dice[2] == dice[3]) return (dice[0] + dice[2]) * Math.abs(dice[0] - dice[2]);
        
        // 2개가 같고 2개가 다를 경우 0012, 0112, 0122
        if(dice[0] == dice[1]) return dice[2] * dice[3];
        if(dice[1] == dice[2]) return dice[0] * dice[3];
        if(dice[2] == dice[3]) return dice[0] * dice[1];
        
        // 모두 다를 경우
        else return dice[0];
    }
    
    public int solution(int a, int b, int c, int d) {
        return useSort(a,b,c,d);
    }
}