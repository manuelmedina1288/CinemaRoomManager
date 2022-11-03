package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner scanner = new Scanner(System.in);
    private static int rows = 0;
    private static int seatsPerRow = 0;
    private static int totalSeats = 0;
    private static String[][] movieTheater;
    private static int numberOfTicketSold = 0;
    private static int currentIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        fillMovieTheather();
        showMenu();

    }

    public static void showMenu() {
        boolean repeat = true;

        while (repeat) {

            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int option = scanner.nextInt();

            switch(option) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    printSeatMap();
                    break;
                case 2:
                    buyATicket();
                    break;
                case 3:
                    showStatistics();
                    break;
                default:
                    System.out.println("No a valid option");
            }
        }
    }

    public static void buyATicket() {

        boolean repeat = true;

        while (repeat) {
            System.out.println("\nEnter a row number:");
            int selectedRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int selectedSeat = scanner.nextInt();

            if (selectedRow < 0 || selectedRow > movieTheater.length || selectedSeat < 0 || selectedSeat > movieTheater.length) {
                System.out.println("Wrong input!");
                continue;
            }

            if (movieTheater[selectedRow - 1][selectedSeat - 1].equals(" S")) {
                movieTheater[selectedRow - 1][selectedSeat - 1] = " B";
                numberOfTicketSold++;

                if (totalSeats <= 60) {
                    System.out.println("\nTicket price: $10");
                    currentIncome += 10;
                } else {
                    if (selectedRow >= (rows / 2 + rows % 2)) {
                        System.out.println("\nTicket price: $8");
                        currentIncome += 8;
                    }
                    if (selectedRow < (rows / 2 + rows % 2)){
                        System.out.println("\nTicket price: $10");
                        currentIncome += 10;
                    }
                }

                repeat = false;

            } else {
                System.out.println("\nThat ticket has already been purchased!");
            }
        }

    }

    public static void fillMovieTheather() {

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();

        totalSeats = rows * seatsPerRow;

        movieTheater = new String[rows][seatsPerRow];

        for (int i = 0; i < movieTheater.length; i++) {
            for (int j = 0; j < movieTheater[i].length; j++) {
                movieTheater[i][j] = " S";
            }
        }
    }

    public static void printSeatMap() {

        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 0; i < movieTheater[0].length; i++) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();

        for (int i = 0; i < movieTheater.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < movieTheater[i].length; j++) {
                System.out.print(movieTheater[i][j]);
            }
            System.out.println();
        }

    }

    public static void showStatistics() {

        final int frontHalfPrice = 10;
        final int backHalfPrice = 8;

        System.out.printf("%nNumber of purchased tickets: %d%n", numberOfTicketSold);
        System.out.printf("Percentage: %.2f%%%n", (double) numberOfTicketSold * (100.00 / totalSeats));
        System.out.printf("Current income: $%d%n", currentIncome);

        int totalIncome = 0;

        if (totalSeats <= 60) {

            totalIncome = totalSeats * frontHalfPrice;

        } else {
            int frontHalf = rows / 2;
            int backHalf = rows / 2 + rows % 2;

            totalIncome = frontHalf * seatsPerRow * frontHalfPrice + backHalf * seatsPerRow * backHalfPrice;

        }

        System.out.printf("Total income: $%d%n", totalIncome );

    }
}