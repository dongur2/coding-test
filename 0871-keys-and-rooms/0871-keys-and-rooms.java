import java.util.List; import java.util.ArrayList;

//0번 방만 잠겨있지 않은 n개 방이 있고, 방문한 방에는 n번 방의 열쇠가 있음 -> 열쇠 번호에 해당하는 방 방문 가능
//모든 방에 방문할 수 있는지 여부. 
class Solution {
    int n;
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        n = rooms.size();
        visited = new boolean[n]; //방문 여부 

        dfs(rooms, 0); //0번 방부터 방문 

        //방문한 적 없는 방이 있는지 확인
        for(boolean v:visited) {
            if(!v) return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int curr) {
        visited[curr] = true;

        //방에 열쇠 없으면 리턴 
        if(rooms.get(curr) == null) return;
        //방에 있는 열쇠에 해당하는 방에 방문
        for(int key:rooms.get(curr)) {
            if(visited[key]) continue;
            dfs(rooms, key);
        }
    }
}