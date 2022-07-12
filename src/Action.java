import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Prompter prompter = new Prompter();
        Random rand = new Random();

		// Player data
        int userHp = 14;
        int attackCounter = 0;
        int bonusAmount = 0;
        boolean payBonus = false;

		// PC data
        int pcHp = 14;
        int pcAtt = 2;
        int pcReg = 2;

        String warrior = prompter.askForWarrior();
        System.out.println("\n" + ".:: Let the battle begins ::.");

        while (pcHp > 0) {
            String action = prompter.askForAction();
            Action battle = new Action();
			
            int userAtt = 0;
            int userBlock = 0;
            int userReg = 0;

			// PC attack ratio (2-6)
            int pcAttRand = rand.nextInt(5);
            int pcAttRatio = pcAtt + pcAttRand;

            // Store max PC attack ratio as a bonus for user bravery
            if (pcAttRatio > bonusAmount) {
                bonusAmount = pcAttRatio;
            }

			// Players updated stats
            switch (warrior) {
                case "k":
                    userAtt = battle.knightPow();
                    userBlock = battle.knightBlock();
                    userReg = battle.knightReg();
                    break;
                case "b":
                    userAtt = battle.barbarPow();
                    userBlock = battle.barbarBlock();
                    userReg = battle.barbarReg();
                    break;
                case "w":
                    userAtt = battle.wizardPow();
                    userBlock = battle.barbarBlock();
                    userReg = battle.wizardReg();
                    break;
            }
			
            switch (action) {
				// Attack description
                case "a":
                    attackCounter += 1;

                    pcHp = pcHp - userAtt;
                    System.out.println("\n" + "You hit " + userAtt + " points." );

                    if (userAtt > 3) {
                        pcHp += pcReg;
                        System.out.println("PC regenerated (+" + pcReg + ") and left " + pcHp + " health points.");
                    } else {
                        System.out.println("PC left " + pcHp + " health points.");
                    }

                    userHp = userHp - pcAttRatio;
                    System.out.println("\n" + "PC hit " + pcAttRatio + " points." );
                    System.out.println("User left " + userHp + " health points.");

                    break;
				// Block description
                case "b":
                    attackCounter = 0;

                    if (pcAttRatio > userBlock) {
                        int damagedBy = pcAttRatio - userBlock;
                        userHp = userHp - damagedBy;

                        System.out.println("\n" + "You used block, but PC was stronger and damaged your HP by " +
                                damagedBy + ".");
                        System.out.println("User left " + userHp + " health points.");
                    } else {
                        userHp = userHp + userReg;

                        System.out.println("\n" + "You block PC's attack and regenerated " + userReg + " HP.");
                        System.out.println("User left " + userHp + " health points.");
                    }

                    break;
            }

            if (attackCounter >= 3) {
                payBonus = true;
                attackCounter = 0;
            }

            // If PC die, but the user has a chance to live
            if (pcHp <= 3 && payBonus) {
                int specialAtt = rand.nextInt(4);

                if (userHp >= 0) {
                    userHp -= specialAtt;
                } else {
                    userHp = userHp - specialAtt;
                }

                System.out.println("\n" + "You have received a special attack from a dying PC (+-" + specialAtt + ")!");
                System.out.println("User left " + userHp + " health points.");
            }

            // If the user is close to death and has once shown courage
            if (userHp <= 0 && payBonus) {
                userHp += bonusAmount;
                payBonus = false;

                System.out.println("\n" + "You almost lost!");
                System.out.println("But thanks to your courage you get a second life (+" + bonusAmount + ")!");
                System.out.println("User left " + userHp + " health points.");
            }

            if (pcHp <= 0 && userHp > 0) {
                System.out.println("\n" + "You won!");
                break;
            } else if (pcHp > 0 && userHp <= 0) {
                System.out.println("\n" + "You lost!");
                break;
            } else if (pcHp <= 0 && userHp <= 0) {
                System.out.println("\n" + "Draw!");
                break;
            }
        }
    }
}
