// --== CS400 File Header Information ==--
// Name: Oneeb Zaheer
// Email: ozaheer@wisc.edu
// Team: N/A
// TA: N/A
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing the nodes within a
 * binary search tree. You can use this class' insert method to build a binary search tree, and its
 * toString method to display the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data;
    public boolean isBlack;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;

    public Node(T data) {
      this.data = data;
      isBlack = false;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     * 
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { // display subtree in order traversal
      String output = "[";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the tree already contains data
   */
  public void insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
    } // add first node to an empty tree
    else {
      insertHelper(newNode, root);
    }
    root.isBlack = true;
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
   */
  private void insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      throw new IllegalArgumentException("This RedBlackTree already contains that value.");

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }
  }

  private void enforceRBTreePropertiesAfterInsert(Node<T> violatingRedNode) {
    // check node's aunt's color to determine which case
    Node<T> violatingNodeAunt;

    if (!violatingRedNode.parent.isBlack) { // first check if parent is red (presenting a violation)

      if (violatingRedNode.parent.isLeftChild()) {
        violatingNodeAunt = violatingRedNode.parent.parent.rightChild;
      } else {
        violatingNodeAunt = violatingRedNode.parent.parent.leftChild;
      }
      // If the aunt is black we rotate, then re-color (done in the rotate methods)
      if (violatingNodeAunt == null || violatingNodeAunt.isBlack) {
        rotate(violatingRedNode, violatingRedNode.parent);
      } else { // This is the case where aunt is red
        // essentially setting parent and aunt to black and grandparent to red
        violatingRedNode.parent.isBlack = true;
        violatingNodeAunt.isBlack = true;
        violatingRedNode.parent.parent.isBlack = false;

      }
    }
  }


  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   * 
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this BST. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

    if (parent.leftChild != null && parent.leftChild.equals(child)) {
      rotateRight(child, parent);
    } else if (parent.rightChild != null && parent.rightChild.equals(child)) {
      rotateLeft(child, parent);
    } else {
      throw new IllegalArgumentException("No relationship between parent and child nodes.");
    }
  }

  private void rotateRight(Node<T> child, Node<T> parent) {
    if (parent.parent != null) {
      if (parent.isLeftChild()) { // These represent the left-left cases (simple right rotation)
        if (parent.parent.equals(root)) { // case where parent becomes new root (smaller tree)
          Node<T> grandparent = parent.parent;
          root = parent;

          // set grandparent's left child to parent's old right child (and the other way)
          // (IF parent has a right child, otherwise this would throw null)
          if (parent.rightChild != null) {
            grandparent.leftChild = parent.rightChild;
            parent.rightChild.parent = grandparent;
            // parent's right child is now grandparent
            parent.rightChild = grandparent;
            grandparent.parent = parent;
          } else {
            // parent's right child is now grandparent
            parent.rightChild = grandparent;
            grandparent.parent = parent;
            // erase grandparent's left-child connection to parent
            grandparent.leftChild = null;
          }
        } else { // a bigger tree where grandparent is not the root (not at all the same)
          Node<T> grandparent = parent.parent;

          // set grandparent's left child to parent's old right child (and the other way)
          if (parent.rightChild != null) {
            grandparent.leftChild = parent.rightChild;
            parent.rightChild.parent = grandparent;
            // parent's right child is now grandparent
            parent.rightChild = grandparent;
            grandparent.parent = parent;
          } else {
            //erase grandparent's left child connection to parent before changing relationships
            grandparent.leftChild = null;
            // change parent's parent to grandparent's parent
            parent.parent = grandparent.parent;
            // parent's right child is now grandparent
            parent.rightChild = grandparent;
            //change grandparent's parent's right child to parent
            grandparent.parent.rightChild = parent;
            //finally solidify grandparent as parent's new child
            grandparent.parent = parent;
            
            
          }
        }
        // re-coloring step (after rotations)
        parent.isBlack = true;
        parent.leftChild.isBlack = false;
        parent.rightChild.isBlack = false;

      } else { // Now enter right-left cases (need to do a right rotation followed by a left)
        Node<T> grandparent = parent.parent;
        grandparent.rightChild = child;
        child.parent = grandparent;

        parent.leftChild = null;
        child.rightChild = parent;
        parent.parent = child;
        // right rotation complete! now, we call rotateLeft()
        rotateLeft(parent, child); // parent is the new child node and child is new parent
      }
    } else { // the case when no grandparent exists WORKS
      child.parent = null;
      root = child;

      child.rightChild = parent;
      parent.parent = child;
      parent.leftChild = null;

    }

    /*
     * Node<T> origChildRightChild = child.rightChild; if (origChildRightChild != null) {
     * parent.leftChild = origChildRightChild; origChildRightChild.parent = parent; }
     */

  }

  private void rotateLeft(Node<T> child, Node<T> parent) {
    if (parent.parent != null) {
      if (!parent.isLeftChild()) { // this is a right-right case, so simple left rotation
        if (parent.parent.equals(root)) { // grandparent is root so parent becomes new root
          Node<T> grandparent = parent.parent;
          root = parent;

          // set grandparent's right child to parent's old left child, and vice versa
          // ONLY if parent even has a left child
          if (parent.leftChild != null) {
            grandparent.rightChild = parent.leftChild;
            parent.leftChild.parent = grandparent;
          
          // parent's left child is now grandparent
          parent.leftChild = grandparent;
          grandparent.parent = parent;
          } else {
            //erase grandparent's right child connection to parent
            grandparent.rightChild = null;
            parent.leftChild = grandparent;
            grandparent.parent = parent;

          }
        } else { // if grandparent is not root, everything else is literally the same
          Node<T> grandparent = parent.parent;

          // set grandparent's right child to parent's old left child, and vice versa
          if (parent.leftChild != null) {
            grandparent.rightChild = parent.leftChild;
            parent.leftChild.parent = grandparent;
          
          // parent's left child is now grandparent
          parent.leftChild = grandparent;
          grandparent.parent = parent;
          } else { // this is where i made a change (simply the else and first line)
            //erase grandparent's right child connection to parent
            grandparent.rightChild = null;
            //change parent's parent to grandparent's parent
            parent.parent = grandparent.parent;
            //parent's left child is now grandparent
            parent.leftChild = grandparent;
            //change grandparent's parent's left child to parent
            grandparent.parent.leftChild = parent;
            //solidify grandparent as parent's new child
            grandparent.parent = parent;
          }
        }
        // re-coloring step after rotations
        parent.isBlack = true;
        parent.leftChild.isBlack = false;
        parent.rightChild.isBlack = false;

      } else { // this is a left-right case, so rotate left then right (by calling rotateRight)
        Node<T> grandparent = parent.parent;
        grandparent.leftChild = child;
        child.parent = grandparent;

        parent.rightChild = null;
        child.leftChild = parent;
        parent.parent = child;
        // left rotation complete! now we call rotateRight
        rotateRight(parent, child);
      }
    } else { // The case when no grandparent exists, similar to the case above in code
      child.parent = null;
      root = child;

      child.leftChild = parent;
      parent.parent = child;
      parent.rightChild = null;

    }
  }
}

