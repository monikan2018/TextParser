
import java.io.*;
import java.util.*;

import com.sun.source.tree.BinaryTree;
/*Program is to use text file to load Binary tree and traverse the binary tree to generate statistics from tree.
 *Author: Monika Nanda
 *Programming Assignment 3
 *Date modified: March 4, 2019 
 */
public class Driver {
	String filename ="pg345.txt";
	Binarytree BTree = new Binarytree();
	DownloadAndDisplayFile df = new DownloadAndDisplayFile();
	public static void main(String[] args) throws IOException {
		Driver me = new Driver();
		me.doIt();				
	}
/* doIt method downloads and display the file, load linked list and call methods to perform different operations
 * using Binary Tree traversal methods. IOException error will trigger if file does not exist.
 */
	public void doIt() throws IOException{
		df.Download_file(filename);
								//Binary Tree operations		
		try {	
			Load_Binary_Tree();							//Load and Display File
		    Search_Word();								//Search Target words get their count
		    Depth_of_Tree();							//Get Binary Tree depth
		    Words_in_Book();							//Number of different words in the book
		    root_Word();								//Word at the root of tree
		    deepest_leaves_Tree();						//Get word at deepest leaves in the tree
		    Total_word_Entries();						//Total count of words in the book
		    mostOccurWord();							//Get words occur most fequently
			traversal_Display();						//Pre-order,Post-order,In-order Traversal
			
			
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
 /*Load_Binary_Tree loads the tree from the text file. Text is streamed through scanner and node is added with word 
 *and count of words to binary tree in alphabetical order . Count is incremented if the word already exit in the tree.  
 */
   public void Load_Binary_Tree() throws FileNotFoundException
   {	 
	 Scanner scr = new Scanner(new File(filename));	
	 while(scr.hasNext()) {
		 //loop to load all the words in list eliminating duplicates by adding count if word exist
			String word = scr.next();
			 BTree.add(word);
			}
	}
  /*Search_Word() performs the search on the Binary tree for the target in an array. If the word exist in the binary 
   *tree it returns the word count for tthe target else value 0 is returned.
   */
   public void Search_Word() {
	   String[] findWords = {"transylvania","harker","renfield","vampire","expostulate","fang"};
	   String indent ="              ";
	   System.out.println("*********************************************************************************");
	   System.out.println("Word"+"          "+"Count");
	   String output ="";
	   for(String word: findWords) {
		   //loop to find the words from String array in Linked list
		    int wordCount =0;
			wordCount = BTree.findWordCount(word);		
			output = word + indent.substring(0,indent.length()-word.length())+ Integer.toString(wordCount);
			System.out.println(output);		
		 }
	  }
   
   // Find the depth of the tree
   public void Depth_of_Tree() {
	 int depthTree= BTree.depth();  
	 System.out.println("The depth of the tree is : " + depthTree);   
   }
   
  //Find different words are there in the books
   public void Words_in_Book() {
	   System.out.println("No. of different words in the book: " + BTree.wordsInTree());
   }
   
   //Find word at the root of the tree
   public void root_Word() {
	   System.out.println("The word at the root is : "+ BTree.findRootWord());
   }
   
 //word_Entries method will count the total words in the book
   public void Total_word_Entries() {
	
		System.out.println("Tree is             :" + BTree.searchTotalWords());		  
   }
  //Traverse first 20 words of n=binary tree by preorder, postorder and inorder 
   public void traversal_Display() throws FileNotFoundException {
	    String preorder = BTree.preOrder();
		System.out.println("First 20 words pre order word : " + preorder);
		String postorder = BTree.postOrder();
		System.out.println("First 20 words post order word : " + postorder);
		String inorder =  BTree.inOrder();
		System.out.println("First 20 words in order word : " + inorder);
   }
   
  //Find word at the deepest level of the binary tree
   public void deepest_leaves_Tree() {
	   String deepestleave = BTree.deepestLeaves();
	   System.out.println("Deepest word(s) is/are : " + deepestleave);
	   
   }
   
 // Find the word that occured most frequently occured 
   public void mostOccurWord() {
	   Node mostOccur = BTree.searchMostOccur();
	   String wordmoccur= mostOccur.getWord();
	   int timesOccur = mostOccur.getCount();
	   System.out.println("Most Frequent is    : \""+ wordmoccur + "\" occuring "+timesOccur+" times " );
	   
   } 
	   
}