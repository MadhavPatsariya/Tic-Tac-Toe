import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static boolean firstPlayerTurn = true;
    static char[][] board = new char[3][3];
    static String[] playersArr;
    static String[] playersMark;
    static Scanner scanner;
    static int total = 9;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("hi, welcome to tic-tac-toe..");
        boolean playing = true;
        for(char[] row: board) {
            Arrays.fill(row, '.');
        }
        System.out.println("Kindly enter player names");
        playersArr = scanner.nextLine().trim().split(" ");
        System.out.println("Kindly enter your mark");
        playersMark = scanner.nextLine().trim().split(" ");

        while(playing) {
            System.out.println("Please choose an option to proceed: ");
            System.out.println("1. Play");
            System.out.println("2. Quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                if(total == 0) {
                    System.out.println("Game draw");
                    playing = false;
                }
                else{
                    playing = playGame();
                }
            } else {
                playing = false;
            }
        }
        scanner.close();
    }

    public static boolean playGame() {
        int result = playerMove();
        if(result == 0) {
            System.out.println("Player: " + playersArr[0] + " Won!!!!!!");
            return false;
        }
        else if(result == 1) {
            System.out.println("Player: " + playersArr[1] + " Won!!!!!!");
            return false;
        }
        return true;
    }

    public static int playerMove() {
        int playerIdx = 0;
        total = total - 1;
        if(!firstPlayerTurn) {
            playerIdx = 1;
        }
        System.out.println("Player: " + playersArr[playerIdx] + " turn");
        System.out.println("Kindly enter the position you want to move: ");
        String[] positions = scanner.nextLine().trim().split(" ");
        int row = Integer.parseInt(positions[0]);
        int column = Integer.parseInt(positions[1]);
        if(row > 2 || column > 2) {
            System.out.println("Wrong input.");
        }
        else if(board[row][column] != '.') {
            System.out.println("Invalid Operation. Already a value exists");
        }
        else {
            board[row][column] = playersMark[playerIdx].charAt(0);
            displayBoard(board);
            char markA = playersMark[0].charAt(0);
            char markB = playersMark[1].charAt(0);
            char winMark = didAnyoneWin();
            if(winMark == markA) {
                return 0;
            }
            else if(winMark == markB) {
                return 1;
            }
            firstPlayerTurn = !firstPlayerTurn;
        }
        return 2;
    }

    public static void displayBoard(char[][] board) {
        System.out.println("Current Board: ");
        for(char[] row: board) {
            for(char element: row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static char didAnyoneWin() {
        char markA = playersMark[0].charAt(0);
        char markB = playersMark[1].charAt(0);
        if(checkBoard(markA)){
            return markA;
        }
        else if(checkBoard(markB)){
            return markB;
        }
        else{
            return '.';
        }
    }

    public static boolean checkBoard(char mark) {
        return checkLeftDiagonal(mark, 0, 0) || checkRightDiagonal(mark, 0, 2) ||
                checkColumn(mark, 0) || checkRow(mark, 0);
    }

    private static boolean checkLeftDiagonal(char mark, int r, int c) {
        if(r > 2 || c > 2) {
            return true;
        }
        return board[r][c] == mark && checkLeftDiagonal(mark, r + 1, c + 1);
    }

    private static boolean checkRightDiagonal(char mark, int r, int c) {
        if(r > 2 || c < 0) {
            return true;
        }
        return board[r][c] == mark && checkRightDiagonal(mark, r + 1, c - 1);
    }

    private static boolean checkColumn(char mark, int c) {
        if(c > 2) {
            return false;
        }
        for(int i = 0; i < 3; i++) {
            if (board[i][c] != mark) {
                return checkColumn(mark, c + 1);
            }
        }
        return true;
    }

    private static boolean checkRow(char mark, int r) {
        if(r > 2) {
            return false;
        }
        for(int i = 0; i < 3; i++) {
            if(board[r][i] != mark) {
                return checkRow(mark, r + 1);
            }
        }
        return true;
    }
}