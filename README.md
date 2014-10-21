logins: agvania, arnoncohen
id: 300257235, 021931738

OOAD PROJECT

-------------
HOW TO CREATE
-------------

create a project called OOD_PROJECT


------
DESIGN
------


Here's an overview about all interfaces, classes and components of the program:

Disc:
This is the basic element of the game. The Board interface uses this interface to represent the discs inside the board.
A disc has to be distinguished from another, and to have the ability to clone itself.

MyDisc:
Implements the Disc interface.
It has only one attribute that distinguishes one from another: the player number. The player number is the number of the player in the game which the disc belongs to.
We could have given each disc a Player reference, but that would be a redundant dependency. Moreover, in order to implement the flyweight pattern - this method of characterizing discs will prove more efficient.
The Disc class implements the flyweight pattern - for every player number there exists only ONE Disc instance, since the only thing discs differ from each other is their player number. Having one only one instance is a significant memory optimization, since given a numbering convention - only two instances of MyDisc have to be created, even for multiple games.

Board:
Can be inserted and extracted discs from it. Gives all info required to know its inner state. See documentation.
Every game has one instance of this class.

ObservableBoard:
Implements Board and extends the Observable class, and can be observed to display it.

MyBoard:
Has its own implementation of notifyObservers which uses Observable's implementation to utilize a more convenient and unified notification.

BoardAnalyzer:
This class can give information about the significance of the discs in the board. Right now it has only one method - isWinningDisc(). This method is used by computer players to gain information about there moves, and by the game itself to find out if a certain move won the game.
But it can be extended to have more analyzing methods.
This job of analyzing the board has been separated from the Board class, since it exceeds the basic informatting on the board's state.

Player:
A representation of a game player. every game has two instances of this class.
Every Player implementation must implement the method chooseColumn(), and thus it is implementing the strategy pattern.
This method gets a board in a certain state, and two discs. These are given in order to perform manipulations on the board in order to decide what column to choose.
In order to give the information about the player's characterization, there's the method isComp(). This method returns true/false, and is to be implemented by subclasses of Player. Thus one can know if the player is human or computer without the need of the 'instanceof' keyword.

MyPlayer:
Implements Player.
This is an abstract class, since there is always a certain characterization of the player(human/computer).
In this implementation, a player can be distinguished from another by assigning them different numbers.

HumanPlayer:
Has input and output streams in order to communicate with the real human being represented by it.
Note that in our implementation, the board and discs given are not used, since the board is being printed to the screen, but one could have a different implementation, say giving the human player actually manipulate the board in certain ways in order to decide his choice.

ComputerPlayer:
This abstract class was made for one reason - code reuse. Every subclass of it can have the benefits of its static methods - tryToWin() and preventRivalWin() [which are using BoardAnalyzer], and also have a free implementation of isComp().

ComputerPlayer has three subclasses implemented by us as examples:

1. YourComputerPlayer - implements chooseColumn() the way the school solution did the choosing.
2. MyComputerPlayer - same as the school solution, just instead of choosing the most left column it chooses the most right column.
3. RandomComputerPlayer - same as school solution, but chooses a random column instead of a deterministic one.

Note that since the only difference between all subclasses of Player is the implementation of the method chooseColumn(), and our implementations of ComputerPlayer are not preserving any state, they can easily be instanciated as flyweights, like we did in the ComputerPlayerFactory class.
This is not a built-in property of the ComputerPlayer class since one can implement a different subclass to ComputerPlayer which might be preserving a state, thus not applicable for the flyweight pattern.

Game:
This is where it all happens. It gets two players and a board, and makes the interaction between them.
In every turn, the game should ask the current player to chooseColumn(), and inserts a disc to the board accordingly.
Game has methods to give all information about the state of the game, and also can return its players and board.

MyGame:
Our implementation to Game. Uses our implementation to Disc.

BoardDisplay:
All classes implementing this interface are responsible for visually representing the board.
Its method displayBoard() can be invoked in order to visually display the board according to the specific implementation.
This interface extends the Observer interface, since every BoardDisplay is an observer of the Board class.
If we would like to allow the user to configure the display, we could implement a BoardDisplay that allows this.

BoardTerminalDisplay:
This is an example implementation of the BoardDisplay interface.
It looks just like the school solution's display.

--------------------------------------------------------------

Up until now was the basic design of the game(including some examples, like ComputerPlayer's subclasses and BoardTerminalDisplay).
This load of classes can be used to build a flexible and extendable complete program of the game.
An example of that is given by the following classes:

FourInLine:
The main class, running the UI and creating instances of the game, using GameFactory.
An example of configuring computer heuristics is also implemented in this class.

GameFactory:
Utilizes the creation of game for the FourInLine class. Given an option, it creates the instances of Player, Board, and Game classes necessary to run the game.

ComputerPlayerFactory:
Implements the flyweight pattern for the given implementations of the ComputerPlayer.

TerminalGameRunner:
Runs a specific game and displays it to the terminal, the way it was done in the school solution.


Requirements answers:
1. Implement the BoardDisplay interface in the way suitable for the displaying method wanted, and add it as an observer to the board. When the board will change, all observers will be notified simultaneously.
-Observer pattern.

2. More heuristics can implemented by extending the ComputerPlayer class, implementing the chooseColumn method the way we want.
-Strategy pattern.

3. Implement BoardDisplay the way we want, and give the option, through set methods, to configure the display.
If we only want to ADD features to the display(like a title), we can make a decorator to our implementation of the board that adds those features.

4. MyDisc is a flyweight, thus optimizing memory. ComputerPlayer, at least in some implementations, can be a flyweight, thus optimizing memory.
-Flyweight pattern.


----------
UNIT TESTS
----------

Testing all features of our implementations of the interfaces specified.


--------------
DYNAMIC PROXY
--------------

Logging methods of Player and Board.


-------
ASPECTJ
-------

Basically what we do is to trace a call to the System.out.print* methods, and to ensure that it was called by one of our methods, we used annotations - we gave all classes that are printing something the "traceable" annotation, and we checked that it is present in the join point.