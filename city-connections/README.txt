###############################
# Design/Implementation Notes #
###############################

The solution consists of 2 classes: Graph and Connected.
Graph class serves as a data structure to hold the information about paths between cities.
Connected class is the main program that extracts the connection info from input file
and provides answers as to whether or not 2 given cities are connected.

Main program algorithm:

1. Build a graph, reading from input file.
   Note: An invalid line in the input file is ignored.

2. Interactively get 2 city names, check if there is a path between the 2 cities, then prints out yes/no answer.
   Note: The interactive design deviates from the problem description; refer to the instructions on how to build/run.

Algorithm to check connection between 2 cities:

1. Check edge cases:
   a. If either of the 2 cities is not a node in the graph, return false.
   b. If the 2 cities are identical, i.e., represents the same city, return true.
   c. If the 2 cities are directly connected, i.e., there is an edge between the 2 cities, return true.

2. Use BFS algorithm to check the connection between the 2 cities:
   Initially, the queue and visited set contain one of the 2 cities, regarded as the source city.
   While the queue is not empty, poll a city from the queue, and check each of the adjacent cities directly connected.
     If an adjacent city is the destination city, return true.
     Else, if the adjacent city has not been visited yet, mark it as visited and add it to the queue.
   Once the queue is emptied out, it means there is no connection between the 2 input cities, so return false.


####################
# How To Build/Run #
####################

Prerequisite: Installation of Java 8.

In the src directory, build the classes as follows:

javac city/connections/Graph.java
javac city/connections/Connected.java

To run the program, issue the following command:

java city.connections.Connected <filename>

e.g. java city.connections.Connected ../cities.txt

Once the program starts, provide the input city names as follows each time:

<cityname1>, <cityname2>

e.g. New York, Boston

To exist the program, type "done":

done
