import java.util.Set; import java.util.HashSet;
class Solution {
    Set<Integer> set = new HashSet<>();
    String[] arr;
    boolean[] visited;
    
    public int solution(String numbers) {
        arr = numbers.split("");
        visited = new boolean[numbers.length()];
        getPrimes("");
        return set.size();
    }
    
    void getPrimes(String num) {
        //소수 확인
        if(num.length()>0 && isPrime(Integer.valueOf(num))) set.add(Integer.valueOf(num));
        
        //최대 길이면 재귀 중지
        if(num.length() == arr.length) return;
        
        for(int idx=0; idx<arr.length; idx++) {
            if(visited[idx]) continue;
            
            visited[idx] = true;
            getPrimes(num+arr[idx]);
            visited[idx] = false;           
        }
    }
    
    boolean isPrime(int num) {
        if(num == 0 || num == 1) return false;
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num%i==0) return false;
        }
        return true;
    }
}