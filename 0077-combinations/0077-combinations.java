import java.util.*;

class Solution {
    /*
     ** 1부터 n까지 숫자로 만들 수 있는 k개로 이루어진 조합을 반환
     - 조합: 중복X && 순서 의미 없음 

     2, [1,4]
     [1,2][1,3][1,4][2,3][2,4][3,4] - 한 번 같은 조합으로 묶였다면 다시는 묶이지 않음
     */

    boolean[] visited; //중복된 숫자를 추가하지 않기 위해 방문 여부 관리
    List<List<Integer>> answer; //만든 조합을 저장해 반환할 리스트

    public List<List<Integer>> combine(int n, int k) {
        visited = new boolean[n+1];
        answer = new ArrayList<>();

        backtrack(new ArrayList<>(), n, k, 1);

        return answer;
    }

    void backtrack(List<Integer> list, int n, int k, int start) {
        //base case: 현재 만들고 있는 조합이 원하는 길이와 같다면 정답 리스트에서 저장하고 더 이상의 탐색(원소 추가) 중지
        if(list.size() == k) {
            answer.add(new ArrayList<>(list)); return;
        }

        /* recursive call: 전체 배열 순회
        현재 노드에 방문한 적이 없다면, 방문 처리 후 조합에 추가
        추가한 노드를 포함하는 조합 찾아 저장하는 하청: 길이를 만족시킬 때까지 알아서 찾아 저장까지 하는 작업까지 완료 후 돌아옴
        현재 노드 방문 처리 무효 & 조합에서 제거
        */
        for(int i=start; i<=n; i++) {
            if(visited[i]) continue;

            visited[i] = true; list.add(i);
            backtrack(list, n, k, i+1);
            visited[i] = false; list.remove(Integer.valueOf(i));
        }
    }
}