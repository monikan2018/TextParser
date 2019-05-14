import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
/*This class connects to website and streams data to the text file. Before the data is written to the file
 * the text is converted to lowercase and all punctuations are removed. Error message shows up if filename
 * does not exist.
 */
public class DownloadAndDisplayFile {
	public static final String UTF8_BOM = "\uFEFF";
	
	 public void Download_file(String file) throws IOException{
			
			System.out.println("Hi");
			
//Connect to the URL and Read File			
			String TxtURL ="http://www.gutenberg.org/cache/epub/345/pg345.txt";
		  	String filename = file;
			  try 
				{	  	
					BufferedReader in = null;
					URL url = new URL(TxtURL);	
					in = new BufferedReader(new InputStreamReader(url.openStream(),"ASCII"));
					BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
					String line ="";
					
//Read data streamed and process words					
					while((line = in.readLine())!= null)
					{	
						 line = process_words(line);
						 writer.write(line);//write process string to file						 
						 System.out.println(line);
					}
					in.close();
					writer.close();
					}	
				catch(FileNotFoundException e)
				{
					System.out.println("Error opening the file" + e.getMessage());
					System.exit(0);
				}
				catch(MalformedURLException e)
				{
					System.out.println("Error opening the file" + e.getMessage());
					System.exit(0);
				}
	 }
//Save string as String array of words and then process word by word and return as a string 	 
		 public String process_words(String line)
		  {
			  String newline ="";
			  String[] ln = line.split(" ");
			  for(int i = 0; i <ln.length;i++)
			  {	  			  
				 String word = ln[i];	
				word = Normalizer.normalize(word, Normalizer.Form.NFKD);
				 newline += getCharsAndDigits(word)+" ";
					 
			  }
			  return newline;
		}
//Remove from the word all punctuations and change it to lowercase and return the word.		 
	 public String getCharsAndDigits(String word) {
		 String newWord ="";
		 for(char c : word.toCharArray())
		  {
			 
		    if(Character.isLetterOrDigit(c))
		    {
			  newWord += Character.toString(c);  
			  newWord = newWord.toLowerCase();
		//	  normalize(newWord, newWord);
			}
		  } 
		 return newWord;
	 }
			  
}

