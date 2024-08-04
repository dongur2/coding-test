import java.util.*;

class Solution {
    Set<Integer> set;
    boolean[] visited;
    
    public int solution(String numbers) {
        set = new HashSet<>();
        visited = new boolean[numbers.length()];
        
        //주어진 문자열을 1글자씩 분할
        String[] splited = numbers.split("");
        
        //1글자부터 주어진 문자열 길이까지 순회:
        for(int size=1; size<=numbers.length(); size++) {
            //반복문을 도는 길이의 숫자 조합을 모두 생성하여 소수인지 확인 후 카운트: 매개변수로 새로운 StringBuilder 전달 
            makeNumberAndFindPrime(splited, size, new StringBuilder());
        }

        //카운트한 소수 개수 리턴
        return set.size();
    }
    
    private void makeNumberAndFindPrime(String[] splited, int size, StringBuilder number) {
        //base case: 현재 반복문을 돌고있는 문자열 길이와 만든 숫자 길이가 같다면:
	    if(number.length() == size) {
            //소수인지 확인 후, 소수라면 정답 세트에 저장하고(아니라면 아무 작업도 하지 않고) 재귀 중지
            int num = Integer.parseInt(number.toString());
            if(isPrime(num)) set.add(num);
            return;
        }    
        
        //recursive call
        //분할한 문자열 배열을 처음부터 끝까지 순회:
        for(int i=0; i<splited.length; i++) {
            if(!visited[i]) {
                //현재 노드 방문 체크 & 문자열에 추가
                visited[i] = true;
                number.append(splited[i]);

                //backtrack(문자열): 다음 노드 탐색에 대한 하청
                makeNumberAndFindPrime(splited, size, number);

                //현재 노드 방문 체크 해제 & 문자열에 추가했던 현재 노드 삭제
                visited[i] = false;
                number.deleteCharAt(number.length()-1);
            }
        }
    }
    
    private boolean isPrime(int num) {
        if(num == 0 || num == 1) return false;
        for(int n=2; n <= Math.sqrt(num); n++) {
            if(num % n == 0) return false;
        }
        return true;
    }
}