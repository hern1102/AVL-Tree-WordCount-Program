/*
Description: THIS IS THE MAIN FILE. The wordCount class will read a file and will
then parse each word in that file, and add each word to the AVL tree created in the class.
If the program stumbles upon a word that it has not read yet, then it will add that 
word to the tree. If the word happens to be a word that we have already add to our tree, 
then the wordCount class will keep track of how many times we have seen that word. 
At the end of the program, the wordCount class will print out every word it has 
come across, and how many times it has seen that word.
 */

package project.pkg5;

import java.io.BufferedReader;
import java.io.FileReader;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author antho
 */
public class wordCount {
    
    public wordCount(){
        
    }
    
    /*
    An instance of the avlTree was created in order to store the words, and check
    if the words exists in the tree or not, in order to insert the words if they are 
    not. 
    */
    
    private avlTree avl = new avlTree();
    
    
    /*
    Function: void readFile(String path)
    Description: This method will simply read each line is a text file, and parse 
    each word in that text file and then call the wordCount method against each word.
    Inputs: The file path to the text file that you would like to read
    Outputs: None
    */
    
    
    public void readFile(String path){
            
            FileReader fr = null;
            BufferedReader br = null;

            try{
                
                fr  = new FileReader(path);
                br = new BufferedReader(fr);
                String line;

                while((line = br.readLine()) != null){

                    String[] words = line.split("[^a-zA-Z0-9'-]+");

                    for(String word : words){
                        if(!word.equals(""))
                            wordCount(word);
                       
                    }
 
                }
                
                printWordCount();
    
            }
            catch(Exception e){
                System.out.println("Error opening file");
                 }
            finally{
                try{
                    fr.close();
                    br.close();
                }
                catch(Exception e){
                System.out.println(e.getMessage());
                }
            }
        }
    
        /*
        Function: void printWordCount()
        Description:  This method will print every word that was read in the text
        file, and the number of times that the word was read.
        Inputs: None
        Outputs: The words read, and how many times they were read to the console
        */
    
        public void printWordCount(){
        
            avl.printTreeWithCount();
            
        }
        
        /*
        Function: void wordCount(String word)
        Description: This method will check to see if the word exists in the tree
        and if it does, then it will increment the amount of times that this word 
        has been read, and if it hasn't, then it will insert that word into the 
        AVL tree.
        Inputs: A string representing the word that we are searching for
        Outputs: None
        */
    
    
        public void wordCount(String word){
        
            if(avl.contains(toLowerCase(word)))
                avl.incrementNode(toLowerCase(word));
             else 
                avl.insert(toLowerCase(word));

        }
        
        
        /*
        The test code for this program simply creates an instance of this class, 
        and calls the readFile method in order to start counting the words. The 
        methods in the class will do the rest. The file that is being read from 
        is included in the project in order to make things easy.
        */
        
        
        public static void main(String [] args ){
        
            wordCount wc = new wordCount();
            wc.readFile("text\\WordCountTest.txt");

        }
        
}
