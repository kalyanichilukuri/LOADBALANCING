package org.foo.app;

import java.util.ArrayList;

public class Tache {
	private ArrayList<Operation> Operations;
	private String assignedTo = null; 
	private String state;
	private int resultat = 0;

	public Tache() {
		this.Operations = new ArrayList<Operation>();
		this.setToToDoState();
	}
	public Tache(ArrayList<Operation> Operations) {
		this.Operations = Operations;
		this.setToToDoState();
	}
	public void add(Operation Op) {
		this.Operations.add(Op);
	}
	public int execute() {
		this.setToInProgressState();
		this.resultat = 0;
		for (Operation operation : this.Operations) {
			try {
				this.resultat = (this.resultat + operation.compute() % 5000) % 5000;
			} catch (OperationUnknownException e) { e.printStackTrace(); }
		}
		this.setToFinishedState();
		return this.resultat;
	}

	
	public void show() {
		System.out.print(this.Operations.size()+" Op�rations � effectuer : ");
		for (Operation operation : this.Operations) {
			System.out.print(operation.name+" "+operation.value+"; ");
		}
		System.out.println();
	}
	// State Getters & Setters
	public String getState(){ return this.state; }
	public void setToToDoState(){ this.state = "ToDo"; }
	public void setToFinishedState(){ this.state = "finished"; } 
	public void setToInProgressState(){ this.state = "inProgress"; }
	// AssignedTo Getters & Setters
	public String getAssignedTo() { return this.assignedTo; }
	public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
	// resultant Getters & Setters
	public int getResultat() { return this.resultat; }
	public void setResultat(int resultat) { this.resultat = resultat; }
}