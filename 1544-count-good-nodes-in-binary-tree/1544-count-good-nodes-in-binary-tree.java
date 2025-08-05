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
 //루트 노드부터 x노드까지 가는 길에 x보다 더 큰 값이 없으면 good node -> good node 개수  -> 조상 노드 중에 더 큰 값이 없음 
class Solution {
    int cnt = 0;
    public int goodNodes(TreeNode root) {
        dfs(root, root.val);
        return cnt;
    }

    private void dfs(TreeNode curr, int val) {
        if(curr == null) return;

        //넘어온 값보다 현재 노드의 값이 크면 카운트 
        if(val <= curr.val) {
            cnt++; val = curr.val;
        }

        dfs(curr.left, val);
        dfs(curr.right, val);
    }
}