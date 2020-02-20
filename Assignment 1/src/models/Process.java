package models;

/**
 * @author shweta
 * 
 * This class represent the process information
 *
 */
public class Process {
	private String name; // process name
	private String owner; // process owner
	private int pid; // process id
	private int numberOfThreads; // number of threads of this process
	private double usedCpuPercentage; // cpu percentage used by this process
	private long usedCpuTime; // cpu time used in units of seconds

	public Process(String name, String owner, int pid, int numberOfThreads, double usedCpuPercentage,
			long usedCpuTime) {
		this.name = name;
		this.owner = owner;
		this.pid = pid;
		this.numberOfThreads = numberOfThreads;
		this.usedCpuPercentage = usedCpuPercentage;
		this.usedCpuTime = usedCpuTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public double getUsedCpuPercentage() {
		return usedCpuPercentage;
	}

	public void setUsedCpuPercentage(double usedCpuPercentage) {
		this.usedCpuPercentage = usedCpuPercentage;
	}

	public long getUsedCpuTime() {
		return usedCpuTime;
	}

	public void setUsedCpuTime(long usedCpuTime) {
		this.usedCpuTime = usedCpuTime;
	}

	/*
	 * Overriding toString method to represent process information in the form of
	 * Process : name=job3, owner=system, pid=1000, numberOfThreads=3,
	 * usedCpuPercentage=10.56, usedCpuTime=18967
	 */
	@Override
	public String toString() {
		return "Process : name=" + name + ", owner=" + owner + ", pid=" + pid + ", numberOfThreads=" + numberOfThreads
				+ ", usedCpuPercentage=" + usedCpuPercentage + ", usedCpuTime=" + usedCpuTime;
	}
}
