/*********************************************************************************************************************
 *The BinaryTree class has methods to add and traverse the binary tree and return data based on operations performed.
 */
public class Binarytree {
		
		private Node root;
		private int count;
		private static int preCounter;
		private static int postCounter;
		private static int inOrderCounter;
		private static int totalWords;
		private static String inOrderString ="";
		private static String preOrderString ="";
		private static String postOrderString ="";
		
		public void add(String data) {					//add data
		
			// See if the data already exists in the tree
			// If it does just return
			if (search(data)) {
				Node n = search(root,data);
				count = n.getCount();
				count++;							// increment count of the word already exist
				n.setCount(count);
				return;
			}
			
			if (root == null) {
				// Add root to the tree
				root = new Node();					//increment count if the root is null
				count++;
				root.setData(data, count);
			} else {
				internalAdd(root, data);
			}
		}
		
		private void internalAdd(Node root, String data) {
			if (root == null) {
				return;
			}
			if (data.compareTo(root.getWord()) < 1) {      //if word to add alphabetically comes before the read word
				if (root.getlChild() == null) {
					// add it here
					Node n = new Node();
					count = n.getCount();
					count++;
					n.setData(data,count);
					root.setlChild(n);
				} else {
					// recurse
					internalAdd(root.getlChild(), data);
				}
			} else {
			if (data.compareTo(root.getWord()) > -1) {	 //if word to add alphabetically comes after the read word
				if (root.getrChild() == null) {
					// add it here
					Node n = new Node();
					count = n.getCount();
					count++;
					n.setData(data,count);
					root.setrChild(n);
				} else {
					internalAdd(root.getrChild(), data); 
				}
			  }
			}			
		}
		
		
		public boolean search(String data) {				//search the target word
			return (search(root, data) != null);
		}
		
		private Node search(Node root, String data) {
			
			if (root == null) 
				return null;
			
			if (root.getWord().equals(data)) {
				return root;
			}
			
			if (data.compareTo(root.getWord()) < 1) {			//search left subtree
				return search(root.getlChild(), data);
			} else {
				return search(root.getrChild(), data); 			//search right subtree
			}
						
		}
		public Node searchMostOccur() {						//search most frequently occuring word
			return searchMFO(root, 0);
		}
		
		private Node searchMFO(Node root, int mostOccurWord) {
			
			if (root == null) 
				return null;
			
			if (root.getCount()> mostOccurWord) {
				mostOccurWord = root.getCount();
				return root;
			}
						
		   Node lMfow = searchMFO(root.getlChild(), root.getCount());
		   Node rMfow =searchMFO(root.getrChild(), root.getCount());
		   return rMfow.getCount() > lMfow.getCount() ? rMfow : lMfow;
		   
						
		}	
		public int searchTotalWords() {				//Search total count of words
			totalWords =-1;
			return searchTW(root);
		}
		
		private int searchTW(Node root) {
			if (root == null) { 
				return totalWords;
			}
			totalWords += root.getCount();
			 
		   totalWords = searchTW(root.getlChild());
		  // System.out.println(lMfow);
		   totalWords = searchTW(root.getrChild());
		   return  totalWords;
		   
						
		}
	
		public void printTree() {						//print the tree
			printTree(root);
			System.out.println();
		}
		
		private void printTree(Node root) {
			if (root == null) {
				return;
			}			
			printTree(root.getlChild());
			System.out.print(root.getWord()+" ");
			printTree(root.getrChild());
			
		}
		
		public String preOrder() {					//print first 20 words in preOrder traverse
			preCounter = 1;
			String result ="";
			result = preOrder(root);
			return result;
			//System.out.println();
		}
		
		private String preOrder(Node root) {
			if ((root == null)||( preCounter > 20)) {
				return preOrderString;
			}				
			//System.out.print(root.getWord() + " ");	
			preOrderString += root.getWord() + " "; 
			preCounter +=1;
			preOrder(root.getlChild());
			preOrder(root.getrChild());
			return preOrderString;
		}
		
		public String inOrder() {							//print first 20 words in inOrder traverse
			inOrderCounter =1;
			String result = "";
			result =inOrder(root);
			return result;
			//System.out.println();
		}
		
		private String inOrder(Node root) {
			if ((root == null) ||(inOrderCounter >20)) {
				return inOrderString;
			}
			inOrderCounter +=1;
			inOrder(root.getlChild());
			inOrderString += root.getWord() +" ";
			//System.out.print(root.getWord() + " ");
			inOrder(root.getrChild());
			return inOrderString;
			
		}
		public String postOrder() {							//print first 20 words in postOrder traverse
			postCounter =1;
			String result = "";
			result = postOrder(root);
			return result;
			//System.out.println();
		}
		
		private String postOrder(Node root) {
			if ((root == null)||(postCounter >20)) {
				return postOrderString;
			}
			postCounter +=1;
			postOrder(root.getlChild());
			postOrder(root.getrChild());
			postOrderString += root.getWord() + " ";
			//System.out.print(root.getWord() + " ");
			return postOrderString;
			
		}
		
		public int depth() {							//Find the depth of the tree
			return depth(root, 0, 0);
		}
		
		private int depth(Node root, int curLevel, Integer maxLevel) {
			if (root == null) {
				return curLevel;
			}
			
			curLevel++;
			
			if (curLevel  > maxLevel) {
				maxLevel = curLevel;			
			}
			
			int lmax = depth(root.getlChild(), curLevel, maxLevel);
			int rmax = depth(root.getrChild(), curLevel, maxLevel);
			
			return rmax > lmax ? rmax : lmax;
			

	 	}
		
		public int findWordCount(String word) {						//find the word count for target word
			int wordCount =0;
			if(search(word)) {
				Node n = search(root, word);
				wordCount = n.getCount();
			};
			
		  return wordCount; 	
		}

		public int wordsInTree() {								//Search different words in the tree	
			int result = wordsInTree(root) +1;  //add root node
			return result;    
		}		
		public int wordsInTree(Node root)
		{				
			if(root == null) 
				return 0;
			else
			   return 1 + wordsInTree(root.getlChild()) + wordsInTree(root.getrChild());
		
		}

		public String findRootWord() {				// Get the root word
			return root.getWord();
		}
		public String deepestLeaves() {					//Get the deepest leaves of the tree
			Node1 n = depthNd(root);
			return ( n.node.getWord());
		}
		
		private Node1 depthNd(Node root) {				
			    if( root == null ){
			        Node1 result = new Node1();
			        return result;
			    }
			    Node1 lResult = depthNd( root.getlChild() );
			    Node1 rResult = depthNd( root.getrChild() );
			    Node1 result = lResult.level < rResult.level ? rResult : lResult;
			    ++ result.level;
			    if( result.node == null)
			        result.node = root;
			    return result;
			  
			}

		private class Node1{							//private class for Node to find deepest leave
			private int level;
			private Node node;
			
		 public Node1() {
			 level =  0;
			 node =null;
		 }
		}
	
	}
