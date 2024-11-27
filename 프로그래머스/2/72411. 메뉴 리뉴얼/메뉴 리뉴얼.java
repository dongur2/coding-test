import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    Q. 새로 추가하게 될 코스요리의 메뉴 구성 반환
    - 최소 2번 이상 주문받은 조합 -> 후보
    - 후보 중 course 배열에 있는 숫자에 해당하는 개수로 이루어진 조합
    */
    public String[] solution(String[] orders, int[] course) {
        //주문받은 조합들에 포함된 단품메뉴를 분리해서 저장
        List<Character> menus = splitMenu(orders);
        
        //단품메뉴로 만들 수 있는 모든 조합을 생성(부분집합 - 조합)하고 실제 주문받은 조합만 저장 (중복될 경우 카운트)
        Map<Set<Character>, Integer> orderedMap = new HashMap<>();
        makeCombinations(menus, orders, orderedMap, course);
        
        //실제로 주문받은 조합 중 조건에 맞는 조합을 코스요리로 선정하여 리턴
        return makeCourseMenu(course, orderedMap);
    }
    
    private List<Character> splitMenu(String[] orders) {
        Set<Character> set = new HashSet<>();
        
        for(String order : orders) {
            order.chars().mapToObj(c -> (char)c).forEach(set::add);
        }

        return set.stream().collect(Collectors.toList());
    }
    
    private void makeCombinations(List<Character> menus, String[] orders, Map<Set<Character>, Integer> orderedMap, int[] course) {
        //받은 주문을 단품으로 나누어 Set에 저장 ("ABC" -> 'A', 'B',' C')
        List<Set<Character>> orderSet = new ArrayList<>();
        for(String order:orders) {
            Set<Character> set = new TreeSet<>();
            char[] arr = order.toCharArray();
            for(char a : arr) {
                set.add(a);
            }
            orderSet.add(new HashSet<>(set));
        }
        
        //원하는 개수로 이루어진 부분집합만 구하기
        for(int len : course) {
            makeCombs(menus, orderSet, orderedMap, new boolean[menus.size()], len, 0, new TreeSet<>());
        }
    }
    
    private void makeCombs(List<Character> menus, List<Set<Character>> orderSet, Map<Set<Character>, Integer> orderedMap, boolean[] visited, int len, int idx, Set<Character> comb) {
        //만드려는 조합 개수를 충족했을 경우, 주문이 들어온 적 있는지 확인 후 "주문받았을 경우에" 저장 + 카운트 후 종료
        if(comb.size() == len) {
            for(Set<Character> order : orderSet) {
                if(order.containsAll(comb)) orderedMap.put(new TreeSet<>(comb), orderedMap.getOrDefault(comb, 0)+1);
            }
            
            return;
        }
        
        //가능한 조합 모두 찾기
        for(int i=idx; i<menus.size(); i++) {
            if(visited[i]) continue;
            
            visited[i] = true; 
            comb.add(menus.get(i));
            
            makeCombs(menus, orderSet, orderedMap, visited, len, i+1, comb);
            
            comb.remove(menus.get(i));
            visited[i] = false;
        }
    }
    
    private String[] makeCourseMenu(int[] course, Map<Set<Character>, Integer> orderedMap) {
        List<String> menu = new ArrayList<>();
        
        for(int cnt : course) {
            int max = 0;
            
            //cnt개로 이루어진 조합 중 가장 많이 주문된 '횟수'를 저장
            for(Set<Character> comb : orderedMap.keySet()) {
                if(comb.size() == cnt) max = Math.max(max, orderedMap.get(comb));    
            }
            
            //가장 많이 주문된 횟수를 가진 메뉴를 리턴할 리스트에 추가 (최대 횟수가 2 미만이라면 무시)
            if(max < 2) continue;
            
            for(Set<Character> comb : orderedMap.keySet()) {
                if(comb.size() == cnt && orderedMap.get(comb) == max) {
                    StringBuilder sb = new StringBuilder();
                    
                    for(Character c : comb) {
                        sb.append(c);
                    }
                    
                    menu.add(sb.toString());
                }  
            }
        }
        
        Collections.sort(menu); //사전순 정렬
        return menu.toArray(new String[0]);
    }
    
}