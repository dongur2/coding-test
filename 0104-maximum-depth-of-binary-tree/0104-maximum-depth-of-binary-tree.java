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
 //최대 깊이 리턴 
class Solution {
    int max = 0;
    public int maxDepth(TreeNode root) {
        dfs(root, 1);
        return max;
    }

    private void dfs(TreeNode curr, int depth) {
        //자식 노드가 없으면 직전 깊이와 비교 
        if(curr == null) {
            max = Math.max(max, depth-1);
            return;
        }

        //왼쪽 오른쪽 자식 노드로 이동 
        dfs(curr.left, depth+1);
        dfs(curr.right, depth+1);
    }
}