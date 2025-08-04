/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.List; import java.util.ArrayList;
import java.util.Queue; import java.util.ArrayDeque;

//이진 트리의 루트 노드가 주어질 때, 오른쪽에서 봤을 때 보이는 노드를 깊이 순서대로 출력 
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();

        //깊이 순서대로 정렬해야하므로 bfs
        return bfs(root);
    }

    private List<Integer> bfs(TreeNode root) {
        List<Integer> rights = new ArrayList<>();

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root); //초기값 - 루트 

        while(!q.isEmpty()) {
            int size = q.size(); //각 트리 층==해당 층의 노드 개수
            
            for(int i=0; i<size; i++) {
                TreeNode curr = q.poll();

                //그 층의 마지막 노드일 경우 오른쪽에서 보이는 노드
                if(i == size - 1) rights.add(curr.val);

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }

        return rights;
    }
}