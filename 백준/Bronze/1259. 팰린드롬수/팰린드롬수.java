import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String input = br.readLine();
            if(input.equals("0")) break;

            if(isPal(input)) System.out.println("yes");
            else System.out.println("no");
        }
    }

    public static boolean isPal(String word) {
        Deque<Character> st = new ArrayDeque<>();

        for(char c:word.toCharArray()) {
            st.push(c);
        }

        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) == st.peek()) st.pop();
            else return false;
        }

        return st.isEmpty();
    }
}