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
 //key 제거
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null; 

        //x<key -> right
        if(root.val < key) root.right = deleteNode(root.right, key);
        //x>key -> left
        else if(root.val > key) root.left = deleteNode(root.left, key);
        //x=key
        else {
            //왼쪽 자식 노드가 없으면 오른쪽 자식 노드로 대체 
            if(root.left == null) return root.right;
            //오른쪽 자식 노드가 없으면 왼쪽 자식 노드로 대체 
            if(root.right == null) return root.left;
            
            //양쪽 자식 노드가 있으면, 왼쪽 부분 트리에서 최대값
            int maxLeft = getMaximum(root.left);
            root.val = maxLeft;
            root.left = deleteNode(root.left, maxLeft);
        }
        
        return root;
    }

    private int getMaximum(TreeNode node) {
        while (node.right != null) node = node.right;
        return node.val;
    }
}