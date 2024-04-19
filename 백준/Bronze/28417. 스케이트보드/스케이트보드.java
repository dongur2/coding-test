import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int people = Integer.parseInt(sc.nextLine());
        
        int top = 0;
        for(int i=0; i<people; i++) {
            String input = sc.nextLine();
            
            int[] run = new int[2];
            int[] trick = new int[5]; 
            
            for(int j=0; j<2; j++) run[j] = Integer.parseInt(input.split(" ")[j]);
            for(int j=0; j<5; j++) trick[j] = Integer.parseInt(input.split(" ")[j+2]);
            
            Arrays.sort(trick);

            int score = Math.max(run[0], run[1]) + trick[4] + trick[3];
            top = (top < score)? score : top;
        }
        System.out.println(top);
    }
}