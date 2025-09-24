/*
    체육수업을 들을 수 있는 최대 학생 수 
    - 앞번호나 뒷번호한테만 빌리기 가능
*/
import java.util.Set; import java.util.HashSet;
class Solution {
    int answer = 0; //도난당한 학생 중 최대로 빌릴 수 있는 학생 수 
    Set<Integer> spare = new HashSet<>(); //이미 앞사람이 빌렸는지 체크용
    
    public int solution(int n, int[] lost, int[] reserve) {
        for(int r:reserve) {
            spare.add(r);
        }
        
        int loss = lost.length; //도난당한 학생
        int extra = reserve.length; //여분 가지고 있는 학생 
        
        //여분 갖고왔다가 도난당한 학생 제외
        for(int i=0; i<lost.length; i++) {
            //빌려줄 수 있는 학생 목록과 도난당해서 빌려야 하는 학생 목록에서 동시에 제외
            if(spare.contains(lost[i])) {
                spare.remove(lost[i]); extra--; loss--;
                lost[i] = -99; //안빌려도 되니까 무효값 대체
            }
        }
        
        int only = n - extra - loss; //하나만 갖고 있는 학생 
        
        borrow(lost, 0, 0);
        
        
        return answer+extra+only;
    }
    
    void borrow(int[] lost, int idx, int cnt) { 
        //마지막 학생까지 모두 확인했으면 
        if(idx == lost.length) {
            answer = Math.max(answer, cnt); return;
        }
        
        //도난당한 학생 차례로 빌리기 
        int prev = lost[idx] - 1;
        int next = lost[idx] + 1;

        //앞번호나 뒷번호 사람이 여분이 있는지 확인하고 빌리기
        if(spare.contains(prev)) {
            spare.remove(prev);
            borrow(lost, idx+1, cnt+1);
            spare.add(prev);
        } else borrow(lost, idx+1, cnt);

        if(spare.contains(next)) {
            spare.remove(next);
            borrow(lost, idx+1, cnt+1);
            spare.add(next);
        } else borrow(lost, idx+1, cnt);
    }
}