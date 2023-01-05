public class Board {

    public final String EMPTY = " ";
    public final String CROSS = "X";
    public final String ZERO = "O";
    public final int LINE = 3;
    public final int COLUMN = 3;
    public final int GAME_NOT_FINISH = 0;
    public final int DRAW = 1;
    public final int X_WIN = 2;
    public final int O_WIN = 3;
    public String[][] mas = new String[LINE][COLUMN];

    public void showBoard() {
        System.out.println("---------");
        for (int i = 0; i < LINE; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }

    public int analiseBoard(String winner) {
        if (winner.equals(CROSS)) {
            return X_WIN;
        } else if (winner.equals(ZERO)) {
            return O_WIN;
        } else {
            boolean emptyBoard = isFullBoard();
            if (emptyBoard) {
                return DRAW;
            }
        }
        return GAME_NOT_FINISH;
    }

     String findWinner() {
         String line = null;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case (0) -> line = mas[0][0] + mas[0][1] + mas[0][2];
                case (1) -> line = mas[1][0] + mas[1][1] + mas[1][2];
                case (2) -> line = mas[2][0] + mas[2][1] + mas[2][2];
                case (3) -> line = mas[0][0] + mas[1][0] + mas[2][0];
                case (4) -> line = mas[0][1] + mas[1][1] + mas[2][1];
                case (5) -> line = mas[0][2] + mas[1][2] + mas[2][2];
                case (6) -> line = mas[0][0] + mas[1][1] + mas[2][2];
                case (7) -> line = mas[0][2] + mas[1][1] + mas[2][0];
                default -> {
                }
            }

            if (line.equals("XXX")) {
                return CROSS;
            } else if (line.equals("OOO")) {
                return ZERO;
            }
        }
        return null;
    }

    public boolean isFullBoard() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (mas[i][j].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}
