import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MainTest {

    Board board = Main.board;

    @Test
    void gameResult_StatusXWin_True() {
        String input = "1 3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = board.CROSS;
        board.mas[0][0] = "X";
        board.mas[0][1] = "X";
        board.mas[0][2] = " ";
        board.mas[1][0] = "O";
        board.mas[1][1] = "O";
        board.mas[1][2] = " ";
        board.mas[2][0] = " ";
        board.mas[2][1] = " ";
        board.mas[2][2] = " ";

        Main.gameResult();

        StringBuilder result = new StringBuilder();
        result.append("X enter the coordinates: ");
        result.append("---------\n");
        for (int i = 0; i < board.LINE; i++) {
            result.append("| ");
            for (int j = 0; j < board.COLUMN; j++) {
                result.append(board.mas[i][j]).append(" ");
            }
            result.append("| \n");
        }
        result.append("---------\n");
        result.append("X wins\n");

        assertEquals(result.toString(), out.toString());
    }

    @Test
    void gameResult_StatusOWin_True() {
        String input = "2 3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = board.ZERO;
        board.mas[0][0] = "X";
        board.mas[0][1] = "X";
        board.mas[0][2] = " ";
        board.mas[1][0] = "O";
        board.mas[1][1] = "O";
        board.mas[1][2] = " ";
        board.mas[2][0] = " ";
        board.mas[2][1] = " ";
        board.mas[2][2] = "X";

        Main.gameResult();

        // result
        StringBuilder result = new StringBuilder();
        result.append("O enter the coordinates: ");
        result.append("---------\n");
        for (int i = 0; i < board.LINE; i++) {
            result.append("| ");
            for (int j = 0; j < board.COLUMN; j++) {
                result.append(board.mas[i][j]).append(" ");
            }
            result.append("| \n");
        }
        result.append("---------\n");
        result.append("O wins\n");

        assertEquals(result.toString(), out.toString());
    }

    @Test
    void gameResult_StatusDraw_True() {
        String input = "3 2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = board.ZERO;
        board.mas[0][0] = "X";
        board.mas[0][1] = "X";
        board.mas[0][2] = "O";
        board.mas[1][0] = "O";
        board.mas[1][1] = "O";
        board.mas[1][2] = "X";
        board.mas[2][0] = "X";
        board.mas[2][1] = " ";
        board.mas[2][2] = "X";

        Main.gameResult();

        // result
        StringBuilder result = new StringBuilder();
        result.append("O enter the coordinates: ");
        result.append("---------\n");
        for (int i = 0; i < board.LINE; i++) {
            result.append("| ");
            for (int j = 0; j < board.COLUMN; j++) {
                result.append(board.mas[i][j]).append(" ");
            }
            result.append("| \n");
        }
        result.append("---------\n");
        result.append("Game end. Draw\n");

        assertEquals(result.toString(), out.toString());
    }

    @Test
    void startGame_ActivePlayerIsCross_true() {
        String activePlayers = board.CROSS;
        String[][] mas = new String[board.LINE][board.COLUMN];
        for (int i = 0; i < board.LINE; i++) {
            for (int j = 0; j < board.COLUMN; j++) {
                mas[i][j] = " ";
            }
        }
        Main.startGame();

        assertEquals(activePlayers, Main.activePlayers);
    }

    @Test
    void getCoordinates_IncorrectDataEntry_True() {
        String input = "a a\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = board.ZERO;
        Main.putCoordinates();

        String consoleOutput = "O enter the coordinates: " +
                "You should enter numbers!\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    void getCoordinates_CellIsOccupied_False() {
        String input = "1 5\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = board.ZERO;
        Main.putCoordinates();

        String consoleOutput = "O enter the coordinates: " +
                "Coordinates should be from 1 to 3!\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    void getCoordinates_CorrectDataEntry_True() {
        String input = "3 1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        board.mas[0][0] = "X";
        board.mas[0][1] = "X";
        board.mas[0][2] = "X";
        board.mas[1][0] = "O";
        board.mas[1][1] = "O";
        board.mas[1][2] = " ";
        board.mas[2][0] = " ";
        board.mas[2][1] = " ";
        board.mas[2][2] = " ";

        assertTrue(Main.putCoordinates());
    }
    @Test
    void validateCoordinate_CoordinatesValid_True() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.mas[i][j] = board.EMPTY;
            }
        }
        boolean result = Main.validateCoordinate(2, 2);
        assertTrue(result);
    }

    @Test
    void validateCoordinate_CoordinatesMustBeFromOneToThree_False() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.validateCoordinate(4, 4);
        String consoleOutput = "Coordinates should be from 1 to 3!\n";

        assertEquals(consoleOutput, out.toString());
    }

    @Test
    void validateCoordinate_CellIsOccupied_False() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.activePlayers = board.ZERO;
        board.mas[0][0] = "X";
        board.mas[0][1] = "X";
        board.mas[0][2] = "X";
        board.mas[1][0] = "O";
        board.mas[1][1] = "O";
        board.mas[1][2] = " ";
        board.mas[2][0] = " ";
        board.mas[2][1] = " ";
        board.mas[2][2] = " ";

        Main.validateCoordinate(1, 1);
        String consoleOutput = "This cell is occupied! Choose another one!\n";

        assertEquals(consoleOutput, out.toString());
    }
}