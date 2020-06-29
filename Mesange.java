import java.util.Scanner;

public class Mesange {
    public static void main(String[] args) {
        int[][] rooms = {{1, 2, 3, 4, 5}, {0, 0, 0, 0, 0}};
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bonjour !");
        printTable(rooms);
        int action = askAction();

        while (action!=3) {
            if (action == 1) {
                int numberOfPeople;

                // Tant que l'utilisateur n'a pas entré un nombre de personnes valide
                do {
                    System.out.println("Entrer le nombre de personnes :");
                    numberOfPeople = scanner.nextInt();
                    if((numberOfPeople != 1) && (numberOfPeople != 2) && (numberOfPeople != 3) && (numberOfPeople != 4)) {
                        printImpossible();
                    }
                } while((numberOfPeople != 1) && (numberOfPeople != 2) && (numberOfPeople != 3) && (numberOfPeople != 4));

                // Recherche chambre libre
                searchRoom(rooms, numberOfPeople);
                printTable(rooms);
                action = askAction();
            }
            else if (action == 2) {
                int roomNumber;

                // Tant que l'utilisateur n'a pas entré un numéro de chambre valide
                do {
                    System.out.println("Entrer le numéro de chambre :");
                    roomNumber = scanner.nextInt();
                    if(roomNumber != 1 && roomNumber != 2 && roomNumber != 3 && roomNumber != 4 && roomNumber !=5) {
                        printImpossible();
                    }
                } while(roomNumber != 1 && roomNumber != 2 && roomNumber != 3 && roomNumber != 4 && roomNumber !=5);

                // Libértion de la chambre
                if(rooms[1][roomNumber - 1] == 0) {
                    printImpossible();
                } else {
                    rooms[1][roomNumber - 1] = 0;
                    printResultAction(action, roomNumber);
                }

                printTable(rooms);
                action = askAction();
            }
        }
       scanner.close();
    }

    // Choix de l'action à effectuer
    public static int askAction() {
        int a;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(""
                + "Quelle action voulez-vous effectuer ? (1 pour les arrivées, 2 pour les départs, 3 pour sortir) :\n"
                + "     * Tapez 1 pour une arrivée \n"
                + "     * Tapez 2 pour un départ \n"
                + "     * Tapez 3 pour sortir ");
            a = sc.nextInt();
        } while ((a != 1) && (a != 2) && (a != 3));
        return a;
    }

    // cherche une chambre disponible
    public static void searchRoom(int[][] tab, int people) {
        boolean done = false;

        if (people <= 2) {
            done = searchFreeRoom(1, tab, people);
        }
        if (people <= 4 && !done) {
            done = searchFreeRoom(0, tab, people);
        }
        if (!done) {
            printImpossible();
        }
    }

    // Boucle tableau recherche chambre libre
    static boolean searchFreeRoom(int a, int [][]tab, int p) {
        boolean succeed = false;
        for (int j = a; j < tab[1].length; j = j+2) {
            if (tab[1][j] == 0) {
                tab[1][j] = p;
                printResultAction(1, tab[0][j]);
                succeed = true;
                break;
            }
        }
        return succeed;
    }

    //affiche le tableau d'occupation des chambres
    static void printTable(int [][] tab) {
            System.out.print("________________________________________________________\n");
            System.out.print("| Numéro de chambre :    |  ");
            for(int i = 0; i < tab[0].length; i++) {
                System.out.print(tab[0][i]);
                System.out.print("  |  ");
            }
            System.out.println("\n|________________________|_____|_____|_____|_____|_____|");
            System.out.print("| Nombre de personnes :  |  ");
             for (int j = 0; j < tab[1].length; j++) {
                 System.out.print(tab[1][j]);
                 System.out.print("  |  ");
             }
            System.out.print("\n|________________________|_____|_____|_____|_____|_____|\n");
    }

     //affiche un message d'impossibilité
    static void printImpossible() {
        System.out.println("Nous ne sommes pas en mesure de répondre à votre demande.");
    }

    // affiche la réussite de l'action
    static void printResultAction(int a, int b) {
        String occupation = (a == 1 ? "réservée" : "libérée");
        System.out.println("La chambre "+b+ " a été "+occupation+".");
        System.out.println(" ");
    }
}
