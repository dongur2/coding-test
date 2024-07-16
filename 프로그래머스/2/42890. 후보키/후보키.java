import java.util.*;

class Solution {
    //1. 전역 변수 선언: 후보키 조건을 만족하는 속성조합인덱스를 저장할 리스트, 주어지는 배열을 복사할 변수, 후보키조합 카운트 변수
    List<Set<Integer>> result;
    String[][] copyOfRelation;
    int answer = 0;
    
    public int solution(String[][] relation) {
        //2. 전역 변수 초기화
        result = new ArrayList<>();
        copyOfRelation = relation;
        
        //3. 속성 1개 선택 ~ 최대 개수 선택하는 속성인덱스조합 구하기
        for(int attr=1; attr<=relation[0].length; attr++) { // 속성 하나 선택 ~ 모두 선택까지
            comb(new HashSet<>(), attr, 0, 0); // 속성 조합을 저장할 SET, 만드려는 속성 조합의 선택 개수, 시작 인덱스, 현재 속성 선택 개수
        }
        
        return answer;
    }
    
    /* 모든 속성의 부분집합(조합)을 구하고, 그 중 후보키 조건을 만족하는 조합을 찾는 메서드 */
    private void comb(Set<Integer> set, int attr, int start, int cnt) {
        //base case: 현재 조합 크기가 원하는 속성 개수만큼 추가했으면 일단 인덱스 조합 완성: 후보키 조건을 충족하는지 확인
        if(set.size() == attr) { 
            //1. 유일성 검사: 유일성을 만족하면 계속 진행, 만족하지 못하면 중지
            if(!isUnique(set)) return;
            else {
                //2. 유일성 검사를 통과한 조합은 최소성 검사: 최소성을 만족하면 계속 진행, 만족하지 못하면 중지
                //현재 조합이 후보키 조합 중 한 조합이라도 그 구성 인덱스를 모두 가지고 있다면, 최소성 불만족 (현재: 012 -> 후보키 조합 중 하나가 02라면 최소성 불만족)
                for(Set<Integer> res : result) {
                    if(set.containsAll(res)) return;
                }
            }

            //3. 최소성 & 유일성 검사를 모두 통과한 조합은 최종 결과 리스트에 저장하고, 후보키 카운트
            result.add(new HashSet<>(set));
            answer++;
        }
        
        //recursive call: 속성을 하나씩 밀어가면서 조합에 속성의 인덱스를 추가 => 중복 제외
        for(int i=start; i<copyOfRelation[0].length; i++) {
            set.add(i);
            comb(set, attr, i+1, cnt+1);
            set.remove(i);
        }
    }
    
    /* 유일성 검사 메서드 */
    private boolean isUnique(Set<Integer> set) {
        //1. 비교를 위해 임시로 속성을 저장할 리스트를 정의
        List<String> list = new ArrayList<>();
        
        //2. 전체 배열(행/각 학생)을 순회하면서
        for(int student=0; student<copyOfRelation.length; student++) {
            StringBuilder com = new StringBuilder();
            
            //3. SET에 저장된 인덱스에 해당하는 속성을 StringBuilder로 붙여만들고
            for(int idx : set) {
                com.append(copyOfRelation[student][idx]);    
            }
            
            //4. 리스트가 비었거나, 리스트에 현재 만든 문자열을 포함하고 있지 않으면 리스트에 저장
            if(list.isEmpty() || !list.contains(com.toString())) list.add(com.toString());
            else return false; //5. 리스트가 현재 만든 문자열을 포함한다면, 유일하지 않다는 말이므로 중지
        }
        
        //6. 끝까지 값을 성공적으로 추가했다면, 모든 키조합이 유일성을 만족함을 의미
        return true;
    }
}                                                                                                                      
