import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        // Game constants
        int playerHealth = 50;
        int monsterHealth = 50;
        int heals = 3;

        System.out.println("Welcome to monster fight!");
        loop: while (true) {
            char input = '?';

            System.out.println("Your health: " + playerHealth + " | Monster health: " + monsterHealth);

            if (input != 'h') {
                do {
                    System.out.print("What would you like to do: (A)ttack " + ((heals == 0) ? "(No heals left)" : "(" + heals + " heals left)") + ": ");
                    input = scan.nextLine().trim().toLowerCase().charAt(0);
                } while (!(input == 'a' || input == 'h'));
            }

            // Player's turn
            switch (input) {
                case 'a' -> {
                    monsterHealth -= 10;
                    System.out.println("You hit the monster for 10 damage.");

                    if (monsterHealth < 1) {
                        System.out.println("You defeated the monster!");
                        break loop;
                    }
                }
                case 'h' -> {
                    if (playerHealth == 50) {
                        System.out.println("You cannot heal right now");
                        continue;
                    }

                    // Heal a random amount of health varying between 5 and 25
                    int healStrength = rand.nextInt(30) + 6;
                    playerHealth += healStrength;
                    heals--;
                    System.out.println("You healed " + healStrength + " health.");
                    continue;
                }
                default -> {
                    continue;
                }
            }


            // Monster's turn
            int monsterStrength = rand.nextInt(20) + 6;
            playerHealth -= monsterStrength;
            System.out.println("The monster hit you for " + monsterStrength + " damage.");

            if (playerHealth <= 0) {
                System.out.println("Defeat! The monster killed you. :(");
                break;
            }

            System.out.println();
        }
    }
}