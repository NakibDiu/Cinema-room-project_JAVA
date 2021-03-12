package com.company;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int collumn = input.nextInt();
        char[][] newArray = createArray(row, collumn);
        selection(row, collumn, newArray);
    }
    public static char[][] createArray(int row, int collumn) {
        char[][] array = new char[row][collumn];
        for (int i = 0; i < row; i++) {
            for(int j = 0; j < collumn; j++) {
                array[i][j] = 'S';
            }
        }
        return array;
    }

    public static void displayChoice() {
        System.out.println("\n1. show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void selection(int row, int collumn, char[][] array) {
        Scanner input = new Scanner(System.in);
        int  finish = 0;
        while(finish != 1) {
            displayChoice();
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    showTheSeats(row, collumn, array);
                    break;
                case 2:
                    buyTicket(row, collumn, array);
                    break;
                case 3:
                    showStatistics(row, collumn, array);
                    break;
                case 0:
                    finish = 1;
                    break;
                default:
                    break;
            }
        }
    }

    public static void showTheSeats(int row, int collumn, char[][] array) {
        System.out.println("\nCinema:");
        System.out.print(" ");
        for (int i = 0; i < collumn; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.print("\n");
        for (int j = 0; j < row; j++) {
            System.out.print(j + 1);
            for (int k = 0; k < collumn; k++) {
                System.out.print(" " + array[j][k]);
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public static void buyTicket(int row, int collumn, char[][] array) {
        Scanner input = new Scanner(System.in);
        int total = row * collumn;
        int check = 0;
        while (check != 1) {
            System.out.println();
            System.out.println("Enter a row number:");
            int rowNum = input.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = input.nextInt();
            if (rowNum <= row && seat <= collumn) {
                if (array[rowNum - 1][seat - 1] == 'S') {
                    array[rowNum - 1][seat - 1] = 'B';
                    int halfseats = row / 2;
                    int price;
                    if (total <= 60) {
                        price = 10;
                    } else {
                        if (rowNum <= halfseats) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    }
                    System.out.println("\nTicket price: $" + price);
                    System.out.println();
                    check = 1;
                } else if (array[rowNum - 1][seat - 1] == 'B') {
                    System.out.println("\nThat ticket has already been purchased!");
                }
            } else {
                System.out.println("\nWrong input!");
            }
        }
    }

    public static void showStatistics(int row, int collumn, char[][] array) {
        int sell = 0;
        int currentIncome = 0;
        int totalseat = row * collumn;
        int halfrow = row / 2;
        int firstHalf = halfrow * collumn;
        int price;
        int totalIncome = 0;
        double percentage;
        if (totalseat <= 60) {
            totalIncome = totalseat * 10;
        } else {
            totalIncome = firstHalf * 10 + (totalseat - firstHalf) * 8;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < collumn; j++) {
                if (array[i][j] == 'B') {
                    sell++;
                    if (totalseat <= 60) {
                        currentIncome += 10;
                    } else {
                        if (i < halfrow) {
                            currentIncome += 10;
                        } else{
                            currentIncome += 8;
                        }
                    }

                } else {
                    continue;
                }
            }
        }
        percentage = ((double) sell * 100) / (double) totalseat;
        System.out.printf("\nNumber of purchased tickets: %d\n", sell);
        String str = String.format("%.02f", percentage);
        System.out.println("Percentage: " + str + "%");
        System.out.printf("current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }
}
