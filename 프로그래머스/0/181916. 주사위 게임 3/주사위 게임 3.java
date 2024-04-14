import java.util.*;

class Solution {
    public int solution(int a, int b, int c, int d) {
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
}