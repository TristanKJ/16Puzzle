import java.util.Queue;

/**
 * 
 * @author Tristan Knope-Jenkins
 *
 * @param <T>
 */

public class DLLQueue<T>{ //implements Queue<T>{

	class Node<T>
	{
		private Node<T> prev;
		private Node<T> next;
		private T data;
		
		public Node(T data)
		{
			this.data = data;
			prev = null;
			next = null;
		}
		public Node(Node<T> prev, T data, Node<T> next)
		{
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}
	
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public DLLQueue()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean add(T newItem)
	{
		if(newItem == null)
			throw new NullPointerException("Your new Item was null");
		
		if(size == 0)
			return addEmpty(newItem);
		else
		{
			Node<T> temp = new Node<T>(tail, newItem, null);
			tail.next = temp;
			tail = temp;
			size++;
			return true;
		}
	}
	
	private boolean addEmpty(T newItem)
	{
		Node<T> temp = new Node<T>(newItem);
		head = tail = temp;
		size++;
		return true;
	}
	
	public T remove()
	{
		if(size == 0)
			throw new IndexOutOfBoundsException("Tried to remove from empty list");
		else if(size == 1)
		{
			T tempData = head.data;
			clear();
			return tempData;
		}
		else
		{
			T tempData = head.data;
			head.next.prev = null;
			head = head.next;
			size--;
			return tempData;
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public void clear()
	{
		head = tail = null;
		size = 0;
	}
}
