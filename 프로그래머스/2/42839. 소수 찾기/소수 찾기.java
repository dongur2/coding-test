import java.util.*;

class Solution {
    /*
    Q. 주어진 숫자로 만들 수 있는 '소수의 개수'를 반환
    - 11 == 011
    - 중복 사용 불가능
    - 중복 X + 순서 의미 있음 => 순열 (123, 132)
    */
    
    int cnt = 0;
    boolean[] visited;
    Set<Integer> set;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        set = new HashSet<>();
        
        char[] nums = numbers.toCharArray();
        
        for(int i=1; i<=nums.length; i++) {
            makeNumAndCheckPrime(nums, i, new StringBuilder());
        }
        
        return set.size();
    }
    
    void makeNumAndCheckPrime(char[] nums, int len, StringBuilder num) {
        if(num.length() == len) {
            int number = Integer.valueOf(num.toString());
            if(isPrime(number)) set.add(number);
        }
        
        for(int i=0; i<nums.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            num.append(nums[i]);
            
            makeNumAndCheckPrime(nums, len, num);
            
            visited[i] = false;
            num.deleteCharAt(num.length()-1);
        }
    }
    
    boolean isPrime(int num) {
        if(num == 0 || num == 1) return false;
        else if(num == 2) return true;
        
        for(int i=2; i<Math.abs(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}