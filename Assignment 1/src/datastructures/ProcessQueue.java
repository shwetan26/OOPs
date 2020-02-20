package datastructures;

import exceptions.InvalidCapacityException;
import models.Process;

/**
 * @author shweta
 * 
 * This class represents the queue holding processes Queue will have two
 * pointers front and rear, front will point to first element and rear
 * will point to last element head points to first node of circular
 * doubly linked list Default initialization capacity of queue is 2,
 * user can create queue with any capacity greater than 1
 *
 */
public class ProcessQueue {
	private ProcessNode front, rear;
	private ProcessNode head;
	private int capacity;
	private int size;
	private int totalNodes = 0;
	private int INITIAL_CAPACITY = 2;

	public ProcessQueue() {
		front = null;
		rear = null;
		head = null;
		size = 0;
		capacity = INITIAL_CAPACITY;
	}

	/**
	 * return true is size is 0 i.e. queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * return true if queue is full with elements
	 */
	public boolean isFull() {
		return size == capacity;
	}

	/**
	 * allocate process nodes for given capacity
	 * 
	 * @param newCapacity
	 * @throws InvalidCapacityException
	 */
	public void createQueue(int newCapacity) throws InvalidCapacityException {
		// if capacity is not grater than 1 throw exception
		if (newCapacity < INITIAL_CAPACITY) {
			throw new InvalidCapacityException("Capacity should be greater than : " + INITIAL_CAPACITY);
		}
		while (totalNodes != newCapacity) {
			ProcessNode newNode = new ProcessNode();
			// add first node
			if (head == null) {
				newNode.setNextLink(newNode);
				newNode.setPrevLink(newNode);
				head = newNode;
			} else {
				// add new node at the end of last node
				ProcessNode lastNode = head.getPrevLink();
				newNode.setNextLink(head);
				head.setPrevLink(newNode);
				newNode.setPrevLink(lastNode);
				lastNode.setNextLink(newNode);
			}
			totalNodes++;
		}
		// update the capacity with new capacity
		capacity = newCapacity;
	}

	/**
	 * return number of nodes in queue
	 * 
	 * @return total number of nodes
	 */
	public int getQueueCapacity() {
		return totalNodes;
	}

	/**
	 * insert an element(process) in queue, if queue is not initialized create queue
	 * and insert
	 * 
	 * @param newProcess
	 * @throws InvalidCapacityException
	 */
	public void insertQueue(Process newProcess) throws InvalidCapacityException {
		// if queue is not initialized first initialize queue with default capacity
		if (isEmpty()) {
			createQueue(capacity);
		}
		if (isFull()) {
			// queue is full, double the capacity of queue
			createQueue(capacity * 2);
		}
		if (front == null && rear == null) {
			// add first element in queue
			front = rear = head;
			front.setProcess(newProcess);
		} else {
			// new element will be added from rear end
			rear = rear.getNextLink();
			rear.setProcess(newProcess);
		}
		size++;
	}

	/**
	 * removes the first element from queue
	 * 
	 * @return first element
	 */
	public ProcessNode removeFromQueue() {
		if (isEmpty()) {
			return null;
		}
		ProcessNode currentNode = front;
		// delete last node
		if (front == rear) {
			front = null;
			rear = null;
		} else {
			ProcessNode lastNode = currentNode.getPrevLink();
			lastNode.setNextLink(currentNode.getNextLink());
			currentNode.getNextLink().setPrevLink(lastNode);
			// element is removed from front end , FIFO
			front = front.getNextLink();
		}
		size--;
		return currentNode;
	}

	/**
	 * @return size
	 */
	public int getQueueSize() {
		return size;
	}

	/**
	 * print queue elements in FIFO order
	 * 
	 * @return String representation of queue elements
	 */
	public String printQueue() {
		Process[] processList = generateProcessArray();
		return printQueueArray(processList);
	}

	/**
	 * helper method to print queue elements stored in array
	 * 
	 * @param processList
	 * @return String representation of queue elements
	 */
	public String printQueueArray(Process[] processList) {
		StringBuilder str = new StringBuilder();
		for (Process currentProcess : processList) {
			str.append(currentProcess).append("\n");
		}
		return str.toString();
	}

	/**
	 * helper method to generate array for queue elements
	 * 
	 * @return array generated from queue elements
	 */
	private Process[] generateProcessArray() {
		Process[] processList = new Process[getQueueSize()];
		ProcessNode current = front;
		int index = 0;
		while (current != rear) {
			processList[index] = current.getProcess();
			current = current.getNextLink();
			index++;
		}
		// add last element
		processList[index] = current.getProcess();
		return processList;
	}

	/**
	 * display queue elements orderd by PID
	 * 
	 * @return String representation of queue elements
	 */
	public String displayByPid() {
		Process[] processList = generateProcessArray();
		String key = "pid";
		mergeSort(processList, 0, processList.length - 1, key);
		return printQueueArray(processList);
	}

