import java.util.Scanner;

public class Main {
    public static int gameStatus;
    public static String activePlayers;
    static Board board = new Board();

    public static void main(String[] args) {
        startGame();
        gameResult();
    }

    public static void startGame() {
        for (int i = 0; i < board.LINE; i++) {
            for (int j = 0; j < board.COLUMN; j++) {
                board.mas[i][j] = board.EMPTY;
            }
        }
        activePlayers = board.CROSS;
        board.showBoard();
    }

    public static void gameResult() {
        do {
            String winner = getWinner();
            gameStatus = board.analiseBoard(winner);
            board.showBoard();
            if (gameStatus == board.X_WIN) {
                System.out.println("X wins");
            } else if (gameStatus == board.O_WIN) {
                System.out.println("O wins");
            } else if (gameStatus == board.DRAW) {
                System.out.println("Game end. Draw");
            }

            activePlayers = activePlayers.equals(board.CROSS) ? board.ZERO : board.CROSS;
        } while (gameStatus == board.GAME_NOT_FINISH);
    }

    public static String getWinner() {
        boolean takeCoordinates;
        do {
            takeCoordinates = putCoordinates();
        } while (!takeCoordinates);

        String winner = board.findWinner();
        if (winner != null) {
            return winner;
        }
        return board.EMPTY;
    }


    public static boolean putCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(activePlayers + " enter the coordinates: ");
        try {
            String str = scanner.nextLine();
            String[] s = str.split("\\s+");
            int line = Integer.parseInt(s[0]) - 1;
            int column = Integer.parseInt(s[1]) - 1;
            if(validateCoordinate(line, column)){
                board.mas[line][column] = activePlayers;
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    private static boolean validateCoordinate(int line, int column) {
        if (!(line >= 0 && line < board.LINE && column >= 0 && column < board.COLUMN)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        } else if (!board.mas[line][column].equals(board.EMPTY)) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            return true;
        }
    }
}

