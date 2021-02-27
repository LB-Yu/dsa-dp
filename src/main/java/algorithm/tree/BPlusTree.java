package algorithm.tree;

import java.util.*;

/**
 * @author Yu Liebing
 * */
public class BPlusTree<K extends Comparable<? super K>, V> {

  private static final int DEFAULT_ORDER = 3;

  private final int order;
  private int height;

  private Node<K, V> root;

  public BPlusTree() {
    this(DEFAULT_ORDER);
  }

  public BPlusTree(final int order) {
    if (order <= 2)
      throw new IllegalArgumentException();

    this.order = order;
    this.height = 1;
    this.root = new LeafNode<>(order);
  }

  public V search(K key) {
    Node<K, V> node = root;
    while (node.type != NodeType.LEAF_NODE) {
      InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
      int loc = keyBinSearch(internalNode.keys, key);
      if (loc >= 0) node = internalNode.children.get(loc + 1);
      else {
        loc = -loc - 1;
        node = internalNode.children.get(loc);
      }
    }
    LeafNode<K, V> leafNode = (LeafNode<K, V>) node;
    int loc = keyBinSearch(leafNode.keys, key);
    if (loc >= 0) return leafNode.values.get(loc);
    else return null;
  }

  public void insert(K key, V value) {
    insert(root, key, value);
  }

  private void insert(Node<K, V> node, K key, V value) {
    if (node.type == NodeType.LEAF_NODE) {
      LeafNode<K, V> leafNode = (LeafNode<K, V>) node;
      int loc = keyBinSearch(leafNode.keys, key);
      if (loc >= 0) leafNode.values.set(loc, value);
      else {
        loc = -loc - 1;
        leafNode.keys.add(loc, key);
        leafNode.values.add(loc, value);
      }
    } else {
      InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
      int loc = keyBinSearch(internalNode.keys, key);
      if (loc >= 0) insert(internalNode.children.get(loc + 1), key, value);
      else {
        loc = -loc - 1;
        insert(internalNode.children.get(loc), key, value);
      }
    }

    // solve overflow when the number of keys for the node is equal to order
    if (node.keys.size() == order) {
      if (node.type == NodeType.LEAF_NODE) {
        LeafNode<K, V> leafNode = (LeafNode<K, V>) node;
        LeafNode<K, V> sibling = new LeafNode<>(order);
        leafSplit(leafNode, sibling);
        if (leafNode.parent == null) {
          InternalNode<K, V> parent = new InternalNode<>(order);
          parent.keys.add(sibling.keys.get(0));
          leafNode.parent = parent;
          sibling.parent = parent;
          parent.children.add(leafNode);
          parent.children.add(sibling);
          root = parent;
          ++height;
        } else {
          InternalNode<K, V> parent = (InternalNode<K, V>) leafNode.parent;
          int loc = keyBinSearch(parent.keys, sibling.keys.get(0));
          if (loc < 0) loc = -loc - 1;
          parent.keys.add(loc, sibling.keys.get(0));
          parent.children.add(loc + 1, sibling);
          sibling.parent = parent;
        }
      } else {
        InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
        InternalNode<K, V> sibling = new InternalNode<>(order);
        K splitKey = internalSplit(internalNode, sibling);
        if (internalNode.parent == null) {
          InternalNode<K, V> parent = new InternalNode<>(order);
          parent.keys.add(splitKey);
          parent.children.add(internalNode);
          parent.children.add(sibling);
          internalNode.parent = parent;
          sibling.parent = parent;
          root = parent;
          ++height;
        } else {
          InternalNode<K, V> parent = (InternalNode<K, V>) internalNode.parent;
          int loc = keyBinSearch(parent.keys, splitKey);
          if (loc < 0) loc = -loc - 1;
          parent.keys.add(loc, splitKey);
          parent.children.add(loc + 1, sibling);
          sibling.parent = parent;
        }
      }
    }
  }

  public void delete(K key) {
    delete(root, key);
  }

