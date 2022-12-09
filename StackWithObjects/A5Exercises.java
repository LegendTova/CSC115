// Tyson Chan
// V00985911
public class A5Exercises {

	// PART 1

	/*
	 * Purpose: get a count of the number of elements in the array
	 * Parameters: int[] arr
	 * Returns: int - the number of elements
	 * Post-condition - the array contents are unchanged
	 */
	public static int countMultiples(int[] arr, int x) {
		if(arr.length == 0){
			return 0;
		}
		
		return countMultiplesRec(arr, x, arr.length-1); // so it compiles
	}
		
	public static int countMultiplesRec(int[] arr, int x, int i){
		if(i < 0){
			return 0;
		} 
		
		if(arr[i] % x == 0){
			return 1 + countMultiplesRec(arr, x, i-1);
		}
		
		return countMultiplesRec(arr, x, i-1);
	}
	/*
	 * Purpose: double all values in the given array
	 * Parameters: int[] array - the array to modify
	 * Returns: void - nothing
	 */
	public static void doubleAll(int[] array) {
		if(array.length == 0){
			return;
		}
		
		doubleAllRec(array, array.length-1);
	}
	
	public static void doubleAllRec(int[] array, int i) {
		if(i < 0){
			return;
		}

		array[i] = array[i] * 2;
		
		doubleAllRec(array, i-1);
		
	}
	
	/*
	 * Purpose: get the minimum value found in the array
	 * Parameters: int[] array - the array to search
	 * Returns: int - minimum value found in the array
	 *                or -1 if the array is empty
	 * Post-condition - the array contents are unchanged
	 */
	public static int getMinimum(int[] array) {
		if(array.length == 0){
			return -1;
		}
		
		return getMinimumRec(array, 2147483647, array.length-1);
	}
	
	public static int getMinimumRec(int[] array, int min, int i) {
		if(i < 0){
			return min;
		}
		if(min > array[i]){
			min = array[i];
		}
		
		return getMinimumRec(array, min, i-1);
		
	}



	// PART II

	/*
	 * Purpose: get the total number of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of books
	 * Post-condition: s is not modified
	 */
	public static int totalBooks(Stack<Book> s) {
		Stack<Book> temp = new A5Stack<Book>();
		int num = totalBooksRec(s, temp);
		stackReconstruction(s, temp);
		
		return num;
	}
	
	public static int totalBooksRec(Stack<Book> s, Stack<Book> temp){
		if(s.top() == null){
			return 0;
		}
		
		temp.push(s.pop());
		return 1 + totalBooksRec(s, temp);
		
	}


	/*
	 * Purpose: get the total number of pages of all 
	 *          books in the stack
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of pages
	 * Post-condition: s is not modified
	 */
	public static int totalPages(Stack<Book> s) {
		Stack<Book> temp = new A5Stack<Book>();
		int num = totalPagesRec(s, temp);
		stackReconstruction(s, temp);
		
		return num;
	}
	
	public static int totalPagesRec(Stack<Book> s, Stack<Book> temp){
		if(s.top() == null){
			return 0;
		}
		
		Book tempBook = s.pop();
		
		temp.push(tempBook);
		return tempBook.getPages() + totalPagesRec(s, temp);
	}
	
	
	/*
	 * Purpose: get the average number of pages of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: double - the average number of pages
	 *                   0.0 if there are no books in s
	 * Post-condition: s is not modified
	 */
	public static double averagePages(Stack<Book> s) {
		// You don't need to change this, if you have
		// completed the previous two exercises
		// correctly, it should pass all the tests
		if (s.isEmpty()) {
			return 0.0;
		} else {
			double sum = totalPages(s);
			int count = totalBooks(s);
			return sum/count;
		}
	}

	/*
	 * Purpose: determine whether toFind is contained in s
	 * Parameters: Stack<Book> s - the stack of books
	 *             Book toFind - the book to search for
	 * Returns: boolean - true if s contains toFind, false otherwise
	 * Post-condition: s is not modified
	 */
	public static boolean containsBook(Stack<Book> s, Book toFind) {
		Stack<Book> temp = new A5Stack();
		boolean contains = containsBookRec(s, toFind, temp);
		stackReconstruction(s, temp);
		
		return contains;
	}
	
	public static void stackReconstruction(Stack<Book> s, Stack<Book> temp){
		if(temp.top() == null){
			return;
		}
		
		s.push(temp.pop());
		stackReconstruction(s, temp);
	}
	
	public static boolean containsBookRec(Stack<Book> s, Book toFind, Stack<Book> temp){
		if(s.top() == toFind){
			return true;
		} else if(s.top() == null){
			return false;
		}
		
		temp.push(s.pop());
		
		return containsBookRec(s, toFind, temp);
	}

	/*
	 * Purpose: determine the books in s are stacked correctly
	 *          (ie. there is never a book stacked on top of 
	 *               another book with fewer pages)
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: boolean - true if books in s are stacked correctly
	 * Post-condition: s is not modified
	 */
	public static boolean stackedCorrectly(Stack<Book> s) {
		Stack<Book> temp = new A5Stack();
		
		boolean correct = stackedCorrectlyRec(s, temp);
		
		stackReconstruction(s, temp);
		
		return correct;
	}
	
	public static boolean stackedCorrectlyRec(Stack<Book> s, Stack<Book> temp) {
		Book b = s.pop();
		temp.push(b);
		if(s.top() == null){
			return true;
		}
		if(b.getPages() > s.top().getPages()){
			return false;
		}
		
		return stackedCorrectlyRec(s, temp);
	}
}