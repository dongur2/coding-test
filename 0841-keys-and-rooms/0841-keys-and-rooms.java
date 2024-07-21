import java.util.*;

class Solution {
    Queue<Integer> q;
    Map<Integer, Boolean> visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        q = new ArrayDeque<>();
        visited = new HashMap<>();

        bfs(rooms, 0);

        //5.
        return rooms.size() == visited.size();
    }

    private void bfs(List<List<Integer>> rooms, int root) {
        //1.
        q.offer(root); visited.put(root, true);

        while(!q.isEmpty()) {
            //2.
            int curVertex = q.poll();
            
            //3.
            for(int nextVertex : rooms.get(curVertex)) {
                //4.
                if(!visited.containsKey(nextVertex)) {
                    q.offer(nextVertex); visited.put(nextVertex, true);
                }
            }
        }
    }
}