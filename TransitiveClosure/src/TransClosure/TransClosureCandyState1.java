package TransClosure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TransClosureCandyState1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc= null;
		
		File f= new File("Roadpairs.txt");
		HashMap<String,Integer> names_and_Indices= new HashMap<String,Integer>();
		
		try {
			sc= new Scanner(f);
			int numRoads=sc.nextInt();
			sc.nextLine();
			//Read in the cities and assign them indices
			
			int cityCount=0;
			String state="";
			int cityindex=0;
			while(cityCount<numRoads){
				state=sc.next();
				if(!names_and_Indices.containsKey(state)){
					names_and_Indices.put(state, cityindex);
					cityindex++;
				}
				cityCount++;
				if(sc.hasNextLine()){
					sc.nextLine();
				}
			}
			
			
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		CandyStates c= new CandyStates(names_and_Indices);
		c.initializeConnections();
		c.printDistances();
	}

}
