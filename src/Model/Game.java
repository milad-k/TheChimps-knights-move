package Model;

import Controller.PopUpQuestionController;
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
import javafx.stage.Window;

import java.io.IOException;
import java.util.Random;
import java.util.Stack;

import static Controller.GameController.*;
import static Controller.GameLevel2Controller.staticPoints2;
import static Controller.GameLevel3Controller.staticPoints3;
import static Controller.GameLevel4Controller.staticPoints4;

public class Game{

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
    private Stack<Move> moves;


    public Game(GridPane chessBoard, String theme, User user, String stage, Stage stage1) {
        ChessBoardFactory boardFactory = new ChessBoardFactory();
        cb = boardFactory.makeChessBoard(stage,chessBoard, theme);
        currentPiece = null;
        currentPlayer = "white";
        this.game = true;
        this.currentuser = user;
        this.id = idCounter++;
        this.stage = stage1;
        staticStage.setText(stage1.toString());
        this.moves = new Stack<Move>();
        addEventHandlers(cb.chessBoard);
    }

    public static ChessBoard getCb() {
        return cb;
    }

    public static void setCb(ChessBoard cb) {
        Game.cb = cb;
    }

    public Stack<Move> getMoves() {
        return moves;
    }

    public void setMoves(Stack<Move> moves) {
        this.moves = moves;
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
                                if(!square.getChildren().get(0).toString().equals("black Queen") && square.occupied)
                                    killPiece(square);
                            }
                        }
                    }
                    // Dropping a piece on blank square
                    else {
                        if(!square.getType().equals("Question Square"))
                            dropPiece(square);
                        else {
//                            System.out.println("Q Places: " + cb.getQuestionSquares());
//                            System.out.println("possible moves: " + currentPiece.possibleMoves);
                            if (currentPiece.color.equals("white")) {
                                for(String item1 : cb.getQuestionSquares()) {
                                    for (String item2 : currentPiece.possibleMoves) {
                                        if (item2.equals(item1)) {
                                            dropQuestionMark(square);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
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
                            if(!square.getChildren().get(0).toString().equals("black Queen") && square.occupied)
                                killPiece(square);
                        }
                    }
                }
            }
        });
    }

    private void selectPiece(boolean game) {
        staticmessage.setText(" ");
        if(!game){
            currentPiece = null;
            return;
        }

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPiece.setEffect(borderGlow);
        if(this.stage.equals(Stage.First)){
            currentPiece.getAllPossibleMoves();
        }
        else{
            currentPiece.getAllPossibleMovesForAdvancedLevels();
        }
        currentPiece.showAllPossibleMoves(true);
    }

    private void deselectPiece(boolean changePlayer) {
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece = null;
        if(changePlayer) currentPlayer = "white";
    }

    private void dropQuestionMark(Square square) {
        Color color1 = Color.rgb(181, 101, 118);
        Color color2 = Color.rgb(181, 101, 118);
        if (!currentPiece.possibleMoves.contains(square.name)) return;
        LoadQuestionPopUp(square);
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);
        IncrementScore();
        square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
        square.setOccupied(true);
        Move move = new Move(square,+1);
        moves.push(move);
        cb.QuestionSquares.remove("Square" + square.getX() + square.getY());
        square.setOccupied(false);
        square.setType("Square");
        cb.addRandomQuestionsSquares();
    }

    private void dropPiece(Square square) {
        Color color1 = Color.rgb(181, 101, 118);
        if(currentPiece == null){
            return;
        }
        if (!currentPiece.possibleMoves.contains(square.name)) return;
        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);

        if(square.getBackground().getFills().get(0).getFill().equals(color1) && square.getChildren().get(0).toString().equals("white Knight")){
            decreasingScore(square);
            Move move = new Move(square,-1);
            moves.push(move);
        }
        if(square.getChildren().get(0).toString().equals("white Knight")) {
            IncrementScore();
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
            square.setOccupied(true);
            Move move = new Move(square,+1);
            moves.push(move);
        }

        if(square.getType() != null && square.getType().equals("Question Square") && square.getChildren().get(0).toString().equals("white Knight")){
            SysData.getInstance().loadQuestions("src/JSON/QuestionsFormat.json");
            staticmessage.setText("You Step on a question square! Please answer the question");
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
            square.setOccupied(true);
            addAnotherRandomQuestionSquare();
            LoadQuestionPopUp(square);
        }
        if(square.getType() != null && square.getType().equals("Random Jump Square") && square.getChildren().get(0).toString().equals("white Knight")){
            staticmessage.setText("Tou step on a random jump square! you will move to another random square");
            square.setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));
            square.setOccupied(true);
            addAnotherRandomJumpSquare();


        }
        if(square.getType() != null && square.getType().equals("Forgetful Square") && square.getChildren().get(0).toString().equals("white Knight")) {
            staticmessage.setText("You step on a forgetful square! Your last 3 steps will be canceled");
            addAnotherForgetfulSquare();
            Move move = new Move(square,+1);
            moves.push(move);
            move.removingLast3Moves(moves, square, this.stage,cb.theme);


        }
        if(square.getType() != null && square.getType().equals("Blocking Square") && square.getChildren().get(0).toString().equals("white Knight")) {
            staticmessage.setText("You cant Step on a blocking square! try another square");
            addAnotherBlockingSquare();
        }
    }

    private void addAnotherBlockingSquare() {
        //Color color1 = Color.web("black");

        Random rand = new Random();
        int int_rand = rand.nextInt(64);
        if(cb.squares.get(int_rand).getType().equals("Blocking Square")){
            addAnotherBlockingSquare();
        }
        else{
            cb.squares.get(int_rand).setType("Blocking Square");
            //cb.squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));

        }
    }

    private void addAnotherForgetfulSquare() {
        //Color color1 = Color.web("black");

        Random rand = new Random();
        int int_rand = rand.nextInt(64);
        if(cb.squares.get(int_rand).getType().equals("Forgetful Square")){
            addAnotherForgetfulSquare();
        }
        else{
            cb.squares.get(int_rand).setType("Forgetful Square");
            //cb.squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));

        }

    }

    private void addAnotherRandomJumpSquare() {
        //Color color1 = Color.web("black");

        Random rand = new Random();
        int int_rand = rand.nextInt(64);

        if(cb.squares.get(int_rand).getType().equals("Random Jump Square")){
            addAnotherRandomJumpSquare();
        }
        else{
            cb.squares.get(int_rand).setType("Random Jump Square");
            //cb.squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));

        }
    }

    private void addAnotherRandomQuestionSquare() {
        //Color color1 = Color.web("black");

            Random rand = new Random();
            int int_rand = rand.nextInt(64);
            if(cb.squares.get(int_rand).getType().equals("Question Square")){
                addAnotherRandomQuestionSquare();
            }
            else{
                cb.squares.get(int_rand).setType("Question Square");
                //cb.squares.get(int_rand).setBackground(new Background(new BackgroundFill(color1, CornerRadii.EMPTY, Insets.EMPTY)));

            }


    }

    private void decreasingScore(Square square) {
        if(this.stage == Stage.First) {
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
            staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
        }
        else if(this.stage == Stage.Second){
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
            int i = Integer.parseInt(staticPoints2.getText().toString());
            i--;
            staticPoints2.setText(String.valueOf(i));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

        }
        else if(this.stage == Stage.Third){
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
            int i = Integer.parseInt(staticPoints3.getText().toString());
            i--;
            staticPoints3.setText(String.valueOf(i));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

        }
        else if(this.stage == Stage.Fourth){
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() - 1);
            int i = Integer.parseInt(staticPoints4.getText().toString());
            i--;
            staticPoints4.setText(String.valueOf(i));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

        }
    }

    private void IncrementScore() {

        if(this.stage.equals(Stage.First)) {
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
            staticPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));
        }
        else if(this.stage == Stage.Second){
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
            int i = Integer.parseInt(staticPoints2.getText().toString());
            i++;
            staticPoints2.setText(String.valueOf(i));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));


        }
        else if(this.stage == Stage.Third){
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
            int i = Integer.parseInt(staticPoints3.getText().toString());
            i++;
            staticPoints3.setText(String.valueOf(i));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

        }
        else if(this.stage == Stage.Fourth){
            SysData.getInstance().getCurrentUser().setScore(SysData.getInstance().getCurrentUser().getScore() + 1);
            int i = Integer.parseInt(staticPoints4.getText().toString());
            i++;
            staticPoints4.setText(String.valueOf(i));
            staticTotalPoints.setText(String.valueOf(SysData.getInstance().getCurrentUser().getScore()));

        }
    }

    private void LoadQuestionPopUp(Square square) {
        SysData.getInstance().loadQuestions("src/JSON/QuestionsFormat.json");
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
        Difficulty d = Difficulty.randomDifficulty();
        Random rand1 = new Random();
        int int_rand1 = rand1.nextInt(SysData.getInstance().getQuestions().get(d).size());
        Question q = SysData.getInstance().getQuestions().get(d).get(int_rand1);
        square = new QuestionSquare(square.getX(), square.getY(),q);
        PopUpQuestionController.staticQuestion.setText(q.getText());
        PopUpQuestionController.staticDifficulty.setText(q.getLevel().toString());
        PopUpQuestionController.staticAnswer1.setText(q.getAnswer1());
        PopUpQuestionController.staticAnswer2.setText(q.getAnswer2());
        PopUpQuestionController.staticAnswer3.setText(q.getAnswer3());
        PopUpQuestionController.staticAnswer4.setText(q.getAnswer4());


        if(q.getLevel() == Difficulty.EASY){
            PopUpQuestionController.staticPoints.setText("1");

        }
        if(q.getLevel() == Difficulty.MEDIUM){
            PopUpQuestionController.staticPoints.setText("2");

        }
        if(q.getLevel() == Difficulty.HARD){
            PopUpQuestionController.staticPoints.setText("3");

        }
    }

    private void killPiece(Square square) {
        Color color1 = Color.web("black");

        if(!currentPiece.possibleMoves.contains(square.name)) return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if(killedPiece.type.equals("King") || killedPiece.type.equals("Queen")) this.game = false;

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;

        deselectPiece(true);

        javafx.stage.Stage stage4 = (javafx.stage.Stage) Window.getWindows().get(0).getScene().getWindow();
        stage4.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/LoserScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("FXML");
            alert1.setHeaderText("Load failure");
            alert1.setContentText("Failed to load the FXML file.");
            alert1.showAndWait();
        }
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", currentuser=" + currentuser.getUsername() +
                ", total Score=" + staticTotalPoints.getText() +
                '}';
    }
}

