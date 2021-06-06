package org.foo.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Travail {
	public static void main(String[] args) {
		Travail work = new Travail("/Documents/School/PolyMtl/Java/TP2_INF4410_1761581/Fichiers_fournis_TP2_INF4410/donnees-2317.txt");
		work.show();

		int result = 0;
		for (int i = 0; i < work.Taches.size(); i++) {
			Tache task = work.Taches.get(i);
			result = (result + task.execute() % 5000 ) % 5000;
			System.out.println(result);
		}
		System.out.println("Result is : "+result);
		System.out.println("Expected result is : "+work.expectedResult);
	}

	public ArrayList<Operation> Operations;
	public ArrayList<Tache> Taches;
	public int expectedResult;
	private int tacheOperationsLoad;

	public Travail(String path) {
		this.Operations = getOperationsFromFile(path);
		this.expectedResult = getExpectedResult(path);
		this.tacheOperationsLoad = 10;

		Taches = new ArrayList<Tache>();
		cutWorkInTasks(); 
	}

	private void cutWorkInTasks() {
		ArrayList<Operation> curOperationsFlow = new ArrayList<Operation>();
		for (Operation operation : Operations) {
			curOperationsFlow.add(operation);
			if (curOperationsFlow.size() == this.tacheOperationsLoad) { 
				this.Taches.add(new Tache(curOperationsFlow));
				curOperationsFlow = new ArrayList<Operation>();
			}
		}
		if (!curOperationsFlow.isEmpty()) {
			this.Taches.add(new Tache(curOperationsFlow));
			curOperationsFlow = new ArrayList<Operation>();
		}
	}

	private static int getExpectedResult(String path){
		int retour = 0;
		File file = new File(path);
		String fileName = file.getName();
		if (fileName.matches("^donnees-[0-9]+[.]txt$")) {
			retour = Integer.valueOf(fileName.replaceAll("(donnees-|[.]txt)", ""));
		}
		return retour;
	}
	private static ArrayList<Operation> getOperationsFromFile(String path) {
		ArrayList<Operation> Ops = new ArrayList<Operation>();

		
		BufferedReader br = null;
        String sCurrentLine = null;
        try {
            br = new BufferedReader(
            		new FileReader(path));
            while ((sCurrentLine = br.readLine()) != null) {
            	if (sCurrentLine.matches("^(vi|jio|air) [0-9]+$")) {
            		String[] explode = sCurrentLine.split(" ");
            		String name = explode[0];
            		int value = Integer.valueOf(explode[1]);
            		try {
            			Ops.add(new Operation(name, value));
					} catch (OperationUnknownException e) { e.printStackTrace(); }
				}
            }
        } catch (IOException e) { e.printStackTrace(); }
        finally{ 
        	try {
        		if (br != null) br.close();
        	} catch (IOException e){ e.printStackTrace(); }
    	}
        return Ops;
	}
	public void show() {
		System.out.println("size is "+this.Operations.size()+" counted");
		System.out.println("Result "+this.expectedResult);
	}
	public void showTaches() {
		System.out.println("size is "+Operations.size()+" counted "+Taches.size()+" taches : ");
		int i = 1;
		for (Tache tache : Taches) {
			System.out.print("Tache #"+i+" : ");
			tache.show();
			i++;
		}
	}
}