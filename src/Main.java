import java.util.Random;
import java.util.Scanner;

public class Main {
    static int monsterHealth = 50;
    static int playerHealth = 50;
    static int heals = 3;
    static int wins = 0;
    static int losses = 0;
    public static void main(String[] args) {
        System.out.println("Welcome to monster fight!");

        fightMonster();
    }
    static void fightMonster() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        printStats();

        while (playerHealth > 0 && monsterHealth > 0) {
            System.out.print("(A)ttack" + ((heals > 0) ? "or (H)eal? " : "") + '(' + heals + " heals left): ");
            String sInput = scan.nextLine().trim().toLowerCase();

            char input;
            try {
                input = sInput.charAt(0);
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            if (input == 'h') {
                if (heals <= 0) {
                    System.out.println("You have no heals left.");
                    continue;
                }

                if (playerHealth == 50) {
                    System.out.println("You are at max health.");
                    continue;
                }

                int healStrength = rand.nextInt(21) + 5;

                playerHealth += healStrength;
                heals--;

                System.out.println("You healed " + healStrength + " health.");
                printStats();
                attackPlayer();
                printStats();
            } else if (input == 'a') {
                attackMonster();
                attackPlayer();
                printStats();
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

        System.out.print("Play again? (y/n): ");
        char input = scan.nextLine().trim().toLowerCase().charAt(0);

        if (input == 'n') {
            System.out.println("You defeated " + wins + " monsters and were defeated " + losses + " time(s).");
            return;
        }

        monsterHealth = 50;
        playerHealth = 50;
        heals = 3;

        fightMonster();
    }

    static void attackPlayer() {
        Random rand = new Random();

        if (monsterHealth <= 0)
            return;

        if (monsterHealth <= 5) {
            int healStrength = rand.nextInt(31) + 10;

            monsterHealth += healStrength;
            System.out.println("The monster healed itself " + healStrength + " health.");

            return;
        }

        int monsterStrength = rand.nextInt(21) + 5;

        playerHealth -= monsterStrength;
        System.out.println("The monster hit you for " + monsterStrength + " damage.");
    }

    static void attackMonster() {
        Random rand = new Random();

        if (playerHealth <= 0)
            return;

        int playerStrength = rand.nextInt(18) + 7;
        monsterHealth -= playerStrength;
        System.out.println("You hit the monster for " + playerStrength + " health.");
    }

    static void printStats() {
        // Added an extra line to make it easier to follow along with the game
        System.out.println("\nYour health: " + playerHealth + " | Monster's health: " + monsterHealth);
    }
}