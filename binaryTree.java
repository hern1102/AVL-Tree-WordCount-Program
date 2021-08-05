/*
Description: A binary search tree is a tree whose left subtree has values lesser values 
than it's root and the right subtree having values greater than it's root. The binaryTree 
class contains the methods required to act as a Binary Search Tree. This tree will be 
able to add left and right nodes that will act as children. This class is a generic BST, 
which allows for the storage of multiple data types. 
 */

package project.pkg5;


public class binaryTree <T extends Comparable<T>>{

    /*
    An instance of Node is created in order to set an anchor known as root, and 
    also to allow the access of the Node class via composition.
    */
    
    protected Node<T> Root;

    public binaryTree()
    {
        Root = null;
    }
    
    
    /*
    Function: insert(T data, Node node) (private)
    Description: Inserts a node into the proper location of the tree
    Inputs: Takes the data that you would like to insert, and the node which will
    act like the root node when it's time to compare values. Once it finds its
    right location, it will then insert itself.
    Outputs: None
    */
    
    private void insert(T data, Node node){
        
        if(isEmpty()){
            
            this.Root = new Node(data);
            
        } else if (data.compareTo((T) node.getData()) < 0){
            if(node.left == null) {
                node.left = new Node(data);
            } else {
                insert(data,node.left);
            }
        } else if(data.compareTo((T) node.getData()) >= 0){
            if(node.right == null) {
                node.right = new Node(data);
            } else{
                insert(data,node.right);
            }
        }
        
    }
    
    /*
    Function: insert (public)
    Description: This method serves as the interface for the private Insert method.
    It takes the data passed from user and the Root node and passes them both to the
    private method.
    Inputs: Data that the user would like inserted into the BST.
    Outputs: None
    */
    
    
    public void insert(T data){
        insert(data, this.Root);
    }
    

    /*
    Function: Node remove(T data, Node n)(PRIVATE)
    Description: Removes a specific node from the tree
    Inputs: The node that you would want removed, and a node to help with comparison
    in order to search for the node you are looking to remove.
    Outputs: A node is outputted.
    */
    
    private Node remove(T data, Node n){
        
        if(isEmpty())
            return n; //empty tree do nothing

        if(data.compareTo((T) n.getData()) < 0){
            n.left = this.remove(data, n.left);
        } else if(data.compareTo((T) n.getData()) > 0){
            n.right = this.remove(data, n.right);
        } else if(n.left != null && n.right != null){
            
            n.setData(findMin(n.right)); 
            remove((T) n.getData(), n.right);
            
        } else {
            n = (n.left  != null) ? n.left : n.right;
        }
        
        return n;
    }
    
    
    /*
    Function: void remove(T data)
    Description: This method is simply an interface to the private remove method.
    Inputs: This method takes the data that the user wishes to remove from the tree
    Outputs: None
    */
    
    public void remove(T data){
        remove(data, this.Root);
    }
    
    
    /*
    Function: contains (private)
    Description: Reports if the tree contains a specific piece of data
    Inputs: The data that you wish to check for in the tree, and the node that will
    help with navigating the BST. 
    Outputs: true or false will be outputted depending on whether or not, the piece
    of data is in the tree or not.
    */
    
    private boolean contains(T data, Node n){

	if (n == null){
            return false;
	}else if (data.compareTo((T)n.getData()) < 0){
		return contains(data, n.left);
	}else if (data.compareTo((T)n.getData()) > 0){
                return contains(data, n.right);
	}else{
                return true;
	}
    }
    
    
    /*
    Function: contains (public)
    Description: This method is the interface of the private contains method.
    Inputs: The data that you wish to search for the BST
    Outputs: true or false depending on whether or not the piece of data you passed
    exists in the tree.
    */
    
    
    public boolean contains(T data){
        return contains(data, this.Root);
    }
    
    
    
    /*
    Function: findMin (private)
    Description: Finds and returns the smallest value in the tree
    Inputs: A node which will typically be the left node. 
    Outputs: The data of the node that will represent the small value in the tree,
    */
    
    private T findMin(Node n){
        if(n.left == null)
            return (T) n.getData();
        else
            return findMin(n.left);
    }
    
    
    /*
    Function: findMin (public)
    Description: This method acts as the public interface to the private findMin
    method.
    Inputs: No inputs required
    Outputs: The smallest value data in the entire tree will be returned.
    */
    
    
    public T findMin(){
        return this.findMin(this.Root);
    }
    
    
    
    /*
    Function: findMax (private)
    Description:  Finds and returns the largest value in the tree
    Inputs: A node to assist in the search for the largest value, will typically be
    the right node.
    Outputs: Data that will represent the largest value in the tree.
    */

    private T findMax(Node n){
        
        if(n.right == null)
            return (T) n.getData();
        else
            return findMin(n.right);
    }

    /*
    Function: findMax (public)
    Description: This method serves as an interface to the private findMax method.
    Inputs: None
    Outputs: Data that will represent the largest value in the tree.
    */
    
    public T findMax(){
        return this.findMax(Root);
    }
    

    /*
    Function: boolean isEmpty()
    Description: Determines if the tree is empty
    Inputs: None
    Outputs: True or false will be return depending on whether or not the tree 
    is empty.
    */
    
    public boolean isEmpty(){
        return this.Root == null;
    }

    
    /*
    Function: printTree (private)
    Description:  Visits each node in the tree and prints the data to the screen
    Inputs: The node that will help the method traverse the tree
    Outputs: The data will be printed to the console
    */
    
    private void printTree(Node n){
    
      if(n != null){
            
        System.out.println(n.getData());
        this.printTree(n.left);
        this.printTree(n.right);
        
      }
    }
    
    /*
    Function: printTree (public)
    Description: This method serves as the interface for the private printTree method
    Inputs: None
    Outputs: The data of the tree will be printed to the console
    */
    
    public void printTree(){
        printTree(this.Root);
    }
    

    /*
    public static void main(String [] args){
        binaryTree<Integer> st = new binaryTree<>();
        st.insert(10);
        st.insert(5);
        st.insert(15);
        st.insert(20);
        st.insert(12);
        st.insert(25);
    if(st.contains(10))
        System.out.println("Found");
    else
        System.out.println("Not Found");
    st.printTree();
    System.out.println("Remove Node\n");
    st.remove(10);
    st.printTree();

    if(st.contains(10))
        System.out.println("Found");
    else
        System.out.println("Not Found");      
    }
    */
}
