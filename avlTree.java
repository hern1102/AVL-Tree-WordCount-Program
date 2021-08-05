/*
Description: This file contains the AVL tree structure, which allows for a tree to
self balance it self out by checking if the nodes within the tree are properly balanced based 
off the data within the node. 
 */

package project.pkg5;


public class avlTree <T extends Comparable<T>> extends binaryTree<T> {
    

    
    public avlTree(){
        this.Root = null;
    }
    
    /*
    Function: Node insert (T data)
    Description: This method is the interface for the private insert method. See private 
    method just below for further desricption 
    Inputs: The data being inserted
    Outputs: None
    */
    
    @Override
    public void insert(T data){
        this.Root = insert(data, this.Root);
    }
    
    
    /*
    Function: Node insert (T x, Node t)
    Description: This method inserts a node into the AVL tree similar to the BST. 
    It first checks to see if the tree is empty or not. If the tree is empty, then 
    it will add the node. It will then check to see whether or not the value of the 
    node is greater than or less than, and finally call the balance method. TO balance 
    the tree.
    Inputs: The data being inserted, and the node being worked on will be the input
    Outputs: A node will be return that will pushed to the balance method.
    */

    
    private Node insert(T x, Node t){
        
        if(t == null)
            return new Node(x, null, null);

        if(x.compareTo((T) t.getData()) < 0){
            t.left = insert(x, t.left);
        } else if(x.compareTo((T) t.getData()) > 0){
            t.right = insert(x, t.right);
        }

        return balance(t);
    }
    
    
    private static final int ALLOWED_IMBALANCE = 1;
    
    /*
    Function: Node balance( Node t )
    Description: This method will balance out the nodes in the tree depending
    on what the height of the tree is. If the height of the tree is greater than
    the allowed imbalance, that the balance method will call the single rotate 
    or double rotate method (left or right) depending on what the situation calls for.
    Inputs: The node that will be check for balancing
    Outputs: A node will be returned.
    */
    
    private Node balance( Node t ){
        
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

       t.setHeight(Math.max( height( t.left ), height( t.right ) ) + 1);
        return t;
    }
    
    
    
    /*
    Function: remove( T x )
    Description: This method removes a specific piece of data from a tree
    Inputs: The piece of data that the user wishes to remove
    Outputs: None
    */
    
    
    @Override
    public void remove( T x ){
        Root = remove( x, this.Root );
    }
    
    /*
    Function: Node remove( T x, Node  t )
    Description: This method is the private method for remove that takes a node, 
    and the piece of data, and with that node traverse the tree until that piece of 
    data is found and then after the node is removed, the tree is balanced.
    Inputs: The piece of data that the user wishes to remove. and a node to traverse 
    the tree
    Outputs: A node will be the output
    */
    
    private Node remove( T x, Node  t ){
        if( t == null )
            return t;   // Item not found; do nothing
        
        if(x.compareTo((T) t.getData()) < 0)
            t.left = remove( x, t.left );
        else if( x.compareTo((T) t.getData()) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.setData(findMin( t.right ).getData());
            t.right = remove( (T) t.getData(), t.right );
        }
        else  // one or no children
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }
    
    /*
    Function: findMin(Node t)
    Description:  This method travers the tree until it finds the furtherest left
    node, and then it returns that node. since the tree will be balances, the left node
    with be the lowest value node
    Inputs: A node to be traverse will be the input
    Outputs: A node with the smallest value will be the output
    */
    
    private Node findMin( Node t ){
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }
    
    /*
    Function: public T findMin()
    Description: This method simply is the interface for the private findMin method
    and will pass the root to the private method
    Inputs: None
    Outputs: The lowest value. 
    */
    
    @Override
    public T findMin(){
        return (T) (findMin(this.Root).getData());
    }
    
    /*
    Function: Node findMax( Node t )
    Description:  This method finds the max value in the tree by traversing the furthest 
    right path that it can find.
    Inputs: A node to traverse will be the input
    Outputs: The output will be a node with the largest value
    */
    
    private Node findMax( Node t ){
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }
    
    /*
    Function: public T findMax()
    Description: This method is the interface to the findMax private method. 
    Inputs: None
    Outputs: The largest value in the tree
    */
    
    @Override
    public T findMax(){
        return (T) (findMax(this.Root).getData());
    }
    
    /*
    Function: int height( Node t )
    Description:  This method simply checks to see if the height of a node is null
    if so, hight is -1 and if node then it will get the height fo a node.
    Inputs: The node you wish to check the height for
    Outputs: AN in that will represent the height of the node
    */
    
    
    private int height( Node t ){
        return t == null ? - 1 : t.getHeight();
    }
    
    /*
    Function: Node rotateWithLeftChild( Node k2 )
    Description: This method will take a node as input, and pass that nodes
    left child, making that node the root and ultimately making the node passed
    the right child of the node of the roots previous node.
    Inputs: Node to be balanced
    Outputs: Node 
    */
    
    private Node rotateWithLeftChild( Node k2 ){
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.setHeight(Math.max( height( k2.left ), height( k2.right ) ) + 1);
        k1.setHeight(Math.max( height( k1.left ), k2.getHeight() ) + 1);
        return k1;
    }
    
