/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and Tamassia
*
* This implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeLow is called.
*
* This implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
@SuppressWarnings({"rawtypes", "unchecked"})
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected Comparable[] storage;
	protected int currentSize;

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		// TODO: implement this
		currentSize = 1;
		storage = new Comparable[DEFAULT_SIZE + 1];
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		// TODO: implement this
		currentSize = 1;
		storage = new Comparable[size + 1];
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}

	public void insert (Comparable element) throws HeapFullException {
		// TODO: implement this
		if(currentSize == storage.length){
			throw new HeapFullException();
		}
		
		int index = currentSize;
		
		storage[index] = element;
		currentSize++;
		
		bubbleUp(index);
		
		// When inserting the first element, choose whether to use 
		// a 0-based on 1-based implementation. Whatever you choose,
		// make sure your implementation for the rest of the program
		// is consistent with this choice.		
    }
	
	public void bubbleUp(int index) {
		// TODO: implement this
		if(index == 1){
			return;
		}
		
		if(storage[index].compareTo(storage[index/2]) < 0){
			Comparable temp = storage[index];
			storage[index] = storage[index/2];
			storage[index/2] = temp;
		}else{
			return;
		}
		
		bubbleUp(index/2);
	}
			
	public Comparable removeMin() throws HeapEmptyException {
		if(currentSize == 1){
			throw new HeapEmptyException();
		}
		
		Comparable temp = storage[1];
		
		storage[1] = storage[currentSize-1];
		currentSize--;
		
		bubbleDown(1);
		
		return temp; // so it compiles
	}
	
	private void bubbleDown(int index) {
		// TODO: implement this
		if(2 * index >= currentSize){
			return;
		}
		int nextIndex = 2 * index;
		
		if(2 * index + 1 < currentSize){
			if(storage[2 * index].compareTo(storage[2*index + 1]) > 0){
				nextIndex = 2 * index + 1;
			}
		}
		
		if(storage[index].compareTo(storage[nextIndex]) > 0){
			Comparable temp = storage[nextIndex];
			storage[nextIndex] = storage[index];
			storage[index] = temp;
		}
		
		bubbleDown(nextIndex);
	}

	public boolean isEmpty(){
		if(size() == 0){
			return true;
		}
		
		return false; // so it compiles
	}
	
	public boolean isFull() {
		// TODO: implement this

		return currentSize == storage.length; // so it compiles
	}
	
	public int size () {
		// TODO: implement this
		
		return currentSize-1; // so it compiles
	}

	public String toString() {
		String s = "";
		String sep = "";
		// This implementation of toString assumes you 
		// are using a 1-based approach. Update the initial
		// and final value for i if using a 0-based
		for(int i=1; i<=currentSize-1; i++) {
			s += sep + storage[i];
			sep = " ";
		}
		return s;
	}
}
