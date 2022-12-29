package Model;

import Controller.GameController;
import Utils.Difficulty;
import Utils.Stage;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Random;

public class Game extends GameController{

    private int id;
    private static int idCounter = 1;
    private GridPane chessBoard;
    public static Piece currentPiece;
    public static String currentPlayer;
    public static ChessBoard cb;
    private boolean game;
    private Stage stage;
    private boolean turnToPlay;
    private User currentuser;


    public Game(GridPane chessBoard, String theme, User user, String stage, Stage stage1) {
        ChessBoardFactory boardFactory = new ChessBoardFactory();
        cb = boardFactory.makeChessBoard(stage,chessBoard, theme);
        currentPiece = null;
        currentPlayer = "white";
        this.game = true;
        this.currentuser = user;
        this.id = idCounter++;
        staticStage.setText(Stage.First.toString());
        addEventHandlers(cb.chessBoard);
    }

    public static ChessBoard getCb() {
        return cb;
    }

    public static void setCb(ChessBoard cb) {
        Game.cb = cb;
    }

    private void addEventHandlers(GridPane chessBoard){
        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventTarget target = event.getTarget();

                // Clicked on square
                if(target.toString().equals("Square")){
                    Square square = (Square) target;
                    if(square.occupied){
                        Piece newPiece = (Piece) square.getChildren().get(0);
                        // Selecting a new piece
                        if(currentPiece == null){
                            currentPiece = newPiece;
//                            currentPiece.getAllPossibleMoves();
                            if(!currentPiece.getColor().equals(currentPlayer)){
                                currentPiece = null;
                                return;
                            }
                            selectPiece(game);
                        }
                        // Selecting other piece of same color || Killing a piece
                        else{
                            if(currentPiece.color.equals(newPiece.color)){
                                deselectPiece(false);
                                currentPiece = newPiece;
//                                currentPiece.getAllPossibleMoves();
                                selectPiece(game);
                            }
                            else{
                                killPiece(square);
                            }
                        }

                    }
                    // Dropping a piece on blank square
                    else {
                        dropPiece(square);
                    }
                }
                // Clicked on piece
                else {
                    Piece newPiece = (Piece) target;
                    Square square = (Square) newPiece.getParent();
                    // Selecting a new piece
                    if(currentPiece == null) {
                        currentPiece = newPiece;
                        if(!currentPiece.getColor().equals(currentPlayer)) {
                            currentPiece = null;
                            return;
                        }
                        selectPiece(game);
                    }
                    // Selecting other piece of same color || Killing a piece
                    else{
                        if(currentPiece.color.equals(newPiece.color)) {
                            deselectPiece(false);
                            currentPiece = newPiece;
                            selectPiece(game);
                        }
                        else {
                            killPiece(square);
                        }
                    }

                }
            }
        });
    }

    private void selectPiece(boolean game) {
        if(!game){
            currentPiece = null;
            return;
        }

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPiece.setEffect(borderGlow);
        currentPiece.getAllPossibleMoves();
        currentPiece.showAllPossibleMoves(true);
    }

    private void deselectPiece(boolean changePlayer) {
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece = null;
        if(changePlayer) currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    private void dropPiece(Square square) {
        Color color1 = Color.web("#FF0000");
        if (!currentPiece.possibleMoves.contains(square.name)) return;
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);

        if(square.getBackground().getFills().get(0).getFill().equals(color1)){
            if(square.getChildren().get(0).toString().equals("white Knight") || square.getChildren().get(0).toString().equals("black Knight")) {

                SysData.getInstance().getCurrentUser().setPoints(SysData.getInstance().getCurrentUser().getPoints() - 1);
                staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getPoints()));

            }
        }
        else if(square.getChildren().get(0).toString().equals("white Knight") || square.getChildren().get(0).toString().equals("black Knight")) {
            SysData.getInstance().getCurrentUser().setPoints(SysData.getInstance().getCurrentUser().getPoints() + 1);
            staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getPoints()));
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));

        }
        else if(SysData.getInstance().getCurrentUser().getPoints() == 15){
            ChessBoardFactory cbTwo = new ChessBoardFactory();
            cb = cbTwo.makeChessBoard("Second Stage ChessBoard",cb.chessBoard,"Sandcastle" );

            setCb(cb);
            setStage(Stage.Second);
            staticStage.setText(Stage.Second.toString());

        }
        if(square.getType() != null && square.getType().equals("Question Square") && square.getChildren().get(0).toString().equals("white Knight")){
            Random rand1 = new Random();
            Difficulty d = Difficulty.randomDifficulty();
            int int_rand1 = rand1.nextInt(SysData.getInstance().getQuestions().get(d).size());
            Question q = SysData.getInstance().getQuestions().get(d).get(int_rand1);
            square = new QuestionSquare(square.getX(), square.getY(),q);


            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/QuestionPopUp.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                javafx.stage.Stage stage = new javafx.stage.Stage();
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("FXML");
                alert.setHeaderText("Load failure");
                alert.setContentText("Failed to load the FXML file.");
                alert.showAndWait();
            }
        }


    }
    private void killPiece(Square square) {
        Color color1 = Color.web("#FF0000");

        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if(killedPiece.type.equals("King")) this.game = false;

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        if(square.getChildren().get(0).toString().equals("white Knight") || square.getChildren().get(0).toString().equals("black Knight")) {
            SysData.getInstance().getCurrentUser().setPoints(SysData.getInstance().getCurrentUser().getPoints() + 1);
            staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getPoints()));
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        deselectPiece(true);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isTurnToPlay() {
        return turnToPlay;
    }

    public void setTurnToPlay(boolean turnToPlay) {
        this.turnToPlay = turnToPlay;
    }

    }