    /*
    Function: Node rotateWithRightChild( Node k1 )
    Description: This method will take a node as input, and pass that nodes 
    right child, making that node the root and ultimately making the node
    passed the left child of the node of the roots previous node.
    Inputs: Node to be balanced
    Outputs: Node
    */
    
    private Node rotateWithRightChild( Node k1 ){
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.setHeight(Math.max( height( k1.left ), height( k1.right ) ) + 1); 
        k2.setHeight(Math.max( height( k2.right ), k1.getHeight() ) + 1);
        return k2;
    }
    
    /*
    Function: Node doubleWithLeftChild( Node k3 )
    Description: This method will 
    Inputs: 
    Outputs: 
    */
    
    private Node doubleWithLeftChild( Node k3 ){
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    
    /*
    Function: Node doubleWithRightChild( Node k1 )
    Description: This method will help in situations where a single rotation is 
    not enough. This method will conduct two rotations that will allow the tree balance.
    Inputs: Node to be balanced
    Outputs: A node
    */
    
    private Node doubleWithRightChild( Node k1 ){
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    
    /*
    Function: void printTree( Node t )
    Description: This method will print the subtree starting from the left subtree,
    to the right subtree
    Inputs: The root node will be passed to this method to traverse the tree
    Outputs: None, printed to the console however will be the tree
    */
    
    private void printTree( Node t ){
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.getData() );
            printTree( t.right );
        }
    }
    
    /*
    Function: void printTree( )
    Description:  This method is simply the interface to the private printTree method
    Inputs: None
    Outputs: None, printed to the console however will be the tree
    */
    
    @Override
    public void printTree( ){
        if( isEmpty() )
            System.out.println( "Empty tree" );
        else
            printTree( this.Root );
    }
    
    /*
    Function: boolean contains(T data)
    Description: This method is the interface to the private contains method
    Inputs: The data that will be checked to see if it exists in the tree
    Outputs: True or False depending on whether or not the data exists in the tree
    */
    
    @Override
    public boolean contains(T data){
	return contains(data, this.Root);
    }
    
    /*
    Function: boolean contains( T x, Node t )
    Description: This method will check to see if the data passed exists in the tree
    Inputs: This method will take as input, a node to assist in traversing the tree
    and the data to search for.
    Outputs: True or False depending on whether or not the data exists in the tree
    */
    
    private boolean contains( T x, Node t ){
        while( t != null )
        {
                    
            if( x.compareTo((T) t.getData()) < 0 )
                t = t.left;
            else if( x.compareTo((T) t.getData()) > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;// No match
    }
    
    /*
    Function: Node findNode(T x, Node t)
    Description: This method finds a node using a piece of data that it is searching
    for, and a node to help with traversing the tree
    Inputs: The piece of data to search for and a node
    Outputs: The node that you were looking for
    */
    
    
    private Node findNode(T x, Node t){
        
        while( t != null )
        {
                    
            if( x.compareTo((T) t.getData()) < 0 )
                t = t.left;
            else if( x.compareTo((T) t.getData()) > 0 )
                t = t.right;
            else
                return t;    // Match
        }

        return null;// No match
        
    }
    
    /*
    Function: Node findNode(T x)
    Description: This method will return the node with the specific data that you
    are search for.
    Inputs: The data that you are searching for.
    Outputs: The node that you were search for
    */
    
    
    public Node findNode(T x){
        
        return findNode(x, this.Root);
        
    }
    
    /*
    Function: incrementNode(T data)
    Description: This method calls the findNode method, and then increments that
    node's count variable
    Inputs: The data that you are searching for
    Outputs: None
    */
    
    public void incrementNode(T data){
        
        findNode(data).increment();
        
    }
    
    /*
    Function: printTreeWithCount(Node t)
    Description: This method will take a node as input, and travers a tree starting
    with the left branch first, and then right branch
    Inputs: A node to traverse the tree
    Outputs: prints tree to the console
    */
    
    private void printTreeWithCount(Node t){
        
        if( t != null )
        {
            printTreeWithCount( t.left );
            System.out.println( t.getData() + " - " + t.getCount());
            printTreeWithCount( t.right );
        }
        
    }
    
    /*
    Function: printTreeWithCount()
    Description: This method will is the interface for the private printTreeWithCount
    method.
    Inputs: None
    Outputs: Prints tree to the console
    */
    
    public void printTreeWithCount(){
        
        if( isEmpty() )
            System.out.println( "Empty tree" );
        else
            printTreeWithCount( this.Root );
        
    }
    
    
     
    /*
    // Test program
    public static void main(String [] args ){
        avlTree avl= new avlTree();
        
        avl.insert(23);
        avl.insert(18);
        avl.insert(44);
        avl.insert(6);
        avl.insert(12);
        avl.insert(52);
        avl.insert(2);
        avl.insert(45);
        avl.insert(100);
        avl.insert(21);
        avl.insert(1);


        avl.printTree();
        
        System.out.println("Max num in tree: " + avl.findMax());
        System.out.println("Min num in tree: " + avl.findMin());
        
        
        System.out.println("After Remove of 44");

        avl.remove(44);
        avl.printTree();
    }
    */
    
}
