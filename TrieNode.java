import java.util.Vector;


public class TrieNode {

	private Word wordHere;

	private TrieNode[] links;


	public TrieNode() {
		wordHere = null;
		links= new TrieNode[26];
	}

	private int let(char c) {
		return c - 'a';
	}

	private char firstChar(String key) {
		return key.charAt(0);
	}
	
	private String rest(String key) {
		return key.substring(1,key.length());
	}
	
	private TrieNode linkWordStart(String start) {
		return links[let(firstChar(start))];
	}
	
	public void insert(String key, String toHere) {
	
		if(toHere.equals("")) {
			toHere = key;
		}

		if(toHere.length() > 1) {
			if(links[let(firstChar(toHere))] == null){
				links[let(firstChar(toHere))] = new TrieNode();
				links[let(firstChar(toHere))].insert(key, rest(toHere));
			} else {
				// System.out.println(toHere);
				links[let(firstChar(toHere))].insert(key, rest(toHere));
				// System.out.println("test");
			//	links[let(firstChar(toHere))].insert(key, rest(toHere));
			}
		} else if(toHere.length() == 1) {
			if(links[let(firstChar(toHere))] == null) {
				links[let(firstChar(toHere))] = new TrieNode();
				links[let(firstChar(toHere))].wordHere = new Word(key, 1);
				//System.out.println("Inserted: " + key);
			} else {
				//System.out.println(toHere);
				if(links[let(firstChar(toHere))].wordHere != null) {
					links[let(firstChar(toHere))].wordHere.incrementTheCount();
				} else {
					links[let(firstChar(toHere))].wordHere = new Word(key, 1);
				}
				//System.out.println("Inserted: " + key);
			}
		} 
	}

	
	public Word find(String key) {
		if (key.length() == 0) {
			if (wordHere==null)
				return null;
			else return wordHere;
		}
		else {
			if (linkWordStart(key) == null)
				return null;
			else return linkWordStart(key).find(rest(key));
		}		
	}

	public void allKeyValue(Vector v) {
		//Go trough the entire Trie, when it encounters a node thas word, add it to the vector of all the values
		for(int i = 0; i < 26; i++) {
			if(links[i] != null) {
				if(links[i].wordHere != null) {
					v.add(links[i].wordHere);
				}
				links[i].allKeyValue(v);
			}
		}
	}

	public void spellCheck1(Vector v, String start) {
		//Use a word to store the longest prefix returned and length
		int max = Integer.MIN_VALUE; //length of prefix
		int maxPre; //Record the prefix that returns the longest vector
		String p = "";
		
		for(int i = 0; i < start.length(); i++){
			Vector v2 = new Vector<Word>();
			p += start.charAt(i);
			prefixMatch(v2, p);
			int x = p.length();
			if(x > max && v2.size() != 0) {
				max = x;
				v.clear();
				v.addAll(v2);
			}
		}
	}



	public void prefixMatch(Vector v, String start) {
		//for all the nodes after the start if the wordHere isnt null add to vector
			if(start.length() != 0) {//goes to end of prefix
				if(links[let(firstChar(start))] == null) {
					return;
				}
				links[let(firstChar(start))].prefixMatch(v, rest(start));
			} else {
				if(wordHere != null) {
					v.add(wordHere);
				}
				for(int i = 0; i < 26; i++) {
					if(links[i] != null) {
						links[i].prefixMatch(v, start);
					}
				}
			}
	}

	public void spellCheck2(Vector ws, String key, int errs) {
		//Go through the Trie recursively every time you find a word check distance
		//if negative it means they arent same length and you should move on
		//If the word is greater than the length end the recursion
		String n = "";
		if(wordHere != null) {
			n = wordHere.revealTheString();
		}
		int numErrs = -1;
		if(n.length() == key.length() && wordHere != null) {
			numErrs = distanceChecker(key, n);
		}
		if(numErrs < errs && numErrs >= 0) {
			ws.add(wordHere);
		}
		for(int i = 0; i < 26; i++) {
			if(links[i] != null) {
				links[i].spellCheck2(ws, key, errs);
			}
		}

	}

	private int distanceChecker(String key, String a) {
		int distance = 0;
		if(a.length() !=  key.length()) {
			return -1;
		} else {
			for(int i = 0; i < key.length(); i++) {
				if(key.charAt(i) != a.charAt(i)) {
					distance++;
				}
			}
		}
		return distance;
	}
	
	public void print(String string) {
		if (wordHere != null)
			System.out.println(string+" "+wordHere);
		else {
			System.out.println(string+" empty");
		}
		for (int i =0; i<26; i++) {
			if (links[i] != null){
				links[i].print(string+"-");
			}
		}
	}

	public boolean delete(String key) {
		//Do this recursively and clean up the extra branches
		//If that node doesnt have any children then it should be made null
		if(find(key) == null) {
			return false; //Word was not initially in the Trie so just returns false
		}

		boolean deleted = false;
		delete2(key, deleted, 0);
		return deleted;
	}

	private boolean delete2(String key, boolean keyDeleted, int depth) {
		if(depth == key.length()) { //means you reached the node where the word should be
			if(wordHere.equals(key)); { //Redundant and useless but oh well. 
				wordHere = null;
				//System.out.println("FOOBAR");
				keyDeleted = true;
			}
		} else {
			//System.out.println("fubar");
			links[let(key.charAt(depth))].delete2(key, keyDeleted, depth + 1);
			if(!links[let(key.charAt(depth))].anyKids()){
				links[let(key.charAt(depth))] = null;
			}
		}
		return keyDeleted;
	}


	public boolean anyKids() {
		for(int i = 0; i < 26; i++) {
			if(links[i] != null || wordHere != null) {
				return true;
			}
		}
		return false;
	}
}

