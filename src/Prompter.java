import java.util.Scanner;

public class Prompter {

    String askForWarrior() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose your warrior: k (knight); b (barbarian); w (wizard)");
        String warrior = scan.next();

        while (!warrior.equals("k") && !warrior.equals("b") && !warrior.equals("w")) {
            System.out.println("\n" + "Only k / b / w characters are available for the warrior!");
            System.out.print("Warrior: ");
            warrior = scan.next();
        }

        return warrior;
    }

    String askForAction() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n" + "Choose your action: a (attack); b (block)");
        String action = scan.next();

        while (!action.equals("a") && !action.equals("b")) {
            System.out.println("\n" + "Only a / b characters are available for the action!");
            System.out.print("Action: ");
            action = scan.next();
        }

        return action;
    }
}
