import java.util.Set;
import java.util.HashSet;

//모든 길이의 부분수열 구하기 -> 소수 판별
class Solution {
    static Set<Integer> set = new HashSet<>();
    static int n = 0;
    
    public int solution(String numbers) {
        n = numbers.length();
        
        //모든 길이로 만들 수 있는 숫자 생성
        for(int len=1; len<=n; len++) {
            makeSub(numbers.toCharArray(), len, new boolean[n], new StringBuffer());
        }
        
        //소수 판별
        return (int) set.stream().filter(s -> isPrime(s)).count();
    }
    
    public static void makeSub(char[] chars, int len, boolean[] visited, StringBuffer sb) {
        //중복을 필터링하기 위해 세트에 추가
        if(sb.length() == len) {
            set.add(Integer.parseInt(sb.toString()));
            return;
        }
        
        //조합하기 (중복 비허용, 순서 의미 있음)
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            
            sb.append(chars[i]); visited[i] = true;
            makeSub(chars, len, visited, sb);
            sb.deleteCharAt(sb.length()-1); visited[i] = false;
        }
    }
    
    public static boolean isPrime(int num) {
        if(num == 0 || num == 1) return false;
        
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}