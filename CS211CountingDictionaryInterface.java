import java.util.Vector;

public interface CS211CountingDictionaryInterface {
	public void insert(String key); 
	/* insert the given key storing 1 as the value if the key does not already exist
	*  if the key already
	*  exists, increment the associated value 
	*/
	
	public boolean delete(String key);
	/* delete the given key, if the key was in the dictionary, return true
	 * otherwise, return false  Implementing this is optional unless you use 
	 * an array as your D/S, in which case it is required
	 */
	
	public int find(String key);
	/* return the value associated with the given key,  If the key is NOT in the dictionary,
	 * return -1
	 */
	
	public Vector<Word> allKeyValue();
	/* return a list of all the key-value Words in the dictionary.  If your D/S is 
	 * a BST or an ordered array, they should be in order.
	 */
	
}
