import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyPlanner {
	
	static int cityStartAsNum = 0;
	static int[] constraintAsNum = {0,0};
	static boolean c1 = false;
	static boolean c2 = false;
	static int totalDist = 99999;
	static int addDist = 0;
	static int[] finalPath;
	static int[] saveDist;
	public static void main (String[] args) throws FileNotFoundException
	{	
	String fileName = args[0];
	String cityStart = args[1];
	String[] inArr = null;
	String line = null;
	String[] cityList;
	String[] constraint;
	String[] temp;
	ArrayList<String> input = new ArrayList<String>();
	
	try   													// try to read input file
	{
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
				
		while((line = bufferedReader.readLine()) != null) 
			{
				input.add(line); 							// save input file line by line in input array 
			}		
		bufferedReader.close();
		
	}
	catch(FileNotFoundException ex)
	{
		System.out.println(" Unable to open file " + fileName);
	}
	catch(IOException ex)
	{
		System.out.println("Error reading file" + fileName);
	}
	
	inArr = input.toArray(new String [input.size()]);

	cityList = inArr[0].split("\\s+"); 							// save list of cities in array cityList
	constraint = inArr[1].split("\\s+"); 						// save contraint in an array	
	
	int cityAsNum[] = new int[input.size()-2]; 					// create integer array as size of city list 

	for(int m = 0; m<input.size()-2; m++) 						// save city list in an array of numbers
	{
		if(cityList[m].equals(cityStart)) 						// check for start city
			{
			cityStartAsNum = m; 								// save start city as number for later	
			}
		
		for(int i =0; i<constraint.length; i++) 				// loop to check for each constraint 
		{
			if(cityList[m].equals(constraint[i])) 				// check for constraint in city list
			{
				constraintAsNum[i] = m;							// remember the constraint as a number separately
			}
		}
		
		cityAsNum[m] = m;										// save city as number in array
		
	} 														  	// end cityAsNum loop
	int cityAsNum2[] = cityAsNum.clone();
	int matrix[][] = new int[input.size()-2][input.size()-2]; 	// create matrix as number of lines in input file -2

 	for(int row = 2; row < input.size(); row++)					// fill matrix loop
 	{
 		temp = inArr[row].split("\\s+");						// split the temp array at white space
 		for( int col = 2; col < input.size(); col++)
 		{	
 			int temp2 = tryParse(temp[col-2]);
 			matrix[row-2][col-2] = temp2;
 //			System.out.println(matrix[row-2][col-2]+ " i,k "+ (row-2) + " " + (col-2)); //prints matrix array filled w/ data
 			
 		}
 		temp = null;
 	}  	
 	
	permute(cityAsNum, input.size()-2, matrix); 				// call create permutations and check for constraints 
	
 	if(totalDist < 99999) 										// check that route is not inf value
 	{
 		for(int e = 0; e < input.size()-2; e++)					//
 		{
 			for(int x = 0; x< input.size()-2; x++)
 			{
 				if(finalPath[e] == cityAsNum2[x])				// compare path number to cityAsNum2 if true print the city in cityList from CityAsNum
					{
						System.out.print(cityList[x]+ " " + saveDist[x] + "_to_" + " ");
					}
 			}
 		}
 		System.out.println(("\n Total distance = " + totalDist));// print total distance
	}
 	else
 	{
 		System.out.println("No possible itineray");				// 
 	}
 	
	}															// end main
	
	
	public static Integer tryParse(String toParse) 				// attempts to parse integer value, if unable to parse assumes inf set to 99999
	{
		   try 
		   {
			   return Integer.parseInt(toParse);
		   	} 
		   catch (NumberFormatException ex)
		   	 {
		     return 99999;										// inf value
		     }
	}
	
	private static void swap(int[] v, int h, int k) 			// swap method, swaps two variables with each other and returns
    {
        int t = v[h];
        v[h] = v[k];
        v[k] = t;
    }
	
	 public static void permute(int[] b, int n, int a[][]) 		// permute alg, also check for constraints, shortest path
	    {
	        if (n == 1)											// new permutation 
	        {
	        	//System.out.println(Arrays.toString(v)); 		// all possible permutations 
	            
	            if(b[0] == cityStartAsNum)
	            {
	            //	System.out.println(Arrays.toString(v)); 	// all possible paths starting from correct city
	            	for(int t=0; t < b.length; t++)
	            	{
	            		if(a[b[b.length-1]][cityStartAsNum] != 99999) // check that last city has a path back to first
	            		{	
	            			if(constraintAsNum[0] == b[t])
	            			{
	            				c1 = true; 
	            			}
	            			if(constraintAsNum[1] == b[t])
	            			{
	            				c2 = true;
	            			}
	            			if(c1 == true && c2 == false)
	            			{	
	            			// System.out.println(Arrays.toString(v)); // all possible paths that fit constraint 
	            				for(int l = 0; l < b.length-1; l++)	// add up distance 
	            				{
	            					addDist += a[b[l]][b[l+1]];
	            					
	            				}
	            			if (addDist <= totalDist)			// check if distance is less then previous distance
	            			{
	            				saveDist = b.clone(); 			// clone v to set saveDist to proper array length
	            				totalDist = addDist;			// addDist = new totatDist
	               				finalPath =	b.clone();			// clone v to set finalPath to proper array length
	               				for (int c = 0; c<b.length-1; c++)
	               				{
	               					saveDist[c] = a[b[c]][b[c+1]]; // save dist between each city in order
	               				}
	            			}
	            			addDist = 0;
	            			break; 								// break out of to not produce duplicate values
	            			} 
	            		}
	            	}
	            	c1 = false; 								//set constraints back to false
	            	c2 = false;
	            }
	            
	        }
	        else
	        {
	            for (int i = 0; i < n; i++)
	            {
	                permute(b, n - 1, a);
	                if (n % 2 == 1)
	                {
	                    swap(b, 0, n - 1); 							
	                }
	                else
	                {
	                    swap(b, i, n - 1);
	                }
	            }
	        }
	     }
	  
	 
}