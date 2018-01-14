Package/Directory Structure:

src
---- set.solver
     ---- main
     	  ---- Card.java
     	  ---- CardSet.java
     	  ---- SetGame.java
     	  ---- SetSolver.java
     ---- test
     	  ---- unit
     	  	   ---- CardUnitTest.java
     	  	   ---- CardSetUnitTest.java
     	  	   ---- SetGameUnitTest.java
     	  ---- functional
     	  	   ---- SetGameFunctionalTest.java
     	  	   ---- SetSolverFunctionalTest.java
     	  ---- manual
     	  	   ---- SetSolverManualTest.java


========================================================================================================================
Overall Design:

Object-oriented design using a modular approach.

Card class models an individual card object.
CardSet class models a set of 3 cards as a unit of solution.
SetGame class models a set game with the given number of dimensions per card and number of values per dimension.
SetSolver class implements the method to solve the game, i.e., method to return all possible sets given a deal of cards.

Note:
The design/implementation focused on satisfying the problem requirements,
namely, to find/return all possible valid sets of cards, given a deal of cards selected from the card deck.
No attempt has been made to simulate a set game with the turn-taking between players.


========================================================================================================================
Command-Line Compile Instructions:

Go to the "src" directory.

Compile the classes in "main" directory as follows:

javac set/solver/main/Card.java
javac set/solver/main/CardSet.java
javac set/solver/main/SetGame.java
javac set/solver/main/SetSolver.java

Compile the classes in "test/unit" directory as follows:

javac -sourcepath "set/solver/main" -cp <lib directory where junit.jar file is> set/solver/test/unit/CardUnitTest.java
javac -sourcepath "set/solver/main" -cp <lib directory where junit.jar file is> set/solver/test/unit/CardSetUnitTest.java
javac -sourcepath "set/solver/main" -cp <lib directory where junit.jar file is> set/solver/test/unit/SetGameUnitTest.java

Compile the classes in "test/functional" directory as follows:

javac set/solver/test/functional/SetGameFunctionalTest.java
javac set/solver/test/functional/SetSolverFunctionalTest.java

Compile the class in "test/manual" directory as follows:

javac set/solver/test/manual/SetSolverManualTest.java


========================================================================================================================
Command-Line Run Instructions:

Run the unit tests in "test/unit" directory as follows:

java -cp <lib directory where junit.jar file is> org.junit.runner.JUnitCore set.solver.test.unit.CardUnitTest
java -cp <lib directory where junit.jar file is> org.junit.runner.JUnitCore set.solver.test.unit.CardSetUnitTest
java -cp <lib directory where junit.jar file is> org.junit.runner.JUnitCore set.solver.test.unit.SetGameUnitTest

The above assumes JUnit version 4.X.
For JUnit version 3.X, replace "org.junit.runner.JUnitCore" with "junit.textui.TestRunner".

Run the functional tests in "test/functional" directory as follows:

java set.solver.test.functional.SetGameFunctionalTest
java set.solver.test.functional.SetSolverFunctionalTest

Run the manual test in "test/manual" directory as follows:

java set.solver.test.manual.SetSolverManualTest <numDimensions> <numValues> <numTrials> <maxDealSize>


========================================================================================================================
IntelliJ Load/Run Instructions:

The program can be run via IntelliJ by placing the "set.solver" package under a new/existing project "src" directory.
