package Model;

import javafx.scene.layout.GridPane;

public class ChessBoardFactory {
    public ChessBoard makeChessBoard(String ChessBoardType, GridPane chessBoard, String theme){
        if(ChessBoardType.equals(null)){
            return null;
        }
        if(chessBoard.equals(null)){
            return null;
        }
        if(theme.equals(null)){
            return null;
        }

        if(ChessBoardType.equals("First Stage ChessBoard")){
            return new StageOneChessBoard(chessBoard, theme);
        }
        else if(ChessBoardType.equals("Second Stage ChessBoard")){
            return new StageTwoChessBoard(chessBoard, theme);
        }
        else if(ChessBoardType.equals("Third Stage ChessBoard")){
            return new StageThreeChessBoard(chessBoard, theme);
        }
        else{
            return new StageFourthChessBoard(chessBoard, theme);
        }
    }
}
