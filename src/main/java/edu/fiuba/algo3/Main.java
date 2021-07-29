package edu.fiuba.algo3;

        import javafx.application.Application;

        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.ComboBox;
        import javafx.scene.layout.*;
        import javafx.scene.shape.Box;
        import javafx.scene.text.*;
        import javafx.event.EventHandler;
        import javafx.scene.control.TextField;
        import javafx.scene.control.Button;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;
        import javafx.stage.Stage;

        import java.util.Stack;

        import static javafx.scene.layout.BorderStroke.*;


public class Main extends Application {

    private void setArch(Rectangle rectangle, double height, double width){
        rectangle.setArcHeight(height);
        rectangle.setArcWidth(width);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        StackPane canvas = new StackPane();
        canvas.setStyle("-fx-background-color: rgb(242,204,133)");

        HBox nameBox = new HBox(100);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPrefSize(200,50);

        Text name = new Text();
        //name.setTextAlignment(TextAlignment.RIGHT);

        Font font = new Font("verdana", 25);
        name.setText("Player1");
        name.setFont(font);


        nameBox.getChildren().addAll(name);

        HBox dataBox = new HBox();
        //dataBox.setAlignment(Pos.CENTER);

        nameBox.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        Text information = new Text();

        information.setText("*Datos jugador 1 *");
        information.setFont(font);
        dataBox.getChildren().add(information);
        dataBox.setPrefSize(700,50);

        dataBox.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

        dataBox.setAlignment(Pos.CENTER);

        HBox firstHBox = new HBox();

        firstHBox.setMaxHeight(100);

        firstHBox.setAlignment(Pos.TOP_RIGHT);

        firstHBox.getChildren().addAll(nameBox,dataBox);



        VBox dataTurn = new VBox();
        dataTurn.setMaxWidth(200);
        dataTurn.setPrefHeight(500);
        dataTurn.setAlignment(Pos.CENTER);
        Text textAttack = new Text();
        textAttack.setText("Ataca desde:");
        textAttack.setFont(font);
        ComboBox countries = new ComboBox();


        //Set text of the ComboBox
        Text attackCountry = new Text();
        textAttack.setText("pais atacante:");
        textAttack.setFont(font);


        Text choice1 = new Text();
        choice1.setText("choice1");
        choice1.setFont(font);

        Text choice2 = new Text();
        choice2.setText("choice2");
        choice2.setFont(font);

        Text choice3 = new Text();
        choice3.setText("choice3");
        choice3.setFont(font);

       // countries.setPromptText(String.valueOf(attackCountry));

        countries.getItems().add(choice1);
        countries.getItems().add(choice2);
        countries.getItems().add(choice3);

        Text attacked = new Text();
        attacked.setText("Hacia:");
        attacked.setFont(font);

        ComboBox availableCountries = new ComboBox();

        //availableCountries.setPromptText("Elige un pais para atacar");

        availableCountries.getItems().add(choice1);
        availableCountries.getItems().add(choice2);
        availableCountries.getItems().add(choice3);

        dataTurn.getChildren().addAll(textAttack, countries, attacked, availableCountries);

        dataTurn.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: red;");


        HBox map = new HBox();

        map.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: red;");

        Text mapText = new Text();
        mapText.setText("* MAPA *");
        mapText.setFont(font);

        map.setAlignment(Pos.CENTER);
        map.setPrefWidth(700);
        map.setPrefHeight(500);
        map.getChildren().add(mapText);

        HBox secondHBox = new HBox();

        secondHBox.getChildren().addAll(dataTurn, map);

        VBox mainBox = new VBox(20);

        mainBox.getChildren().addAll(firstHBox, secondHBox);

        canvas.getChildren().add(mainBox);



        canvas.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(canvas, 900, 500);

        primaryStage.setScene(scene);

        primaryStage.setTitle("T.E.G.");
        primaryStage.setResizable(false);

        primaryStage.show();





    }
    public static void main(String[] args) {
        launch(args);
    }
}