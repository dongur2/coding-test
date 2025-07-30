import java.util.List; import java.util.ArrayList;

//1~n 숫자 범위로 만들 수 있는 k 길이의 모든 조합 (중복X, 순서의미X)
class Solution {
    List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, new ArrayList<>(), 1);
        return answer;
    }

    private void backtrack(int n, int k, List<Integer> list, int idx) {
        if(list.size() == k) {
            answer.add(new ArrayList<>(list)); return;
        }

        for(int i=idx; i<=n; i++) {
            list.add(i);
            backtrack(n, k, list, i+1);
            list.remove(Integer.valueOf(i));
        }
    }
}