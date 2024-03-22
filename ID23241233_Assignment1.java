package CS6371;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
//id: 23241233; Name: Kirtika Thakur

public class game {

    public static void main(String[] args) {
        boolean playAgain;
        do {
            String userName;
            String message = "finds themselves lost in a dense forest with no clear path forward. Choices:" + "\n" +
                             "1-Climb a tree to get a better view" + "\n" +
                             "2-Build a shelter and wait for rescue" + "\n" +
                             "3-follow a stream to see if it leads to civilisation";

            do {
                userName = JOptionPane.showInputDialog(null, "Welcome to the adventure game. Please enter a valid username");
                if (isValidUsername(userName)) {
                    // Display the message to the user and get their choice
                    int choice = getUserInput(message, 1, 3);
                    // Display outcome
                    JOptionPane.showMessageDialog(null, userName + " continues their journey.");
                    break; // Exit the loop if the username is valid
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username. Please enter a username that:\n" +
                            "Contains only alphanumeric and/or underscore characters, with a minimum length of 2 and a maximum length of 15.");
                }
            } while (true); // Keep repeating until a valid username is entered

            // Scenario 2
            String scenario2 = "encounters a mysterious stranger who offers them assistance. " +
                               "You must decide whether to trust the stranger.\n" +
                               "Choose an option:\n" +
                               "1 - Accept the stranger's help\n" +
                               "2 - Politely decline and continue on your own\n" +
                               "3 - Confront the stranger and demand answers";

            // display scenario 2 to the user
            JOptionPane.showMessageDialog(null, "While wandering through the forest, " + userName + " " + scenario2);

            // get user choice for scenario 2
            int choice2 = getUserInput(scenario2, 1, 3);

            // handle user choice for scenario 2
            switch (choice2) {
                case 1:
                    handleAcceptHelp(userName);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, userName + " continues their journey.");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "The stranger offers " + userName + " to play a game.");
                    int numberToGuess = (int) (Math.random() * 10) + 1; // generate a random number between 1 and 10
                    int attempts = 3;
                    boolean guessedCorrectly = false;

                    JOptionPane.showMessageDialog(null, "The game is to guess the number hidden in this mysterious box. " +
                            "It should be between 1 and 10 (inclusive). You have THREE tries.");

                    while (attempts > 0 && !guessedCorrectly) {
                        int guess = getUserGuess();
                        if (guess == numberToGuess) {
                            JOptionPane.showMessageDialog(null, userName + " is saved!");
                            guessedCorrectly = true;
                            System.exit(0); //if user guessed the number correctly, skip scenario3 and exit the game
                           
                        } else {
                        	//using ternary comparison operators to compare the users guess and actual number and then assign the higher/lower to variable hint.
                            String hint = guess < numberToGuess ? "HIGHER" : "LOWER";
                            //using (--attempts) to decrement the no. of attempts left.
                            //if the attempts==1, then the message box will display final try instead of tries.
                            JOptionPane.showMessageDialog(null, "INCORRECT. You should aim " + hint + " in your next try, Ryo. You have " + (--attempts) + " " + (attempts == 1 ? "FINAL" : "tries") + " left.");
                        } 
                    }  

                     if (!guessedCorrectly) {
                        JOptionPane.showMessageDialog(null, userName + " is killed!");
                        
                        System.exit(0); //if user has not guessed the number correctly, player dies and the game ends
                    } 
                   
            }

            // Scenario 3-which is come up if option 2 is selected in scenario 2
            String scenario3 = "comes across a wide river blocking their path. " + userName + " must figure out how to cross it safely.\n" +
                               "Choices:\n" +
                               "1 - Attempt to swim across the river\n" +
                               "2 - Look for a shallow area to wade through\n" +
                               "3 - Build a makeshift raft to cross the river";

            JOptionPane.showMessageDialog(null, userName + " " + scenario3);

            // Get user input for scenario 3
            int choice3 = getUserInput(scenario3, 1, 3);

            // Handle user choice for scenario 3
            switch (choice3) {
                case 1:
                    JOptionPane.showMessageDialog(null, userName + " drowns! [Sad End]");
                    break;
                case 2:
                case 3:
                    JOptionPane.showMessageDialog(null, userName + " is saved! [Happy End]");
                    break;
            }

            playAgain = askToPlayAgain(); //calling the AskToPlayAgain method
        } while (playAgain);
        JOptionPane.showMessageDialog(null, "Goodbye!");
    }

    public static int getUserGuess() {
        int guess;
        do {
            String input = JOptionPane.showInputDialog(null, "Enter your guess (1-10):");
            try {
                guess = Integer.parseInt(input);
                if (guess < 1 || guess > 10) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 10.");
                } else {
                    return guess;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                
            }
       
        } while (true);
        
         
   }

    // creating a getUsserInput method
    //takes three parameters the message, minimum and maximum
    public static int getUserInput(String message, int min, int max) {
        int input;
        do {
            String inputString = JOptionPane.showInputDialog(null, message);
            try {
                input = Integer.parseInt(inputString); //parsing string into integer
                if (input < min || input > max) { //if the input is out of range show the below message box
                    JOptionPane.showMessageDialog(null, "Please enter a number between " + min + " and " + max + ".");
                } else {
                    return input; // return input, if the input is a valid number
                }
                //try-catch block to handle exceptions and display the below message
            } catch (NumberFormatException e) { 
                JOptionPane.showMessageDialog(null, "Please enter a valid number."); 
                
            }
        
        } while (true);
        
        
    }
    //outside the main method
//using regex to check if the userName is in valid format
    public static boolean isValidUsername(String username) {
        // Regular expression to match alphanumeric characters and underscore
        String regex = "^[a-zA-Z0-9_]{2,15}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Check if username matches the pattern
        return pattern.matcher(username).matches();
    }

    // if option 1 is chosen in scenario2, 70% chance saved and 30% chance he is dead
    public static void handleAcceptHelp(String userName) {
        // Randomly determine outcome
        double random = Math.random();
        String outcome;
        if (random < 0.7) {
            outcome = userName + " is saved!";
        } else {
            outcome = userName + " is killed!";
        } 
        JOptionPane.showMessageDialog(null, outcome);
       System.exit(0); //skip going to scenario 3 and end the game
    }  
//creating a method to play the game again
    public static boolean askToPlayAgain() {
        int response;
       // do
        {
        	//creating a yes, no option box instead of ok, cancel
        	//storing the response in the response variable
            response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        
            //creating a while loop, so user must select one option
     while (response != JOptionPane.YES_OPTION && response != JOptionPane.NO_OPTION);
     return response == JOptionPane.YES_OPTION;
    }
}
}

//Referencing 
//for ternary operator: https://ioflood.com/blog/java-ternary-operator/#:~:text=The%20ternary%20operator%20is%20a,code%20more%20concise%20and%20readable.
