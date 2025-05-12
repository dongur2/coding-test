import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //입력받은 국가 등수 출력 
    StringTokenizer st = new StringTokenizer(br.readLine());
    //국가 수 n (10^3)
    int n = Integer.parseInt(st.nextToken());
    //입력 국가 k 
    int k = Integer.parseInt(st.nextToken());
    
    List<Nation> list = new ArrayList<>();
    
    int[] arr = new int[4];
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      
      for(int j=0; j<4; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
      }
      
      list.add(new Nation(arr[0], arr[1], arr[2], arr[3]));
    }
    
    //정렬
    //1. 금메달 수 최대
    //2. 은메달 수 최대 
    //3. 동메달 수 최대 
    list.sort((a,b) -> {
      if(a.gold != b.gold) return b.gold-a.gold;
      if(a.silver != b.silver) return b.silver-a.silver;
      return b.bronze - a.bronze;
    });
    
    Map<Integer, List<Integer>> result = new HashMap<>(); //등수:{국가 번호}
    int rank = 1; int cnt = 1; //동점일 경우 랭크 건너뛸 카운트
    for(int i=0; i<list.size()-1; i++) {
      Nation curr = list.get(i);
      
      //추가
      result.computeIfAbsent(rank, key->new ArrayList<>()).add(curr.num);
      
      Nation next = list.get(i+1);
      //뒤와 같으면 랭크 그대로
      if(curr.gold==next.gold && curr.silver==next.silver && curr.bronze==next.bronze) {
        cnt++; continue;
      }
      //뒤와 다르면 랭크++
      else {
        rank += cnt; cnt = 1;
      }
    }
    
    //마지막 국가와 현재 랭크가 같으면 동점 
    Nation last = list.get(list.size()-1);
    if(result.containsKey(rank)) result.get(rank).add(last.num);
    else result.computeIfAbsent(rank, key->new ArrayList<>()).add(last.num);
    
    //k 국가의 등수
    for(int key:result.keySet()) {
      List<Integer> res = result.get(key);
      if(res.contains(k)) {
        System.out.println(key); return;
      }
    }

  }
  
  private static class Nation {
    int num, gold, silver, bronze;
    
    public Nation(int n, int g, int s, int b) {
      this.num=n; this.gold=g; this.silver=s; this.bronze=b;
    }
  }
}