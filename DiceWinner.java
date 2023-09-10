import java.lang.String;
import java.util.*;


public class DiceWinnerInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter name of the first Player: ");
        String playerOne = scanner.nextLine();
        System.out.print("Enter name of the second Player: ");
        String playerTwo = scanner.nextLine();
        
        // Create Player 1 and Player 2 Dice values in to one variable
        int[] playerOneDice = getDiceValuesForPlayer(random);
        int[] playerTwoDice = getDiceValuesForPlayer(random);
        
        
        System.out.println("Below are dice values for " + playerOne + " => ");
        String playerOneResult = Arrays.toString(playerOneDice);
        System.out.print(playerOneResult + '\n');
        
        String playerOneEvaluation = getEvaluationString(playerOneDice);

        System.out.println("Combination for " + playerOne + " is " + playerOneEvaluation + '\n');

        System.out.println("\nBelow are dice values for " + playerTwo + " => ");
        String playerTwoResult = Arrays.toString(playerTwoDice);
        System.out.print(playerTwoResult + '\n');
        
        String playerTwoEvaluation = getEvaluationString(playerTwoDice);

        System.out.println("Combination for " + playerTwo + " is " + playerTwoEvaluation);
        
        
        int sumOfPlayerOne = Arrays.stream(playerOneDice).sum();
        int sumOfPlayerTwo = Arrays.stream(playerTwoDice).sum();
        
        if (sumOfPlayerOne > sumOfPlayerTwo) {
            System.out.println("\n"+ "Winner is " + playerOne + ".");
        } else if (sumOfPlayerTwo > sumOfPlayerOne) {
            System.out.println("\n"+"Winner is " + playerTwo + ".");
        } else {
            System.out.println("\n"+"Winner is Tie.");
        }
    }
    
    public static int[] getDiceValuesForPlayer(Random random) {
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            // random.nextInt -> get numbers from 0 to (highest number - 1).
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }
    
    /**
     *  dice -> values
     *  [
     *   6,
     *   6,
     *   6,
     *   6,
     *   2,
     *   2,
     *  ]
     */
    public static String getEvaluationString(int[] dice) {
        /**
         *  counts
         *  [
         *   1 => 2
             5 => 4,
         *  ]
         */
        int[] counts = new int[6];
        for (int die : dice) {
            counts[die - 1]++;
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 5) {
                return "Five of a kind";
            } else if (counts[i] == 4) {
                 return  "Four of a kind";
            } else if (counts[i] == 3) {
                for (int j = 0; j < counts.length; j++) {
                    if (counts[j] == 2) {
                         return "Three of a kind and a pair";
                    }
                }
                return "Three of a kind";
            } else if (counts[i] == 2) {
                return "A pair";
            }
        }
        
        return "Highest number";
    }
}
