/*
    모든 공격이 끝난 후 남은 체력 (0이하라면 -1)
    - 붕대감기: t초 동안 1초 당 체력x 회복 
      *t초 연속으로 붕대감기 성공 => 체력y 추가 회복
    - 도중 공격받으면 취소: 공격받은 순간 체력회복 불가, 연속 성공 시간 0으로 최소화, 즉시 붕대감기 재시작
    - 공격받으면: 체력 감소 + 0이하되면 죽음
*/
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        //계속해서 현재 시간초 재야 함
        //공격시간 되면 공격받음
        int max = health;
        
        int t = bandage[0]; //붕대감기 소요시간 t초
        int x = bandage[1]; //붕대감기 1초당 회복량 x
        int y = bandage[2]; //붕대감기 연속 t초 성공시 추가 회복량 y
        
        int curr = 0; //현재 시간초
        
        //모든 공격 받기
        for(int[] attack : attacks) {
            //현재까지 연속 성공 시간초
            int success = attack[0] - curr - 1;
            
            //성공한 시간만큼 체력 회복
            health += (success * x) + ((success/t)*y); //연속성공시간이 t이상이면 추가 회복
            if(health > max) health = max; //체력은 최대치 초과 불가능
            
            //피해량 감소
            health -= attack[1];
            
            //0이하면 중지 
            if(health <= 0) return -1;
            
            //현재 시간 업데이트
            curr = attack[0];
        }
        
        return health;
    }
}