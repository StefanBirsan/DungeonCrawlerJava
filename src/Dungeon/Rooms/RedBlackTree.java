package Dungeon.Rooms;

public class RedBlackTree {
    private Node root;
    private Node TNULL;

    public RedBlackTree() {
        TNULL = new Node(null);
        TNULL.color = false;
        root = TNULL;
    }

    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.room.getId() + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.room.getId() + " ");
            inOrderHelper(node.right);
        }
    }

    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.room.getId() + " ");
        }
    }

    private Node searchTreeHelper(Node node, int key) {
        if (node == TNULL || key == node.room.getId()) {
            return node;
        }

        if (key < node.room.getId()) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private void balanceInsert(Node k) {
        Node u;
        while (k.parent.color == true) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == true) {
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == true) {
                    u.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = false;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void preOrder() {
        preOrderHelper(this.root);
    }

    public void inOrder() {
        inOrderHelper(this.root);
    }

    public void postOrder() {
        postOrderHelper(this.root);
    }

    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    public void insert(Room room) {
        Node node = new Node(room);
        node.parent = null;
        node.room = room;
        node.left = TNULL;
        node.right = TNULL;
        node.color = true;

        Node y = null;
        Node x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.room.getId() < x.room.getId()) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.room.getId() < y.room.getId()) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        balanceInsert(node);
    }

    public Node getLeftChild(Room room) {
        Node node = searchTree(room.getId());
        return node != TNULL ? node.left : null;
    }

    public Node getRightChild(Room room) {
        Node node = searchTree(room.getId());
        return node != TNULL ? node.right : null;
    }


}