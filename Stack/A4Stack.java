public class A4Stack<T> implements Stack<T> {
	private A4Node<T> head;
	private int size = 0;

	public A4Stack() {
		head = null;
	}
	
	public void push(T value){
		if(head == null){
			head = new A4Node(value);
		} else {
			A4Node n = new A4Node(value);
			n.setNext(head);
			head = n;
		}
		size++;
	}
	
	public T pop(){
		if(isEmpty()){
			return null;
		}
		T data = head.getData();
		head = head.getNext();
		return data;
	}
	
	public boolean isEmpty(){
		if(head == null){
			return true;
		}
		
		return false;
	}
	
	public T top(){
		if(head == null){
			return null;
		}
		return head.getData();
	}
	
	public void popAll(){
		head = null;
	}

}