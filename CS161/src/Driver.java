
import java.util.*;
import java.io.*;
 
public class Driver {
 
	public static void main(String[] args) throws FileNotFoundException {
                               
	int[] valuesArray = {1,5,6,2,4,6,9,8,3,10,0,11};
	Node arrayHead;
	Node current;

	arrayHead = new Node(valuesArray[0]);
	current = arrayHead;
	for(int i = 1; i < valuesArray.length; i++) {
		current.next = new Node(valuesArray[i]);
		current.next.prev = current;
		current = current.next;
	}
                              
	printLinkedList(arrayHead);
                               
	}// main
               
	public static void printLinkedList(Node head){
		System.out.print(head.value + " ");
		if(head.next != null){
			printLinkedList(head.next);
		}
	}
 
}// Driver