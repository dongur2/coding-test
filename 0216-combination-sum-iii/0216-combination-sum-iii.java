/*
    1-9 숫자로 구성 (중복 불가)
    k개로 이루어진 조합 중 합이 n이 되는 모든 조합 찾기
 */
import java.util.List; import java.util.ArrayList;
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        for(int i=1; i<=9; i++) {
            makeComb(k, n, i, i, new ArrayList<>(List.of(i))); //1부터 확인 시작
        }
        return res;    
    }

    void makeComb(int k, int n, int curr, int sum, List<Integer> comb) {
        //조합 구성 개수가 k면 합이 n인지 확인 후 재귀 종료
        if(comb.size() == k) {
            if(sum == n) res.add(new ArrayList<>(comb));
            return;
        }

        //합이 이미 n 이상이면 재귀 중지
        if(sum >= n) return;

        //조합에 현재 숫자 추가 및 합 업데이트해서 재귀
        for(int nxt = curr+1; nxt <= 9; nxt++) {
            comb.add(nxt);
            makeComb(k, n, nxt, sum+nxt, comb);
            comb.remove(Integer.valueOf(nxt));
        }
    }
}