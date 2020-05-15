import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trie implements CS211CountingDictionaryInterface {

	TrieNode root = new TrieNode();
	
	public void insert(String key) {
		root.insert(key,"");
	}

	public boolean delete(String key) {
		if (root.find(key)==null)
			return false;
		else root.delete(key);
		return true;
	}

	public int find(String key) {
		if (root == null) 
			return -1;
		else {
			Word w = root.find(key);
			if (w == null)
				return -1;
			else {
				return w.getCount();
			}
		}
	}
	
	public Vector<Word> prefixMatch(String start) {
		Vector v = new Vector<Word>();
		root.prefixMatch(v,start);
		return v;
	}
	
	public Vector<Word> spellCheck1(String start) {
		Vector v = new Vector<Word>();
		root.spellCheck1(v,start);
		return v;
	}

	@Override
	public Vector<Word> allKeyValue() {
		Vector v = new Vector<Word>();
		root.allKeyValue(v);
		return v;
	}

	public Vector<Word> spellCheck2(String key, int errs) {
		Vector ws = new Vector<Word>();
		root.spellCheck2(ws,key,errs+1);
		return ws;
	}
	

	public void print() {
		root.print("");
	}


	public String anagram(String key) {
		//Searches the Trie to see if the key is an anagram of ay of the words in the trie. 
		//A word can be an Anagram of itself
		//Returns a vector of all the words it is an anagram of
		int err = key.length() + 5;
		boolean isAnagram = false;
		Vector<Word> aV = new Vector<Word>();
		Vector<Word> wV = spellCheck2(key, err); //returns all words in the trie of that length

		Object[] oA= wV.toArray();

		for (Object o : oA) {
			Word w = (Word) o;
			String s = w.revealTheString();
			List<Character> cL = new ArrayList<Character>(); 
			for(Character c: s.toCharArray()) {
				cL.add(c);
			}

			boolean containsW = true;
			for(int i = 0; i < key.length(); i++) {
				Character e = (Character)key.charAt(i);
				if(!cL.contains(e)) {
					containsW = false;
				} else {
					cL.remove(e);
				}
			}
			if(containsW) {
				isAnagram = true;
				aV.add(w);
			}
		}
		if(isAnagram) {
			return(key + " was an anagram of the following word(s): " + aV);
		}
		return (key + " is not an anagram of any word(s) in the Trie");
	}


	public static void main(String[] args) {
		Trie t = new Trie();
		
		System.out.println("Starting insert");
		t.insert("hello");
		t.insert("why");;
		t.insert("hellor");
		t.insert("hello");
		t.insert("mezzo");
		t.insert("mezza");
		t.insert("a");
		t.insert("he");
		t.insert("him");
		
		//Mine
		/*
		t.insert("olleh");
		t.insert("oleh");
		t.insert("ollhe");
		t.insert("loleh");
		t.insert("ollhe");
		t.insert("loleh");
		t.insert("olleh");

		
		
		t.insert("hellooo");
		t.insert("hellorrr");
		
		
		t.insert("a");

		t.insert("caw");
		t.insert("cow");
		t.insert("cry");
		t.insert("new");
		t.insert("not");
		t.insert("gawp");
		t.insert("yawp");
		t.insert("zen");
		t.insert("zqwa");
		*/

		
		System.out.println("Done with insert");
		System.out.println();

		System.out.println("Printing all");
		t.print();
		System.out.println();

		System.out.println("Starting the find search;");
		System.out.println(t.find("hello"));
		System.out.println(t.find("hellor"));
		System.out.println(t.find("why"));
		System.out.println();
		//All above is done


		System.out.println("All Key Value");
		Vector<Word> ws = t.allKeyValue();
		for (Word w: ws) {
			System.out.println("WS: " + w);
		}
		System.out.println();

		System.out.println("Prefix Match");
		ws = t.prefixMatch("mez");
		for (Word w: ws) {
			System.out.println("SS "+w);
		}
		System.out.println();

		System.out.println("Spell Check 1");
		ws = t.spellCheck1("hazzo");
		for (Word w: ws) {
			System.out.println("ST "+w);
		}
		System.out.println();

		System.out.println("Spell Check 2");
		ws = t.spellCheck2("hezzo", 1);
		for (Word w: ws) {
			System.out.println("EM "+w);
		}
		System.out.println();

		System.out.println(t.delete("why"));
		t.print();

		System.out.println("hunting for anagrams");
		System.out.println(t.anagram("b"));
	}
}