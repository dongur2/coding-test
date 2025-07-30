import java.util.Queue; import java.util.ArrayDeque;
/*
    도타2 세계관에는 정당 2개가 존재: [Radiant / Dire]
    이 세계의 "의회는 이 두 정당의 의원들로 이루져있는데", 도타2 게임을 변화시키기로 결심

    이 변화를 위한 투표는 "라운드제 절차"로 진행 (round-based procedure)
    "각 라운드마다 각 의원은 두 개의 권리 중 하나를 행사"할 수 있음:
    1. 다른 의원 1명의 권리 금지: 현재부터 끝날 때까지 다른 의원의 모든 권리 박탈
    2. 승리 발표: 여전히 투표할 권리를 가지고 있는 의원들이 모두 자신과 같은 정당이라면 승리 발표 

    주어진 senate 문자열은 각 의원이 속한 파티를 의미 - 문자열 길이 n은 n명의 의원이 있다는 것
    round-based 절차는 주어진 순서대로 처음부터 끝까지 차례로 수행 -> 투표가 끝날 때까지 유지
    물론 권리를 잃은 의원들은 이 절차에서 무시됨

    모든 의원들은 충분히 똑똑하고 그들의 파티를 위해 최선의 전략으로 임할 것
    
    ## 그래서 어느 파티가 결국 승리하여 도타 게임에 변화를 일으킬 것인지 예측하시오 ##

    RD -> (1)[R이 D의 권리 박탈 -> D 무시] -> R 승리 
    RDD -> (1)[R이 D의 권리 박탈 -> D 무시 -> D가 R의 권리 박탈] -> (2)[R, D 무시 -> D 승리]
 */
class Solution {
    public static String predictPartyVictory(String senate) {
        int n = senate.length(); //의원 수

        //[큐]:라운드별로 처음부터 끝까지 순서 고정 - 인덱스 등록 (권리가 있는 의원만 등록)
        Queue<Integer> rq = new ArrayDeque<>(); //radiant
        Queue<Integer> dq = new ArrayDeque<>(); //dire 

        //초기: 모든 의원 등록
        for(int i=0; i<n; i++) {
            char s = senate.charAt(i);

            if(s == 'R') rq.add(i);
            else dq.add(i);
        }

        //같은 정당 의원만 남을 때까지 라운드 반복 
        round(n, rq, dq);

        return rq.isEmpty() ? "Dire" : "Radiant";
    }

    private static void round(int n, Queue<Integer> rq, Queue<Integer> dq) {
        //두 큐 한 쪽이 비면 종료 
        while(!rq.isEmpty() && !dq.isEmpty()) {
            int idxR = rq.poll(), idxD = dq.poll();

            //차례가 뒤로 가도록 + 다른 인덱스와 겹치지 않도록 의원 최대 수인 n을 더해서 다시 큐에 추가 
            //r이 먼저면 d 박탈
            if(idxR < idxD) rq.add(idxR + n);
            //d가 먼저면 r 박탈 
            else dq.add(idxD + n);
        }
    }
}