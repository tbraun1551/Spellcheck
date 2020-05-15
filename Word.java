
public class Word implements Comparable<Word> {

	private String theWord;
	private int theCount;
	
	public String toString() {
		return theWord+"  "+theCount;
	}
	
	public Word(String word, Integer count) {
		this.theWord = word.toLowerCase();
		theCount = count;
	}

	public String revealTheString() {
		return theWord;
	}

	public int revealTheCount() {
		return theCount;
	}

	public void incrementTheCount() {
		theCount++;
	}

	public int getCount() {
		return theCount;
	}

	/* compareTo should return a positive number if this is greater
	 * than other, 0 if they are equal and a negative number if this is less.
	 * 
	 * this is greater (less than) if its count is greater (less than) that of 
	 * other.  If the counts are equal, you should determine which theWord is
	 * larger as a String. compareTo is implemented in Java for Strings, you
	 * should use it
	 */
	public int compareTo(Word other) {
		if(this.theCount > other.theCount){
			return 1;
		}	else if(this.theCount == other.theCount) {
			if(this.theWord.compareTo(other.theWord) > 0) {
				return 1;
			} else if(this.theWord.compareTo(other.theWord) == 0) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
}