  public void delete(Node<K, V> node, K key) {
    boolean remoevFirst = false;
    if (node.type == NodeType.INTERNAL_NODE) {
      InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
      int loc = keyBinSearch(internalNode.keys, key);
      if (loc >= 0) delete(internalNode.children.get(loc + 1), key);
      else {
        loc = -loc - 1;
        delete(internalNode.children.get(loc), key);
      }
    } else {
      LeafNode<K, V> leafNode = (LeafNode<K, V>) node;
      int loc = keyBinSearch(leafNode.keys, key);
      if (loc >= 0) {
        leafNode.keys.remove(loc);
        leafNode.values.remove(loc);
        if (loc == 0) remoevFirst = true;
        if (loc == 0 && leafNode.keys.size() >= order / 2 - 1) {
          if (node.parent != null) {
            InternalNode<K, V> parent = (InternalNode<K, V>) node.parent;
            int pLoc = keyBinSearch(parent.keys, key);
            if (pLoc >= 0) parent.keys.set(pLoc, leafNode.keys.get(0));
          }
        }
      } else return;
    }

    if (node.keys.size() < order / 2 - 1) {
      if (node.type == NodeType.LEAF_NODE) {
        LeafNode<K, V> leafNode = (LeafNode<K, V>) node;
        if (node.parent != null) {
          InternalNode<K, V> parent = (InternalNode<K, V>) node.parent;
          LeafNode<K, V> leftSibling;
          LeafNode<K, V> rightSibling;
          int loc = keyBinSearch(parent.keys, key);
          if (loc >= 0) {
            leftSibling = (LeafNode<K, V>) parent.children.get(loc);
            if (loc + 2 >= parent.children.size()) rightSibling = null;
            else rightSibling = (LeafNode<K, V>) parent.children.get(loc + 2);
          } else {
            int tLoc = -loc - 1;
            if (tLoc - 1 >= 0) leftSibling = (LeafNode<K, V>) parent.children.get(tLoc - 1);
            else leftSibling = null;
            if (tLoc + 1 >= parent.children.size()) rightSibling = null;
            else rightSibling = (LeafNode<K, V>) parent.children.get(tLoc + 1);
          }

          int sibling;
          if (leftSibling == null) sibling = 1;
          else if (rightSibling == null) sibling = 0;
          else sibling = leftSibling.keys.size() >= rightSibling.keys.size() ? 0 : 1;
          if (sibling == 0) {
            if (leftSibling.keys.size() > order / 2 - 1) {  // borrow form left sibling
              int lastIdx = leftSibling.keys.size() - 1;
              K lastKey = leftSibling.keys.get(lastIdx);
              leafNode.keys.add(0, leftSibling.keys.get(lastIdx));
              leafNode.values.add(0, leftSibling.values.get(lastIdx));
              leftSibling.keys.remove(lastIdx);
              leftSibling.values.remove(lastIdx);
              if (loc >= 0) parent.keys.set(loc, lastKey);
              else {
                loc = -loc - 1;
                parent.keys.set(loc - 1, lastKey);
              }
            } else {  // merge to left sibling
              leftSibling.keys.addAll(leafNode.keys);
              leftSibling.values.addAll(leafNode.values);
              leafNode.next = rightSibling;
              K deleteKey = remoevFirst ? key : leafNode.keys.get(0);
              int tLoc = keyBinSearch(parent.keys, deleteKey);
              parent.keys.remove(tLoc);
              parent.children.remove(tLoc + 1);
            }
          } else {
            if (rightSibling.keys.size() > order / 2 - 1) {
              // borrow form right sibling
            } else {
              // merge right sibling
            }
          }
        } // else: only one root node, we don't need to do anything
      } else {

      }
    }
  }

  private int keyBinSearch(List<K> keys, K key) {
    return Collections.binarySearch(keys, key);
  }

  private void leafSplit(LeafNode<K, V> leaf, LeafNode<K, V> sibling) {
    int splitLoc = order / 2;
    sibling.keys.addAll(leaf.keys.subList(splitLoc, order));
    sibling.values.addAll(leaf.values.subList(splitLoc, order));
    leaf.keys.subList(splitLoc, order).clear();
    leaf.values.subList(splitLoc, order).clear();
  }

  private K internalSplit(InternalNode<K, V> internal, final InternalNode<K, V> sibling) {
    int splitLoc = order / 2;
    sibling.keys.addAll(internal.keys.subList(splitLoc + 1, order));
    for (int i = splitLoc + 1; i < order + 1; ++i) {
      Node<K, V> c = internal.children.get(i);
      c.parent = sibling;
      sibling.children.add(c);
    }
    K splitKey = internal.keys.get(splitLoc);
    internal.keys.subList(splitLoc, order).clear();
    internal.children.subList(splitLoc + 1, order + 1).clear();
    return splitKey;
  }

  @Override
  public String toString() {
    Queue<Node<K, V>> queue = new LinkedList<>();
    queue.add(root);
    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
      int size = queue.size();
      sb.append("{");
      for (int i = 0; i < size; ++i) {
        Node<K, V> node = queue.poll();
        if (node.type == NodeType.INTERNAL_NODE) {
          InternalNode<K, V> internalNode = (InternalNode<K, V>) node;
          queue.addAll(internalNode.children);
        }
        sb.append(node);
        sb.append(", ");
      }
      sb.append("}\n");
    }
    return sb.toString();
  }

  public int getHeight() {
    return height;
  }

  enum NodeType {
    INTERNAL_NODE,
    LEAF_NODE
  }

  static class Node<K, V> {
    NodeType type;
    Node<K, V> parent;
    List<K> keys;

    @Override
    public String toString() {
      return keys.toString();
    }
  }

  static class InternalNode<K, V> extends Node<K, V> {
    List<Node<K, V>> children;

    InternalNode(final int order) {
      type = NodeType.INTERNAL_NODE;
      parent = null;
      // In fact, in a B+ tree of order m, the number of keys at any node will not exceed m-1.
      // But here we set the initial size of the list keys to m, this is to prevent the expansion
      // operation when overflow. And same with list children.
      keys = new ArrayList<>(order);
      children = new ArrayList<>(order + 1);
    }
  }

  static class LeafNode<K, V> extends Node<K, V> {
    List<V> values;
    Node<K, V> next;

    public LeafNode(final int order) {
      type = NodeType.LEAF_NODE;
      parent = null;
      next = null;
      keys = new ArrayList<>(order);
      values = new ArrayList<>(order);
    }
  }
}