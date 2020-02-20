package datastructures;

import models.Process;

/**
 * @author shweta
 * 
 * This class represent the node holding process data,
 * links to previous and next node
 *
 */
public class ProcessNode {
	private Process process;
	private ProcessNode prevLink, nextLink;

	public ProcessNode() {

	}

	public ProcessNode(Process process, ProcessNode prevLink, ProcessNode nextLink) {
		this.process = process;
		this.prevLink = prevLink;
		this.nextLink = nextLink;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public ProcessNode getPrevLink() {
		return prevLink;
	}

	public void setPrevLink(ProcessNode prevLink) {
		this.prevLink = prevLink;
	}

	public ProcessNode getNextLink() {
		return nextLink;
	}

	public void setNextLink(ProcessNode nextLink) {
		this.nextLink = nextLink;
	}
}
