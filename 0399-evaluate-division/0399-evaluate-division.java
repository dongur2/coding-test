/*
    equations[i] = [A, B], values[i]는 A/B = values[i]
    queries[j] = [C, D] j번째 쿼리는 C/D=? 찾을 수 있다
    모든 쿼리의 답을 리턴 (값 하나로 결정되지 않으면 -1)
 */
import java.util.Map; import java.util.HashMap;
import java.util.Set; import java.util.HashSet;
import java.util.List; import java.util.ArrayList;
class Solution {
    Map<String, Map<String, Double>> graph = new HashMap<>(); // 노드: {노드:값}
    double[] result;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        result = new double[queries.size()];

        //graph
        for(int i=0; i<equations.size(); i++) {
            graph.computeIfAbsent(equations.get(i).get(0), k -> new HashMap<>()).put(equations.get(i).get(1), values[i]); // a/b
            graph.computeIfAbsent(equations.get(i).get(1), k -> new HashMap<>()).put(equations.get(i).get(0), 1.0 / values[i]); //xa
        } 

        //query
        for(int i=0; i<queries.size(); i++) {
            String c = queries.get(i).get(0), d = queries.get(i).get(1); // c/d
            
            if(!graph.containsKey(c) || !graph.containsKey(d)) result[i] = -1.0;
            else if (c.equals(d)) result[i] = 1.0;
            else {
                Set<String> visited = new HashSet<>();
                result[i] = dfs(c, d, visited, 1.0);
            }
        }

        return result;
    }

    double dfs(String c, String d, Set<String> visited, double res) {
        if(c.equals(d)) return res;

        visited.add(c);

        for(Map.Entry<String, Double> entry : graph.get(c).entrySet()) {
            if(visited.contains(entry.getKey())) continue;
            double num = dfs(entry.getKey(), d, visited, res * entry.getValue());
            if (num != -1.0) return num; //-1.0이 아니면 유효한 경로를 찾았다는 의미니까 즉시 리턴 
        }

        return -1.0;
    }
}