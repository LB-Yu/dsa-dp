package algorithm.tree;

import java.util.*;

/**
 * 二叉树相关算法:
 *     遍历: 层次遍历, 前序遍历, 中序遍历, 后序遍历
 *     重建: 根据中序遍历结果和其余任意一种遍历结果即可重建一棵唯一的二叉树
 * */
public class BinTree {

  /**
   * 二叉树节点类
   * */
  public static class BinTreeNode<T> {
    BinTreeNode<T> left;
    BinTreeNode<T> right;
    T value;

    public BinTreeNode(T value) {
      this.value = value;
    }
  }

  // Start ================================================== 遍历 ============================================== Start

  /**
   * 层次遍历
   * */
  public static <T> List<BinTreeNode<T>> levelOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> levelOrder = new ArrayList<>();
    if (root == null) return levelOrder;
    Queue<BinTreeNode<T>> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      BinTreeNode<T> node = q.poll();
      levelOrder.add(node);
      if (node.left != null) q.add(node.left);
      if (node.right != null) q.add(node.right);
    }
    return levelOrder;
  }

  /**
   * 递归前序遍历
   * */
  public static <T> List<BinTreeNode<T>> recursivePreOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> preOrder = new ArrayList<>();
    recursivePreOrder(root, preOrder);
    return preOrder;
  }

  private static <T> void recursivePreOrder(BinTreeNode<T> node, List<BinTreeNode<T>> preOrder) {
    if (node == null) return;
    preOrder.add(node);
    recursivePreOrder(node.left, preOrder);
    recursivePreOrder(node.right, preOrder);
  }

  /**
   * 非递归前序遍历
   * */
  public static <T> List<BinTreeNode<T>> preOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> preOrder = new ArrayList<>();
    Stack<BinTreeNode<T>> s = new Stack<>();
    while (root != null || !s.empty()) {
      while (root != null) {
        preOrder.add(root);
        s.push(root);
        root = root.left;
      }
      if (!s.empty()) root = s.pop().right;
    }
    return preOrder;
  }

  /**
   * 递归中序遍历
   * */
  public static <T> List<BinTreeNode<T>> recursiveInOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> inOrder = new ArrayList<>();
    recursiveInOrder(root, inOrder);
    return inOrder;
  }

  private static <T> void  recursiveInOrder(BinTreeNode<T> node, List<BinTreeNode<T>> inOrder) {
    if (node == null) return;
    recursiveInOrder(node.left, inOrder);
    inOrder.add(node);
    recursiveInOrder(node.right, inOrder);
  }

  /**
   * 非递归中序遍历
   * */
  public static <T> List<BinTreeNode<T>> inOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> inOrder = new ArrayList<>();
    if (root == null) return inOrder;
    Stack<BinTreeNode<T>> s = new Stack<>();
    while (root != null || !s.empty()) {
      while (root != null) {
        s.push(root);
        root = root.left;
      }
      if (!s.empty()) {
        BinTreeNode<T> node = s.pop();
        inOrder.add(node);
        root = node.right;
      }
    }
    return inOrder;
  }

  /**
   * 递归后序遍历
   * */
  public static <T> List<BinTreeNode<T>> recursivePostOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> postOrder = new ArrayList<>();
    recursivePostOrder(root, postOrder);
    return postOrder;
  }

  private static <T> void recursivePostOrder(BinTreeNode<T> node, List<BinTreeNode<T>> postOrder) {
    if (node == null) return;
    recursivePostOrder(node.left, postOrder);
    recursivePostOrder(node.right, postOrder);
    postOrder.add(node);
  }

  /**
   * 非递归后序遍历
   * */
  public static <T> List<BinTreeNode<T>> postOrder(BinTreeNode<T> root) {
    List<BinTreeNode<T>> postOrder = new ArrayList<>();
    if (root == null) return postOrder;
    Stack<BinTreeNode<T>> s = new Stack<>();
    s.push(root);
    BinTreeNode<T> pre = null;
    while (!s.empty()) {
      BinTreeNode<T> node = s.peek();
      if ((node.left == null && node.right == null) || (pre != null &&(node.right == pre || node.left == pre))) {
        postOrder.add(node);
        s.pop();
        pre = node;
      } else {
        if (node.right != null) s.add(node.right);
        if (node.left != null) s.add(node.left);
      }
    }
    return postOrder;
  }

  // End ================================================== 遍历 ============================================== End


  public static <T> void printBinTreeNodes(String hint, List<BinTreeNode<T>> nodes) {
    System.out.print(hint + ": ");
    for (BinTreeNode<T> node : nodes) {
      System.out.print(node.value + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    BinTreeNode<Integer> root = new BinTreeNode<>(1);
    root.left = new BinTreeNode<>(2);
    root.right = new BinTreeNode<>(3);
    root.left.left = new BinTreeNode<>(4);
    root.left.right = new BinTreeNode<>(5);
    root.right.left = new BinTreeNode<>(6);
    root.right.right = new BinTreeNode<>(7);

    // 层次遍历
    List<BinTreeNode<Integer>> levelOrder = BinTree.levelOrder(root);
    printBinTreeNodes("层次遍历", levelOrder);

    // 递归前序遍历
    List<BinTreeNode<Integer>> recursivePreOrder = BinTree.recursivePreOrder(root);
    printBinTreeNodes("递归前序遍历", recursivePreOrder);

    // 非递归前序遍历
    List<BinTreeNode<Integer>> preOrder = BinTree.preOrder(root);
    printBinTreeNodes("非递归前序遍历", preOrder);

    // 递归中序遍历
    List<BinTreeNode<Integer>> recursiveInOrder = BinTree.recursiveInOrder(root);
    printBinTreeNodes("递归中序遍历", recursiveInOrder);

    // 非递归中序遍历
    List<BinTreeNode<Integer>> inOrder = BinTree.inOrder(root);
    printBinTreeNodes("非递归中序遍历", inOrder);

    // 递归后序遍历
    List<BinTreeNode<Integer>> recursivePostOrder = BinTree.recursivePostOrder(root);
    printBinTreeNodes("递归后序遍历", recursivePostOrder);

    // 非递归后序遍历
    List<BinTreeNode<Integer>> postOrder = BinTree.postOrder(root);
    printBinTreeNodes("非递归后序遍历", postOrder);

    Stack<List<BinTreeNode<Integer>>> s = new Stack<>();
    s.add(new ArrayList<BinTreeNode<Integer>>() {{add(root); }});
  }
}
