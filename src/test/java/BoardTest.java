import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BoardTest {
    Board board = Main.board;

    @Test
    void analiseBoard_WinnerEqualsCross_XWin() {
        int result = board.analiseBoard(board.CROSS);
        assertEquals(board.X_WIN, result);
    }

    @Test
    void analiseBoard_WinnerEqualsZero_OWin() {
        int result = board.analiseBoard(board.ZERO);
        assertEquals(board.O_WIN, result);
    }

    @Test
    void analiseBoard_WinnerEqualsEmpty_DrawGame() {
        String[][] mas = board.mas;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mas[i][j] = "O";
            }
        }
        int result = board.analiseBoard(board.EMPTY);

        assertTrue(board.isFullBoard());
        assertEquals(board.DRAW, result);
    }

    @Test
    void analiseBoard_WinnerEqualsEmpty_GameNotFinish() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.mas[i][j] = "O";
            }
        }
        board.mas[1][1] = " ";
        int result = board.analiseBoard(board.EMPTY);

        assertFalse(board.isFullBoard());
        assertEquals(board.GAME_NOT_FINISH, result);
    }

    @Test
    void isFullBoard_BoardIsFull_True() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.mas[i][j] = "O";
            }
        }
        assertTrue(board.isFullBoard());
    }

    @Test
    void isFullBoard_BoardIsFull_False() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.mas[i][j] = "O";
            }
        }
        board.mas[1][1] = " ";

        assertFalse(board.isFullBoard());
    }
}
