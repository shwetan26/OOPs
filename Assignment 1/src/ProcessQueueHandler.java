import models.Process;
import datastructures.*;
import exceptions.InvalidCapacityException;
/**
 * @author shweta
 * 
 * This is the main program which will use queue functionalities
 *
 */
public class ProcessQueueHandler {
	public static void main(String[] args) {
		Process p1 = new Process("job1", "system", 1011, 1, 20.34, 3000);
		ProcessQueue q = new ProcessQueue();
		try {
			q.createQueue(1);
		} catch (InvalidCapacityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			q.insertQueue(p1);
		} catch (InvalidCapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Process p2 = new Process("job2", "adam", 1023, 2, 30.49, 2456);
		Process p3 = new Process("job3", "system", 1000, 3, 10.56, 18967);
		try {
			q.insertQueue(p2);
		} catch (InvalidCapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			q.insertQueue(p3);
		} catch (InvalidCapacityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//q.display();
		String output1 = q.printQueue();
		System.out.println("Queue is: "+output1);
		//q.removeFromQueue();
		//q.display();
		q.displayByPid();
		String output2 = q.displayByOwner();
		System.out.println("Display by owner: "+output2);
	}
}
