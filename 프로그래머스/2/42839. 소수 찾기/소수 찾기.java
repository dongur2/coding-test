import java.util.*;

class Solution {
    /*
    ** 주어진 문자열에 포함된 숫자들로 만들 수 있는 '소수의 개수'를 반환
    -- 013, 031.. 중복 불가능 && 순서에 의미 부여 ==> 순열
    -- 011의 경우 011,011이 같은 글자가 되므로 세트로 저장해서 개수 세기
    */
    
    boolean[] visited;
    Set<Integer> set; //소수를 저장
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        set = new HashSet<>();
        
        //만들 수 있는 모든 글자수로 숫자 생성
        for(int len=1; len<=numbers.length(); len++) {
            backtrack(numbers, len, new StringBuilder());
        }
        
        return set.size();
    }
    
    void backtrack(String numbers, int len, StringBuilder number) {
        //base case: 만드려는 글자수와 일치하면 소수 확인 후 저장
        if(number.length() == len) {
            int num = Integer.parseInt(number.toString());
            if(isPrime(num)) set.add(num); return;
        }
        
        /* recursive call:전체 배열 순회
        이미 방문한 원소라면 무시
        아니라면 방문 체크 & 문자열에 추가
        추가한 문자열에 더해서 탐색 시작 - 하청
        하청 후 방문 체크 무효 & 문자열에서 삭제
        */
        for(int i=0; i<numbers.length(); i++) {
            if(visited[i]) continue;
            
            visited[i] = true; number.append(numbers.charAt(i)+"");
            backtrack(numbers, len, number);
            visited[i] = false; number.deleteCharAt(number.length()-1);
        }
    }
    
    boolean isPrime(int number) {
        if(number == 0 || number == 1) return false;
        
        for(int i=2; i<=Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        
        return true;
    }
}