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
 import java.util.Queue; import java.util.ArrayDeque;
 //이진 트리에서 루트 노드가 주어졌을 때, 루트 레벨은 1이다. 레벨 x까지의 모든 노드의 값이 합이 가장 작아지는 가장 작은 레벨 x를 리턴
class Solution {
    public int maxLevelSum(TreeNode root) {
        //각 레벨의 합을 구해서 최소값을 구하기 
        return bfs(root);
    }

    public int bfs(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        int maxSum = Integer.MIN_VALUE;
        int answer = 1;
        int level = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            int sum = 0; //이번 레벨 합 초기화

            for(int i=0; i<size; i++) {
                TreeNode curr = q.poll();
                sum += curr.val;

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }

            if(sum > maxSum) {
                maxSum = sum;
                answer = level;
            }

            level++;
        }

        return answer;
    }
}