	/**
	 * display queue elements order by process name
	 * 
	 * @return String representation of queue elements
	 */
	public String displayByName() {
		Process[] processList = generateProcessArray();
		String key = "name";
		mergeSort(processList, 0, processList.length - 1, key);
		return printQueueArray(processList);
	}

	/**
	 * display queue elements ordered by process owner
	 * 
	 * @return String representation of queue elements
	 */
	public String displayByOwner() {
		Process[] processList = generateProcessArray();
		String key = "owner";
		mergeSort(processList, 0, processList.length - 1, key);
		return printQueueArray(processList);
	}

	/**
	 * display queue elements ordered by cpu percentage used by process
	 * 
	 * @return String representation of queue elements
	 */
	public String displayByUsedCpuPercentage() {
		Process[] processList = generateProcessArray();
		String key = "usedCpuPercentage";
		mergeSort(processList, 0, processList.length - 1, key);
		return printQueueArray(processList);
	}

	/**
	 * display queue elements ordered by cpu time used by process
	 * 
	 * @return String representation of queue elements
	 */
	public String displayByUsedCpuTime() {
		Process[] processList = generateProcessArray();
		String key = "usedCpuTime";
		mergeSort(processList, 0, processList.length - 1, key);
		return printQueueArray(processList);
	}

	/**
	 * merge sort the process list based on the key parameter
	 * 
	 * @param list of processes
	 * @param leftIndex
	 * @param rightIndex
	 * @param key to identify the field for sorting
	 */
	private void mergeSort(Process[] list, int leftIndex, int rightIndex, String key) {
		if (leftIndex < rightIndex) {
			int mid = (leftIndex + rightIndex) / 2;
			mergeSort(list, leftIndex, mid, key);
			mergeSort(list, mid + 1, rightIndex, key);
			// merge the left and right subarrays by comparing elements on key parameter
			merge(list, leftIndex, mid, rightIndex, key);
		}
	}

	/**
	 * merge left and right subarray by comparing values
	 * 
	 * @param list of processes
	 * @param leftIndex
	 * @param mid
	 * @param rightIndex
	 * @param key
	 */
	private void merge(Process[] list, int leftIndex, int mid, int rightIndex, String key) {
		int leftSize = mid - leftIndex + 1;
		int rightSize = rightIndex - mid;

		Process[] leftList = new Process[leftSize];
		Process[] rightList = new Process[rightSize];

		// create left subarray
		for (int counter = 0; counter < leftSize; counter++)
			leftList[counter] = list[leftIndex + counter];
		// create right subarray
		for (int counter = 0; counter < rightSize; counter++)
			rightList[counter] = list[mid + 1 + counter];

		// initial index of left and right array
		int leftIterator = 0, rightIterator = 0;

		// initial index of output merged array
		int outputIterator = leftIndex;

		while (leftIterator < leftSize && rightIterator < rightSize) {
			if (compare(leftList[leftIterator], rightList[rightIterator], key) < 0) {
				list[outputIterator] = leftList[leftIterator];
				leftIterator++;
			} else {
				list[outputIterator] = rightList[rightIterator];
				rightIterator++;
			}
			outputIterator++;
		}
		// copy rest of the elements from left array to output array
		while (leftIterator < leftSize) {
			list[outputIterator] = leftList[leftIterator];
			leftIterator++;
			outputIterator++;
		}
		// copy rest of the elements from right array to output array
		while (rightIterator < rightSize) {
			list[outputIterator] = rightList[rightIterator];
			rightIterator++;
			outputIterator++;
		}
	}

	/**
	 * helper method to compare the values of subarray before merge
	 * 
	 * @param left process
	 * @param right process
	 * @param key to compare
	 * @return value of comparison
	 */
	private int compare(Process left, Process right, String key) {
		int value = 0;
		switch (key) {
		case "pid":
			if (left.getPid() == right.getPid()) {
				value = 0;
			} else if (left.getPid() < right.getPid()) {
				value = -1;
			} else {
				value = 1;
			}
			break;
		case "name":
			value = left.getName().compareTo(right.getName());
			break;
		case "owner":
			value = left.getOwner().compareTo(right.getOwner());
			break;
		case "usedCpuPercentage":
			if (left.getUsedCpuPercentage() == right.getUsedCpuPercentage()) {
				value = 0;
			} else if (left.getUsedCpuPercentage() < right.getUsedCpuPercentage()) {
				value = -1;
			} else {
				value = 1;
			}
			break;
		case "usedCpuTime":
			if (left.getUsedCpuTime() == right.getUsedCpuTime()) {
				value = 0;
			} else if (left.getUsedCpuTime() < right.getUsedCpuTime()) {
				value = -1;
			} else {
				value = 1;
			}
			break;
		}
		return value;
	}
}
