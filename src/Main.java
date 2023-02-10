import java.util.Random;
import java.util.Scanner;

public class Main {
    // Need access to these in multiple functions
    // I was thinking of creating a `Creature` class for both the monster and player, but I don't know if I'm allowed to
    // go that far ahead of myself.
    static int monsterHealth = 50;
    static int playerHealth = 50;
    static int heals = 3;
    public static void main(String[] args) {
        System.out.println("Welcome to Monster Fight!");

        fightMonster();
    }
    static void fightMonster() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        int wins = 0;
        int losses = 0;

        printStats();

        while (playerHealth > 0 && monsterHealth > 0) {
            System.out.print("(A)ttack" + ((heals > 0) ? " or (H)eal? " : "") + '(' + heals + " heals left): ");

            // Make sure whether the input is actually valid and only take the first character, discarding the rest
            // I didn't just use `next()` because that would leave it in `System.in` which would screw up the input for
            // the rest of the program
            char input;
            try {
                input = scan.nextLine().trim().toLowerCase().charAt(0);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            if (input == 'h') {
                // Can't heal if you have none left
                if (heals <= 0) {
                    System.out.println("You have no heals left.");
                    continue;
                }

                if (playerHealth == 50) {
                    System.out.println("You are at max health.");
                    continue;
                }

                int healStrength = rand.nextInt(21) + 5;

                if (healStrength + playerHealth > 50) {
                    playerHealth = 50;
                } else {
                    playerHealth += healStrength;
                }

                heals--;

                System.out.println("You healed " + healStrength + " health.");
                printStats();
                attackPlayer();
            } else if (input == 'a') {
                attackMonster();
                attackPlayer();
            } else {
                System.out.println("Invalid option.");
            }
        }

        if (monsterHealth <= 0) {
            System.out.println("You won!");
            wins++;
        } else {
            System.out.println("You lost!");
            losses++;
        }

        char input;
        do {
            System.out.print("Play again? (y/n): ");

            try {
                input = scan.nextLine().trim().toLowerCase().charAt(0);
            } catch(Exception e) {
                System.out.println("Invalid input");
                continue;
            }

            if (input == 'n') {
                System.out.println("You defeated " + wins + " monsters and were defeated " + losses + " time(s).");
                return;
            } else if ( input != 'y') {
                System.out.println("Invalid input.");
                continue;
            }

            monsterHealth = 50;
            playerHealth = 50;
            heals = 3;

            fightMonster();
        } while(true);
    }

    static void attackPlayer() {
        Random rand = new Random();

        // Can't attack anything if the monster is dead so return when the monster dies
        if (monsterHealth <= 0)
            return;

        if (monsterHealth <= 5) {
            int healStrength = rand.nextInt(31) + 10;

            monsterHealth += healStrength;
            System.out.println("The monster healed itself " + healStrength + " health.");

            printStats();

            return;
        }

        int monsterStrength = rand.nextInt(21) + 5;

        if (playerHealth - monsterStrength < 0)
            playerHealth = 0;
        else
            playerHealth -= monsterStrength;

        System.out.println("The monster hit you for " + monsterStrength + " damage.");

        printStats();
    }

    static void attackMonster() {
        Random rand = new Random();

        if (playerHealth <= 0)
            return;

        int playerStrength = rand.nextInt(18) + 7;

        if (monsterHealth - playerStrength < 0)
            monsterHealth = 0;
        else
            monsterHealth -= playerStrength;

        System.out.println("You hit the monster for " + playerStrength + " damage.");
    }

    static void printStats() {
        // Added an extra line to make it easier to follow along with the game
        System.out.println("\nYour health: " + playerHealth + " | Monster's health: " + monsterHealth);
    }
}