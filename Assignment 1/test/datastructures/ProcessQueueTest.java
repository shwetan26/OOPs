package datastructures;

import models.Process;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidCapacityException;

/**
 * @author shweta
 * 
 * This is test class which contains unit tests for testing methods of
 * ProcessQueue class
 *
 */
public class ProcessQueueTest {
	private ProcessQueue processQueue;
	private Process firstProcess, secondProcess, thirdProcess;

	@Before
	public void setUp() throws InvalidCapacityException {
		processQueue = new ProcessQueue();
		firstProcess = new Process("job1", // name
				"system", // owner
				1011, // PID
				1, // number of threads
				20.34, // used cpu percentage
				3000 // used cpu time in ms
		);
		secondProcess = new Process("job2", // name
				"adam", // owner
				1023, // PID
				2, // number of threads
				30.49, // used cpu percentage
				2456 // used cpu time in ms
		);
		thirdProcess = new Process("job3", // name
				"system", // owner
				1000, // PID
				3, // number of threads
				10.56, // used cpu percenatge
				18967 // used cpu time in ms
		);
		processQueue.insertQueue(firstProcess);
		processQueue.insertQueue(secondProcess);
		processQueue.insertQueue(thirdProcess);
	}

	/**
	 * Test to check if queue is empty
	 */
	@Test
	public void isEmptyTest() {
		// remove all elements to empty the queue
		int processCount = processQueue.getQueueSize();
		for (int index = 0; index < processCount; index++) {
			processQueue.removeFromQueue();
		}
		assertTrue(processQueue.isEmpty());
	}

	/**
	 * Test to get queue size
	 */
	@Test
	public void getQueueSizeTest() {
		int expectedSize = 3;
		assertEquals(expectedSize, processQueue.getQueueSize());
	}

	/**
	 * Test to check if queue is full with elements
	 * 
	 * @throws InvalidCapacityException
	 */
	@Test
	public void isFullTest() throws InvalidCapacityException {
		// add 1 element to make queue full
		Process fourthProcess = new Process("job4", "adam", 2345, 3, 10.56, 34456);
		processQueue.insertQueue(fourthProcess);
		assertTrue(processQueue.isFull());
	}

	/**
	 * Test to check if queue capacity is correct, ensuring queue has been created
	 */
	@Test
	public void createQueueTest() {
		int expectedCapacity = 4;
		assertEquals(expectedCapacity, processQueue.getQueueCapacity());
	}

	/**
	 * Test to insert element in queue and check queue size to ensure element is
	 * inserted successfully
	 */
	@Test
	public void insertQueueTest() {
		int expectedSize = 3;
		assertEquals(expectedSize, processQueue.getQueueSize());
	}

	/**
	 * Test to remove element from empty queue
	 */
	@Test
	public void removeFromEmptyQueueTest() {
		// first empty the queue
		int processCount = processQueue.getQueueSize();
		for (int index = 0; index < processCount; index++) {
			processQueue.removeFromQueue();
		}
		// queue is empty now, remove from empty queue
		assertNull(processQueue.removeFromQueue());
	}

	/**
	 * Test to print queue elements in FIFO order
	 */
	@Test
	public void printQueueTest() {
		String expectedString = "Process : name=job1, owner=system, pid=1011, numberOfThreads=1, usedCpuPercentage=20.34, usedCpuTime=3000\n"
				+ "Process : name=job2, owner=adam, pid=1023, numberOfThreads=2, usedCpuPercentage=30.49, usedCpuTime=2456\n"
				+ "Process : name=job3, owner=system, pid=1000, numberOfThreads=3, usedCpuPercentage=10.56, usedCpuTime=18967\n";
		assertEquals(expectedString, processQueue.printQueue());
	}

	/**
	 * Test to remove element from queue and check queue size after removal
	 */
	@Test
	public void removeFromQueueTest() {
		processQueue.removeFromQueue();
		int expectedSize = 2;
		assertEquals(expectedSize, processQueue.getQueueSize());
	}

	/**
	 * Test to display queue elements ordered by PID
	 */
	@Test
	public void displayByPidTest() {
		String expectedString = "Process : name=job3, owner=system, pid=1000, numberOfThreads=3, usedCpuPercentage=10.56, usedCpuTime=18967\n"
				+ "Process : name=job1, owner=system, pid=1011, numberOfThreads=1, usedCpuPercentage=20.34, usedCpuTime=3000\n"
				+ "Process : name=job2, owner=adam, pid=1023, numberOfThreads=2, usedCpuPercentage=30.49, usedCpuTime=2456\n";
		assertEquals(expectedString, processQueue.displayByPid());
	}

	/**
	 * Test to display queue elements ordered by process name
	 */
	@Test
	public void displayByNameTest() {
		String expectedString = "Process : name=job1, owner=system, pid=1011, numberOfThreads=1, usedCpuPercentage=20.34, usedCpuTime=3000\n"
				+ "Process : name=job2, owner=adam, pid=1023, numberOfThreads=2, usedCpuPercentage=30.49, usedCpuTime=2456\n"
				+ "Process : name=job3, owner=system, pid=1000, numberOfThreads=3, usedCpuPercentage=10.56, usedCpuTime=18967\n";
		assertEquals(expectedString, processQueue.displayByName());
	}

	/**
	 * Test to display elements ordered by process owner
	 */
	@Test
	public void displayByOwnerTest() {
		String expectedString = "Process : name=job2, owner=adam, pid=1023, numberOfThreads=2, usedCpuPercentage=30.49, usedCpuTime=2456\n"
				+ "Process : name=job3, owner=system, pid=1000, numberOfThreads=3, usedCpuPercentage=10.56, usedCpuTime=18967\n"
				+ "Process : name=job1, owner=system, pid=1011, numberOfThreads=1, usedCpuPercentage=20.34, usedCpuTime=3000\n";
		assertEquals(expectedString, processQueue.displayByOwner());
	}

	/**
	 * Test to display elements ordered by cpu percentage
	 */
	@Test
	public void displayByUsedCpuPercentageTest() {
		String expectedString = "Process : name=job3, owner=system, pid=1000, numberOfThreads=3, usedCpuPercentage=10.56, usedCpuTime=18967\n"
				+ "Process : name=job1, owner=system, pid=1011, numberOfThreads=1, usedCpuPercentage=20.34, usedCpuTime=3000\n"
				+ "Process : name=job2, owner=adam, pid=1023, numberOfThreads=2, usedCpuPercentage=30.49, usedCpuTime=2456\n";
		assertEquals(expectedString, processQueue.displayByUsedCpuPercentage());
	}

	/**
	 * Test to display elements ordered by used cpu time
	 */
	@Test
	public void displayByUsedCpuTimeTest() {
		String expectedString = "Process : name=job2, owner=adam, pid=1023, numberOfThreads=2, usedCpuPercentage=30.49, usedCpuTime=2456\n"
				+ "Process : name=job1, owner=system, pid=1011, numberOfThreads=1, usedCpuPercentage=20.34, usedCpuTime=3000\n"
				+ "Process : name=job3, owner=system, pid=1000, numberOfThreads=3, usedCpuPercentage=10.56, usedCpuTime=18967\n";
		assertEquals(expectedString, processQueue.displayByUsedCpuTime());
	}
}
