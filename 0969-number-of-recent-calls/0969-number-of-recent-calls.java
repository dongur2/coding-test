import java.util.List; import java.util.ArrayList;
/*
    최근 요청 번호를 세는 클래스 RecentCounter
 */
class RecentCounter {
    int num;
    List<Integer> list;

    //번호는 0으로 초기화 
    public RecentCounter() {
        this.num = 0;
        this.list = new ArrayList<>();
    }
    
    //새로운 요청이 시간 t에 추가되었을 때, t는 밀리초
    //지난 3000 밀리초(3초)가 지나는 동안 발생한 요청 개수를 리턴 - (t - 3000, t) 범위 내에 발생한 요청 개수 
    public int ping(int t) {
        list.add(t); //큐에 추가 
        int s = t - 3000, e = t; //범위

        //범위에 속하는 요청 개수 카운트 
        int cnt = 0;
        for(int l:list) {
            if(s <= l && l <= e) cnt++;
        }

        return cnt;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */