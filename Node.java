/*
Description: The node class is a generic node class that has references to its 
left and right children. The data section of this class also contains private data 
that the node will hold, as well as the height. This node will serve crucial in the 
construction of the BST and the AVL tree.
 */

package project.pkg5;


public class Node <T extends Comparable<T>>{
    
        private T Data;
	protected Node left; // pointer to left child
	protected Node right; // pointer to right child
        private int height;
        private int count = 1;
        

        public Node(T data){
            this.Data = data;
        }
        
        public Node(T data, Node r, Node l){
            this.left = l;
            this.right = r;
            this.Data = data;
        }
        
        /*
        Function: T getData()
        Description: This method is a getter method that allows the user to pull
        down the data of the node which can be of any type.
        Inputs: None
        Outputs: The data of the node will be return.
        */

        public T getData() {
            return this.Data;
        }
        
        /*
        Function: void setData(T data)
        Description: This method is a setter method that will allow the user 
        to set the data of the node. public access to private data.
        Inputs: The data that the user will want inputted in the node
        Outputs: None
        */

        public void setData(T data) {
            this.Data = data;
        }
        
        /*
        Function: int getHeight()
        Description: This method is a getter method that will allow the user to 
        get the height of the node. This method will be useful while trying to keep
        the tree balanced.
        Inputs: None
        Outputs: Any int that will indicate whether or not the tree needs to be rotated
        */
        
        public int getHeight(){
            return this.height;
        }
        
        /*
        Function: void setHeight(int num)
        Description:  This method will allow the user to set the height of the node.
        Inputs: An int that will represent the height of the node.
        Outputs: None.
        */
        
        public void setHeight(int num){
            this.height = num;
        }
        
        
        /*
        Function: increment()
        Description: This method will allow the user to increment the count variable
        by one everytime the method is called
        Inputs: None
        Outputs: None
        */
        
        public void increment(){
            this.count++;
        }
        
        /*
        Function: int getCount()
        Description: This method is a getter method that will allow you to get the
        count of the node
        Inputs: None
        Outputs: An int that represent the amount of times the node data was read from
        a file or something else. There could be many uses from this variable.
        */
        
        public int getCount(){
            return this.count;
        }
    
}
