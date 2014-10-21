package main;

import gamecomponents.MyGame;

import java.util.Scanner;


@traceable public class FourInLine {

	private static final int	QUIT = 0,
								PLAY = 1,
								PLAYCOMPUTER = 2,
								CONFIG = 3;
	
	private static final int	YOUR_COMP = 1,
								MY_COMP = 2,
								RAND = 3;

	@traceable public static void main(String[] args) {
		Scanner terminalInput = new Scanner(System.in);
		
		int choice = 0;
		boolean badchoice = true;

		System.out.println("Welcome to Four in a Line!");

		while (true) {
			
			do {
				badchoice = false;
				printMenu();
				try {
					choice = Integer.parseInt(terminalInput.nextLine());
					badchoice = choice < QUIT || choice > CONFIG;
					if (badchoice) printBadChoise();;
				} catch (NumberFormatException e) {
					printBadChoise();
				}
			} while (badchoice); 


			if (choice == QUIT) {
				System.out.println("Bye bye!");
				terminalInput.close();
				return;
			}
			
			if (choice == CONFIG) {
				do {
					badchoice = false;
					printConfigMenu();
					try {
						choice = Integer.parseInt(terminalInput.nextLine());
						System.out.println();
						badchoice = choice<YOUR_COMP || choice>RAND;
						if (badchoice)
							printBadChoise();
						else 
							GameFactory.configureHuristics(choice);
					} catch (NumberFormatException e) {
						badchoice = true;
						printBadChoise();
					}
				} while (badchoice);
				continue;
			}
			
			//create the game and run it
			MyGame game = GameFactory.createGame(choice);
			TerminalGameRunner.runGameAndDisplay(game);
		}
	}

	
	private static void printBadChoise() {
		System.out.println("Input incorrect! Please try again.");
	}

	private static void printMenu() {
		System.out.println();
		System.out.println(QUIT + ". Exit");
		System.out.println(PLAY + ". Play against a friend");
		System.out.println(PLAYCOMPUTER + ". Play against the computer");
		System.out.println(CONFIG + ". Configure computer player huristics");
		System.out.print("Please choose an option: ");
	}
	
	private static void printConfigMenu() {
		System.out.println();
		System.out.println(YOUR_COMP + ". Your computer player");
		System.out.println(MY_COMP + ". My computer player");
		System.out.println(RAND + ". Random computer player");
		System.out.print("Please choose a huristics: ");
	}
	
}
