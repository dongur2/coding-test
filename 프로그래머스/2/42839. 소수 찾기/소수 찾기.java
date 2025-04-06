import java.util.*;

//만들 수 있는 소수 개수
class Solution {
    static Set<Integer> set = new HashSet<>(); //중복불허
    static boolean[] visited;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        
        for(int len=1; len<=numbers.length(); len++) {
            make(numbers.toCharArray(), len, new StringBuilder());
        }
        
        return set.size();
    }
    
    void make(char[] nums, int len, StringBuilder number) {
        //길이충족시 소수 확인
        if(number.length() == len) {
            int num = Integer.parseInt(number.toString());
            if(isPrime(num)) set.add(num);
            return;
        }
        
        for(int i=0; i<nums.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true; number.append(nums[i]);
            make(nums, len, number);
            visited[i] = false; number.deleteCharAt(number.length()-1);
        }
    }
    
    boolean isPrime(int n) {
        if(n == 0 || n == 1) return false;
        
        for(int i=2; i<n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}