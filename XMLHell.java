
import java.io.*;
import java.util.*;

public class XMLHell {

	public static void main(String[] args) throws Exception {
		File file = new File("/Users/jacobjulag-ay/Documents/GitHub/CS4112/foods.xml");
		BufferedReader br = new BufferedReader(new FileReader(file));
		Stack<String> stack = new Stack<String>();
		StringBuilder str = new StringBuilder();

		String st;
		String temp = "";
		while ((st = br.readLine()) != null) {
			temp += st; //Storing buffered reader into a String
		}

		// Going through String
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i) == '<') {
				// Checking if character contains '/', which indicates a closing tag
				if (temp.charAt(i + 1) == '/') {
					// Loop through closing tag and read inside of '</'
					for (int k = i + 2; k < temp.length(); k++) {
						if (temp.charAt(k) == '>') { // If '>' is ran into in closing tag, then pop string in between '</' & '>' closing tag from the stack
							String closing = temp.substring(i + 2, k); 
							if (stack.peek().equals(closing)) {
								stack.pop();
							}
						}
					}
				} else {
					// If there is no '/' then it's not a closing tag, but an opening tag
					// This is reading only in between the tags '<' & '>'
					for (int j = i + 1; j < temp.length(); j++) {
						if (temp.charAt(j) == '>') { 
							stack.push(temp.substring(i + 1, j)); // This is pushing the string in between '<' & '>'
							break;
						}
					}
				}
			}
		}

		// Check if stack is empty
		if (stack.empty()) {
			System.out.println("Valid XML");
		} else {
			System.out.println("Invalid XML");
		}
		System.out.println(stack);

	}
}
