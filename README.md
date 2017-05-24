# TravelingSaleManProblem
Travling Sales man problem
A requirment when creating this program was that it had to be run from command line. 
Example to run: MyPlanner.java File1.txt Portland
Or you can set the args in run configurations as "File1.txt Portland
The city after the text file is the starting city, this is where your journey starts and ends.
Inside the text file the 
Line1: List all the cities that will be traveled to
Row2: List constraint cities, So any cities listed here will have to be traveled to in order.
If you list NewHaven and Seattle you can not travel to Seattle until you travel to NewHaven first. 
You can travel to any other cities though as long as you do not go to Seattle until you have been to NewHaven.
The rest of the text file contains an adjacency matrix which contains the distances from one city to another.
If the distance = Inf that means that there does not exist a path. 
The output needs work still.
