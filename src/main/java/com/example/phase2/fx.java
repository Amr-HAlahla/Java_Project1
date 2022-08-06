package com.example.phase2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class fx extends Application {

    public static MediaRentalManager manager = new MediaRentalManager();

    Stage window;
    Scene primaryScene;
    Button customerButton;
    Button mediaButton;
    Button rentButton;

    Scene customerScene, addCustomerScene, deleteCustomerScene, updateCustomerScene, searchScene;
    Scene mediaScene, addMediaScene, gameScene, albumScene, movieScene, deleteMediaScene;
    Scene updateMediaScene, searchMediaScene, printAllMediaScene;
    Scene updateGameScene, updateAlbumScene, updateMovieScene;
    Scene rentScene, addToCartScene, printRequestScene, printRentedScene, returnRentedScene;

    public static int indexTemp = -1;

    @Override
    public void start(Stage stage) {
        customerSceneDemo();
        deleteCustomerScene();
        addCustomerScene();
        updateCustomerScene();
        searchScene();
        mediaSceneDemo();
        setAddMediaScene();
        addGameScene();
        addMovieScene();
        addAlbumScene();
        deleteMediaScene();
        setUpdateMediaScene();
        setSearchMediaScene();
        printAllMediaScene();
        setUpdateGameScene();
        setUpdateMediaScene();
        setUpdateAlbumScene();
        setUpdateMovieScene();
        setRentScene();
        setAddToCartScene();
        setPrintRequestScene();
        setPrintRentedScene();
        setReturnRentedScene();

        window = stage;
        window.setTitle("Final Project- Phase II");
        window.setMaximized(true);

        //button
        customerButton = new Button("Customer");
        customerButton.setFont(Font.font(20));
        ImageView customerView = new ImageView(new Image("Customer.jpg"));
        customerView.setFitHeight(100);
        customerView.setFitWidth(100);
        customerButton.setGraphic(customerView);
        customerButton.setStyle("-fx-background-color: white;");

        mediaButton = new Button("Media");
        mediaButton.setFont(Font.font(20));
        ImageView mediaView = new ImageView(new Image("mediaIcon.png"));
        mediaView.setFitHeight(100);
        mediaView.setFitWidth(100);
        mediaButton.setGraphic(mediaView);
        mediaButton.setStyle("-fx-background-color: white;");

        rentButton = new Button("Rent");
        rentButton.setFont(Font.font(22));
        ImageView rentView = new ImageView(new Image("rentIcon.png"));
        rentView.setFitHeight(100);
        rentView.setFitWidth(100);
        rentButton.setGraphic(rentView);
        rentButton.setStyle("-fx-background-color: white;");


        customerButton.setMaxSize(300, 300);
        mediaButton.setMaxSize(300, 300);
        rentButton.setMaxSize(300, 300);

        //button clicked
        customerButton.setOnAction(e -> window.setScene(customerScene));
        mediaButton.setOnAction(e -> window.setScene(mediaScene));
        rentButton.setOnAction(e -> window.setScene(rentScene));

        //Image
        ImageView interfaceView = new ImageView("Logo.png");
        interfaceView.setFitHeight(500);
        interfaceView.setFitWidth(720);

        VBox buttonBox = new VBox(50);
        buttonBox.setPadding(new Insets(180, 40, 40, 40));
        buttonBox.getChildren().addAll(customerButton, mediaButton, rentButton);
        HBox hBox = new HBox(160);
        hBox.getChildren().addAll(buttonBox, interfaceView);
        hBox.setAlignment(Pos.CENTER);


        primaryScene = new Scene(hBox, 1600, 900);
        hBox.setBackground(new Background(
                new BackgroundImage(
                        new Image("https://coolbackgrounds.io/images/backgrounds/index/compute-ea4c57a4.png"),
                        //https://wallpaperboat.com/wp-content/uploads/2019/10/best-background-for-website-01.jpg
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, false, Side.BOTTOM, 0, false),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
                )));
        //hBox.setStyle("-fx-background-color: #0e0505;");
        window.setScene(primaryScene);
        window.show();

    }

    private void setRentScene() {
        //buttons
        Button addToCart = new Button("Add To Cart");
        Button printRequest = new Button("Print Requested Media");
        Button printRented = new Button("Print Rented Media");
        Button returnRented = new Button("Return Rented Media");
        Button back = new Button("Return to Main Page");
        addToCart.setPrefSize(300, 70);
        printRequest.setPrefSize(300, 70);
        returnRented.setPrefSize(300, 70);
        printRented.setPrefSize(300, 70);
        back.setPrefSize(300, 70);
        addToCart.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        printRequest.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        printRented.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        returnRented.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        back.setGraphic(new ImageView(new Image("Return.png")));

        //button functionality
        addToCart.setOnAction(e -> window.setScene(addToCartScene));
        printRequest.setOnAction(e -> window.setScene(printRequestScene));
        printRented.setOnAction(e -> window.setScene(printRentedScene));
        returnRented.setOnAction(e -> window.setScene(returnRentedScene));
        back.setOnAction(e -> window.setScene(primaryScene));

        //layout
        VBox rentPane = new VBox(45);
        rentPane.getChildren().addAll(addToCart, printRequest, printRented, returnRented, back);
        rentPane.setAlignment(Pos.CENTER);

        rentPane.setStyle("-fx-background-color: #00ff80;");
        rentScene = new Scene(rentPane, 1600, 900);

    }

    private void setAddToCartScene() {

        Label idLabel = new Label("Customer ID");
        idLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 25));
        idLabel.setStyle("-fx-background-color: gold;");

        Label codeLabel = new Label("Media Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 25));
        codeLabel.setStyle("-fx-background-color: gold;");

        Label dateLabel = new Label("Rented Date");
        dateLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 25));
        dateLabel.setStyle("-fx-background-color: gold;");

        TextField idText = new TextField();
        idText.setPromptText("ID");

        TextField codeText = new TextField();
        codeText.setPromptText("Code");

        TextField dateText = new TextField();
        // codeText.setPromptText("Date");
        dateText.setText(new Date().toString());


        TextArea customerArea = new TextArea();
        customerArea.setMaxWidth(700);
        customerArea.setMaxHeight(200);
        customerArea.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 15));
        TextArea mediaArea = new TextArea();
        mediaArea.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 15));
        mediaArea.setMaxWidth(700);
        mediaArea.setMaxHeight(200);

        //button
        Button addToCart = new Button("Add To Cart");
        addToCart.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        Button processCart = new Button("Process Cart");
        processCart.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        Button back = new Button("Back");
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitHeight(50);
        backView.setFitWidth(50);

        ImageView addView = new ImageView(new Image("addToCart.png"));
        addView.setFitHeight(50);
        addView.setFitWidth(50);

        ImageView processView = new ImageView(new Image("processCart.png"));
        processView.setFitHeight(50);
        processView.setFitWidth(50);

        back.setGraphic(backView);
        addToCart.setGraphic(addView);
        processCart.setGraphic(processView);
        back.setOnAction(e -> window.setScene(rentScene));

        GridPane addToCartPane = new GridPane();
        addToCartPane.add(idLabel, 0, 0);
        addToCartPane.add(idText, 1, 0);
        addToCartPane.add(customerArea, 1, 1);
        addToCartPane.add(codeLabel, 0, 2);
        addToCartPane.add(codeText, 1, 2);
        addToCartPane.add(mediaArea, 1, 3);
        addToCartPane.add(dateLabel, 0, 4);
        addToCartPane.add(dateText, 1, 4);
        addToCartPane.add(addToCart, 0, 7);
        addToCartPane.add(processCart, 1, 7);
        addToCartPane.add(back, 3, 7);
        addToCartPane.setVgap(12);
        addToCartPane.setHgap(20);
        addToCartPane.setAlignment(Pos.CENTER);

        addToCart.setOnAction(e -> {


            if (manager.addToCart(idText.getText(), codeText.getText())) {
                for (int i = 0; i < manager.customerDatabase.size(); i++) {
                    if (manager.customerDatabase.get(i).getId().equals(idText.getText())) {
                        customerArea.setText(manager.customerDatabase.get(i).toString());
                        try {
                            File customerFile = new File("Customer.txt");
                            PrintWriter printer = new PrintWriter("Customer.txt");
                            printer.println(manager.customerDatabase.get(i).toString());
                            printer.close();
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }
                for (int i = 0; i < manager.mediaDatabase.size(); i++) {
                    if (manager.mediaDatabase.get(i).getCode().equals(codeText.getText())) {
                        mediaArea.setText(manager.mediaDatabase.get(i).toString());
                        try {
                            File mediaFile = new File("Media.txt");
                            PrintWriter printer = new PrintWriter("Media.txt");
                            printer.println(manager.mediaDatabase.get(i).toString());
                            printer.close();
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }

                    }
                }
            } else {
                customerArea.setText("Action Can't be Executed");
                mediaArea.setText("Action Can't be Executed");
            }
        });
        processCart.setOnAction(e -> {

            String process = manager.processRequests();
            File processFile = new File("Process.txt");
            try {
                PrintWriter printWriter = new PrintWriter(processFile);
                printWriter.println(process+dateText.getText());
                printWriter.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            customerArea.setText(process);
        });

        addToCartPane.setStyle("-fx-background-color: #CCFFCC;");
        addToCartScene = new Scene(addToCartPane, 1600, 900);

    }

    private void setPrintRequestScene() {

        Label printLabel = new Label("Print Request Media");
        printLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 40));
        printLabel.setStyle("-fx-background-color: gold;");


        Label idLabel = new Label("Customer ID");
        idLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        idLabel.setStyle("-fx-background-color: gold;");

        TextField idText = new TextField();
        idText.setPromptText("ID");

        TextArea printArea = new TextArea();
        printArea.setMaxWidth(700);
        printArea.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //button
        Button printRequest = new Button("Print Request");
        printRequest.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        Button back = new Button("Back");
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitHeight(50);
        backView.setFitWidth(50);

        ImageView printView = new ImageView(new Image("print.png"));
        printView.setFitHeight(50);
        printView.setFitWidth(50);


        back.setGraphic(backView);
        printRequest.setGraphic(printView);
        back.setOnAction(e -> window.setScene(rentScene));

        GridPane printRequestPane = new GridPane();
        printRequestPane.add(printLabel, 1, 0);
        printRequestPane.add(idLabel, 0, 2);
        printRequestPane.add(idText, 1, 2);
        printRequestPane.add(printArea, 1, 3);
        printRequestPane.add(printRequest, 1, 4);
        printRequestPane.add(back, 2, 4);
        printRequestPane.setHgap(10);
        printRequestPane.setVgap(25);
        printRequestPane.setAlignment(Pos.CENTER);

        printRequest.setOnAction(e -> {

            String result = "";

            for (int i = 0; i < manager.customerDatabase.size(); i++) {
                if (manager.customerDatabase.get(i).getId().equals(idText.getText()))
                    for (int j = 0; j < manager.customerDatabase.get(i).requests.size(); j++)
                        result += manager.customerDatabase.get(i).requests + "\n";
            }
            printArea.setText(result);
        });

        printRequestPane.setStyle("-fx-background-color: #CCFFCC;");
        printRequestScene = new Scene(printRequestPane, 1600, 900);

    }

    private void setPrintRentedScene() {

        Label printLabel = new Label("Print Rented Media");
        printLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 40));
        printLabel.setStyle("-fx-background-color: gold;");


        Label idLabel = new Label("Customer ID");
        idLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        idLabel.setStyle("-fx-background-color: gold;");

        TextField idText = new TextField();
        idText.setPromptText("ID");

        TextArea printArea = new TextArea();
        printArea.setMaxWidth(700);
        printArea.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //button
        Button printRented = new Button("Print Rented");
        printRented.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        Button back = new Button("Back");
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitHeight(50);
        backView.setFitWidth(50);

        ImageView printView = new ImageView(new Image("print.png"));
        printView.setFitHeight(50);
        printView.setFitWidth(50);


        back.setGraphic(backView);
        printRented.setGraphic(printView);
        back.setOnAction(e -> window.setScene(rentScene));

        GridPane printRentedPane = new GridPane();
        printRentedPane.add(printLabel, 1, 0);
        printRentedPane.add(idLabel, 0, 2);
        printRentedPane.add(idText, 1, 2);
        printRentedPane.add(printArea, 1, 3);
        printRentedPane.add(printRented, 1, 4);
        printRentedPane.add(back, 2, 4);
        printRentedPane.setHgap(10);
        printRentedPane.setVgap(25);
        printRentedPane.setAlignment(Pos.CENTER);

        printRented.setOnAction(e -> {

            String result = "";

            for (int i = 0; i < manager.customerDatabase.size(); i++) {
                if (manager.customerDatabase.get(i).getId().equals(idText.getText()))
                    for (int j = 0; j < manager.customerDatabase.get(i).rented.size(); j++)
                        result += manager.customerDatabase.get(i).rented.get(j)+"\n";
            }
            printArea.setText(result);
        });

        printRentedPane.setStyle("-fx-background-color: #CCFFCC;");
        printRentedScene = new Scene(printRentedPane, 1600, 900);

    }

    private void setReturnRentedScene() {

        Label returnLabel = new Label("Return Rented Media");
        returnLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 40));
        returnLabel.setStyle("-fx-background-color: gold;");


        Label idLabel = new Label("Customer ID");
        idLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        idLabel.setStyle("-fx-background-color: gold;");

        Label codeLabel = new Label("Media Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        codeLabel.setStyle("-fx-background-color: gold;");


        TextField idText = new TextField();
        idText.setPromptText("ID");
        TextField codeText = new TextField();
        codeText.setPromptText("Code");

        TextArea printArea = new TextArea();
        printArea.setMaxWidth(700);
        printArea.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //button
        Button returnRented = new Button("Return Rented");
        returnRented.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        Button back = new Button("Back");
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitHeight(50);
        backView.setFitWidth(50);

        ImageView returnView = new ImageView(new Image("Return.png"));
        returnView.setFitHeight(50);
        returnView.setFitWidth(50);


        back.setGraphic(backView);
        returnRented.setGraphic(returnView);
        back.setOnAction(e -> window.setScene(rentScene));

        GridPane returnRentedPane = new GridPane();
        returnRentedPane.add(returnLabel, 1, 0);
        returnRentedPane.add(idLabel, 0, 1);
        returnRentedPane.add(codeLabel, 0, 2);
        returnRentedPane.add(idText, 1, 1);
        returnRentedPane.add(codeText, 1, 2);
        returnRentedPane.add(printArea, 1, 3);
        returnRentedPane.add(returnRented, 1, 4);
        returnRentedPane.add(back, 2, 4);
        returnRentedPane.setHgap(10);
        returnRentedPane.setVgap(25);
        returnRentedPane.setAlignment(Pos.CENTER);

        returnRented.setOnAction(e -> {

            if (manager.returnMedia(idText.getText(), codeText.getText()))
                printArea.setText("Return Done Successfully!");
            else
                printArea.setText("Return Failed!");
        });
        returnRentedPane.setStyle("-fx-background-color: #CCFFCC;");
        returnRentedScene = new Scene(returnRentedPane, 1600, 900);

    }

    private void printAllMediaScene() {

        Label printLabel = new Label("Print All Media Info");
        printLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        printLabel.setStyle("-fx-background-color: gold;");

        Button print = new Button("Print");
        ImageView printView = new ImageView(new Image("print.png"));
        printView.setFitWidth(50);
        printView.setFitHeight(50);
        print.setGraphic(printView);
        print.setFont(Font.font("Times New Roman", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));

        Button back = new Button("Back");
        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitHeight(50);
        backView.setFitWidth(50);
        back.setGraphic(backView);
        back.setFont(Font.font("Times New Roman", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        TextArea printArea = new TextArea();
        printArea.setMaxWidth(600);
        printArea.setFont(Font.font("Impact", FontWeight.LIGHT, 20));
        printArea.setDisable(false);

        back.setOnAction(e -> window.setScene(mediaScene));

        //print clicked

        String mediaInfo = manager.getAllMediaInfo();
        print.setOnAction(e -> printArea.appendText(mediaInfo));

        HBox buttonBox = new HBox(25);
        buttonBox.getChildren().addAll(print, back);
        buttonBox.setAlignment(Pos.CENTER);

        VBox printPane = new VBox(40);
        printPane.getChildren().addAll(printLabel, printArea, buttonBox);
        printPane.setAlignment(Pos.CENTER);

        BorderPane printMediaPane = new BorderPane();
        printMediaPane.setCenter(printPane);

        print.setOnAction(e -> {
            printArea.setText(manager.getAllMediaInfo());
        });

        printMediaPane.setStyle("-fx-background-color: #CCCCFF;");
        printAllMediaScene = new Scene(printMediaPane, 1600, 900);


    }

    private void setSearchMediaScene() {

        //labels
        Label searchLabel = new Label("Search Media");
        searchLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 35));
        searchLabel.setStyle("-fx-background-color: gold;");
        searchLabel.setMinSize(100, 50);
        Label codeLabel = new Label("Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 35));
        codeLabel.setStyle("-fx-background-color: gold;");
        codeLabel.setMinSize(100, 50);

        //text fields
        TextField code = new TextField();
        code.setPromptText("Media Code");
        code.setStyle("-fx-background-color: grey;");
        code.setMinSize(100, 50);

        HBox hBox1 = new HBox(20);
        hBox1.getChildren().addAll(codeLabel, code);
        hBox1.setAlignment(Pos.CENTER);

        //button
        Button find = new Button("Find");
        Button backButton = new Button("Back");

        //button functionality
        backButton.setOnAction(e -> window.setScene(mediaScene));

        //image
        ImageView searchView = new ImageView(new Image("find.png"));
        ImageView backView = new ImageView(new Image("return.jpg"));

        searchView.setFitHeight(60);
        searchView.setFitWidth(60);
        backView.setFitHeight(60);
        backView.setFitWidth(60);

        find.setGraphic(searchView);
        backButton.setGraphic(backView);

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(find, backButton);
        hBox2.setAlignment(Pos.CENTER);

        TextArea searchArea = new TextArea();
        searchArea.setMaxWidth(650);
        searchArea.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));

        //layout
        VBox searchMediaPane = new VBox(40);
        searchMediaPane.getChildren().addAll(searchLabel, hBox1, searchArea, hBox2);
        searchMediaPane.setAlignment(Pos.CENTER);

        find.setOnAction(e -> {

            for (int i = 0; i < manager.mediaDatabase.size(); i++) {
                if (manager.mediaDatabase.get(i).getCode().equals(code.getText()))
                    searchArea.setText(manager.mediaDatabase.get(i).toString());
            }
            code.clear();
        });

        searchMediaPane.setStyle("-fx-background-color: #CCCCFF;");
        searchMediaScene = new Scene(searchMediaPane, 1600, 900);

    }

    private void mediaSceneDemo() {

        //buttons
        Button add = new Button("Add New Media");
        Button delete = new Button("Delete Media");
        Button update = new Button("Update Information About Media");
        Button search = new Button("Search a Media by Code");
        Button printAll = new Button("Print All Media Information");
        Button back = new Button("Return to Main Page");
        add.setPrefSize(300, 70);
        delete.setPrefSize(300, 70);
        search.setPrefSize(300, 70);
        printAll.setPrefSize(300, 70);
        update.setPrefSize(300, 70);
        back.setPrefSize(300, 70);
        add.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        delete.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        update.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        search.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        printAll.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        back.setGraphic(new ImageView(new Image("Return.png")));

        //button functionality
        add.setOnAction(e -> window.setScene(addMediaScene));
        delete.setOnAction(e -> window.setScene(deleteMediaScene));
        printAll.setOnAction(e -> window.setScene(printAllMediaScene));
        update.setOnAction(e -> window.setScene(updateMediaScene));
        search.setOnAction(e -> window.setScene(searchMediaScene));
        back.setOnAction(e -> window.setScene(primaryScene));

        //layout
        VBox mediaPane = new VBox(45);
        mediaPane.getChildren().addAll(add, delete, update, search, printAll, back);
        mediaPane.setAlignment(Pos.CENTER);
        mediaPane.setStyle("-fx-background-color: #9933ff;");
        mediaScene = new Scene(mediaPane, 1600, 900);


    }


    private void setUpdateMediaScene() {

        Label label = new Label("This scene will take you to the appropriate and correct update " +
                "media scene based on the code");
        label.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 30));
        label.setStyle("-fx-background-color: gold;");

        Label codeLabel = new Label("Media Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 20));
        codeLabel.setStyle("-fx-background-color: gold;");

        TextField codeText = new TextField();
        codeText.setPromptText("Media Code");
        codeText.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 12));

        //button
        Button backButton = new Button("Back");
        Button findButton = new Button("Find");

        //Button functionality
        backButton.setOnAction(e -> window.setScene(mediaScene));

        //Image
        ImageView backV = new ImageView(new Image("back.png"));
        backButton.setGraphic(backV);
        backV.setFitHeight(100);
        backV.setFitWidth(100);

        ImageView findV = new ImageView(new Image("find.png"));
        findButton.setGraphic(findV);
        findV.setFitHeight(100);
        findV.setFitWidth(100);
        findButton.setOnAction(e -> {
            for (int i = 0; i < manager.mediaDatabase.size(); i++) {
                if (manager.mediaDatabase.get(i).getCode().equals(codeText.getText())) {
                    indexTemp = i;
                }
            }
            try {
                Media media = manager.mediaDatabase.get(indexTemp);
                if (media instanceof Movie)
                    window.setScene(updateMovieScene);
                else if (media instanceof Game)
                    window.setScene(updateGameScene);
                else if (media instanceof Album)
                    window.setScene(updateAlbumScene);

            } catch (Exception exception) {
            }


            codeText.clear();
        });

        HBox hBox1 = new HBox(15);
        hBox1.getChildren().addAll(codeLabel, codeText);
        hBox1.setAlignment(Pos.CENTER);

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(findButton, backButton);
        hBox2.setAlignment(Pos.CENTER);

        VBox mediaScenePane = new VBox(20);
        mediaScenePane.getChildren().addAll(label, hBox1, hBox2);
        mediaScenePane.setAlignment(Pos.CENTER);

        mediaScenePane.setStyle("-fx-background-color: #CCCCFF;");
        updateMediaScene = new Scene(mediaScenePane, 1600, 900);

    }

    // update game
    private void setUpdateGameScene() {

        Label newLabel = new Label("New Information");
        newLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        newLabel.setStyle("-fx-background-color: gold;");

        Label titleLabel = new Label("Title");
        titleLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        titleLabel.setStyle("-fx-background-color: gold;");
        Label copiesLabel = new Label("Copies Available");
        copiesLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesLabel.setStyle("-fx-background-color: gold;");
        Label weightLabel = new Label("Weight");
        weightLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        weightLabel.setStyle("-fx-background-color: gold;");

        TextField newTitle = new TextField();
        newTitle.setPromptText("New Title");
        newTitle.setMaxWidth(400);
        TextField newCopies = new TextField();
        newCopies.setPromptText("New Copies Available");
        newCopies.setMaxWidth(400);
        TextField newWeight = new TextField();
        newWeight.setPromptText("New Weight");
        newWeight.setMaxWidth(400);

        //button
        Button update = new Button("Update");
        Button back = new Button("Back");

        ImageView updateView = new ImageView(new Image("update.png"));
        updateView.setFitWidth(50);
        updateView.setFitHeight(50);
        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitWidth(50);
        backView.setFitHeight(50);
        update.setGraphic(updateView);
        back.setGraphic(backView);

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(update, back);
        hBox.setAlignment(Pos.CENTER);

        back.setOnAction(e -> window.setScene(updateMediaScene));


        VBox updateGamePane = new VBox(30);
        updateGamePane.getChildren().addAll(newLabel, newTitle, newCopies, newWeight, hBox);
        updateGamePane.setAlignment(Pos.CENTER);


        update.setOnAction(e -> {
            if (indexTemp != -1) {
                Game game = (Game) manager.mediaDatabase.get(indexTemp);
                if (newTitle.getLength() > 0)
                    game.setTitle(newTitle.getText());
                if (newCopies.getLength() > 0)
                    game.setCopiesAvailable(Integer.parseInt(newCopies.getText()));
                if (newWeight.getLength() > 0)
                    game.setWeight(Double.parseDouble(newWeight.getText()));
            }
            newTitle.clear();
            newCopies.clear();
            newWeight.clear();
        });

        updateGamePane.setStyle("-fx-background-color: #CCCCFF;");
        updateGameScene = new Scene(updateGamePane, 1600, 900);

    }

    private void setUpdateAlbumScene() {

        Label newLabel = new Label("New Information");
        newLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        newLabel.setStyle("-fx-background-color: gold;");

        Label titleLabel = new Label("Title");
        titleLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        titleLabel.setStyle("-fx-background-color: gold;");
        Label copiesLabel = new Label("Copies Available");
        copiesLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesLabel.setStyle("-fx-background-color: gold;");
        Label artistLabel = new Label("Artist");
        artistLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        artistLabel.setStyle("-fx-background-color: gold;");
        Label songLabel = new Label("Songs");
        songLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        songLabel.setStyle("-fx-background-color: gold;");

        TextField newTitle = new TextField();
        newTitle.setPromptText("New Title");
        newTitle.setMaxWidth(400);
        TextField newCopies = new TextField();
        newCopies.setPromptText("New Copies Available");
        newCopies.setMaxWidth(400);
        TextField newArtist = new TextField();
        newArtist.setPromptText("New Artist");
        newArtist.setMaxWidth(400);
        TextField newSongs = new TextField();
        newSongs.setPromptText("New Songs");
        newSongs.setMaxWidth(400);

        //button
        Button update = new Button("Update");
        Button back = new Button("Back");

        ImageView updateView = new ImageView(new Image("update.png"));
        updateView.setFitWidth(50);
        updateView.setFitHeight(50);
        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitWidth(50);
        backView.setFitHeight(50);
        update.setGraphic(updateView);
        back.setGraphic(backView);

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(update, back);
        hBox.setAlignment(Pos.CENTER);

        back.setOnAction(e -> window.setScene(updateMediaScene));


        VBox updateAlbumPane = new VBox(30);
        updateAlbumPane.getChildren().addAll(newLabel, newTitle, newCopies, newArtist, newSongs, hBox);
        updateAlbumPane.setAlignment(Pos.CENTER);


        update.setOnAction(e -> {
            if (indexTemp != -1) {
                Album album = (Album) manager.mediaDatabase.get(indexTemp);
                if (newTitle.getLength() > 0)
                    album.setTitle(newTitle.getText());
                if (newCopies.getLength() > 0)
                    album.setCopiesAvailable(Integer.parseInt(newCopies.getText()));
                if (newArtist.getLength() > 0)
                    album.setArtist(newArtist.getText());
                if (newSongs.getLength() > 0)
                    album.setSongs(newSongs.getText());
            }
            newTitle.clear();
            newCopies.clear();
            newArtist.clear();
            newSongs.clear();
        });

        updateAlbumPane.setStyle("-fx-background-color: #CCCCFF;");
        updateAlbumScene = new Scene(updateAlbumPane, 1600, 900);
    }

    private void setUpdateMovieScene() {

        Label newLabel = new Label("New Information");
        newLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 20));
        newLabel.setStyle("-fx-background-color: gold;");

        Label titleLabel = new Label("Title");
        titleLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        titleLabel.setStyle("-fx-background-color: gold;");
        Label copiesLabel = new Label("Copies Available");
        copiesLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesLabel.setStyle("-fx-background-color: gold;");

        TextField newTitle = new TextField();
        newTitle.setPromptText("New Title");
        newTitle.setMaxWidth(400);
        TextField newCopies = new TextField();
        newCopies.setPromptText("New Copies Available");
        newCopies.setMaxWidth(400);

        //button
        Button update = new Button("Update");
        Button back = new Button("Back");

        ImageView updateView = new ImageView(new Image("update.png"));
        updateView.setFitWidth(50);
        updateView.setFitHeight(50);
        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitWidth(50);
        backView.setFitHeight(50);
        update.setGraphic(updateView);
        back.setGraphic(backView);

        //Rating
        CheckBox AC = new CheckBox("AC");
        CheckBox DR = new CheckBox("DR");
        CheckBox HR = new CheckBox("HR");
        AC.setFont(Font.font("Aldhabi", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        DR.setFont(Font.font("Aldhabi", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        HR.setFont(Font.font("Aldhabi", FontWeight.LIGHT, FontPosture.REGULAR, 18));

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(update, back);
        hBox.setAlignment(Pos.CENTER);

        HBox hBox2 = new HBox(12);
        hBox2.getChildren().addAll(AC, DR, HR);
        hBox2.setAlignment(Pos.CENTER);

        back.setOnAction(e -> window.setScene(updateMediaScene));


        VBox updateMoviePane = new VBox(30);
        updateMoviePane.getChildren().addAll(newLabel, newTitle, newCopies, hBox2, hBox);
        updateMoviePane.setAlignment(Pos.CENTER);


        update.setOnAction(e -> {

            String rating="";
            if (AC.isSelected())
                rating = "AC";
            else if (DR.isSelected())
                rating = "DR";
            else if (HR.isSelected())
                rating = "HR";
            else
                rating = "";


            if (indexTemp != -1 ) {
                Movie game = (Movie) manager.mediaDatabase.get(indexTemp);
                if (newTitle.getLength() > 0)
                    game.setTitle(newTitle.getText());
                if (newCopies.getLength() > 0)
                    game.setCopiesAvailable(Integer.parseInt(newCopies.getText()));
                if (rating.length() > 0)
                    game.setRating(rating);
            }
            newTitle.clear();
            newCopies.clear();
            HR.setSelected(false);
            DR.setSelected(false);
            AC.setSelected(false);

        });

        updateMoviePane.setStyle("-fx-background-color: #CCCCFF;");
        updateMovieScene = new Scene(updateMoviePane, 1600, 900);

    }

    private void deleteMediaScene() {

        //labels
        Label codeLabel = new Label("Media Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        codeLabel.setStyle("-fx-background-color: gold;");
        Label typeLabel = new Label("Media Type");
        typeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        typeLabel.setStyle("-fx-background-color: gold;");
        Label nameLabel = new Label("Media Title");
        nameLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        nameLabel.setStyle("-fx-background-color: gold;");
        Label copiesLabel = new Label("Copies Available");
        copiesLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesLabel.setStyle("-fx-background-color: gold;");

        //text fields
        TextField code = new TextField();
        code.setPromptText("Code");
        TextField type = new TextField();
        type.setPromptText("Type");
        TextField name = new TextField();
        name.setPromptText("Title");
        TextField copies = new TextField();
        copies.setPromptText("Copies");

        //Button
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        //button functionality
        back.setOnAction(e -> window.setScene(mediaScene));

        //image
        ImageView deleteV = new ImageView(new Image("delete.png"));
        ImageView backV = new ImageView(new Image("back.png"));


        deleteV.setFitHeight(55);
        deleteV.setFitWidth(55);
        backV.setFitHeight(55);
        backV.setFitWidth(55);

        back.setGraphic(backV);
        delete.setGraphic(deleteV);
        HBox hBox = new HBox(90);
        hBox.getChildren().addAll(delete, back);
        hBox.setAlignment(Pos.CENTER);

        //layout
        GridPane deleteMediaPane = new GridPane();
        deleteMediaPane.setVgap(40);
        deleteMediaPane.addRow(0, codeLabel, code);
        deleteMediaPane.addRow(1, typeLabel, type);
        deleteMediaPane.addRow(2, nameLabel, name);
        deleteMediaPane.addRow(3, copiesLabel, copies);
        deleteMediaPane.add(hBox, 1, 4);
        deleteMediaPane.setHgap(20);
        deleteMediaPane.setAlignment(Pos.CENTER);

        delete.setOnAction(e -> {
            manager.deleteMedia(code.getText());
            code.clear();
        });

        deleteMediaPane.setStyle("-fx-background-color: #CCCCFF;");
        deleteMediaScene = new Scene(deleteMediaPane, 1600, 900);

    }

    private void setAddMediaScene() {

        //combo box
        ComboBox<String> mediaType = new ComboBox<>();
        mediaType.setPromptText("Choose Media type");
        mediaType.getItems().addAll("game", "movie", "album");
        mediaType.setOnAction(e -> {
            if (mediaType.getSelectionModel().getSelectedItem().equals("game")) {
                window.setScene(gameScene);
            } else if (mediaType.getSelectionModel().getSelectedItem().equals("album")) {
                window.setScene(albumScene);
            } else if (mediaType.getSelectionModel().getSelectedItem().equals("movie")) {
                window.setScene(movieScene);
            }
        });

        mediaType.setStyle("-fx-background-color: gold;");

        //button
        Button backButton = new Button("Back");

        //Button functionality
        backButton.setOnAction(e -> window.setScene(mediaScene));

        //Image
        ImageView backV = new ImageView(new Image("back.png"));
        backButton.setGraphic(backV);


        backV.setFitHeight(100);
        backV.setFitWidth(100);


        VBox mediaTypePane = new VBox(20);
        mediaTypePane.setMinSize(1920, 1080);
        mediaTypePane.getChildren().addAll(mediaType, backButton);
        mediaTypePane.setAlignment(Pos.CENTER);
        StackPane mediaScenePane = new StackPane();
        mediaScenePane.getChildren().add(mediaTypePane);

        mediaScenePane.setStyle("-fx-background-color: #CCCCFF;");
        addMediaScene = new Scene(mediaScenePane, 1600, 900);

    }

    private void addGameScene() {

        //labels
        Label codeLabel = new Label("Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        codeLabel.setStyle("-fx-background-color: gold;");
        Label copiesAvailable = new Label("Copies Available");
        copiesAvailable.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesAvailable.setStyle("-fx-background-color: gold;");
        Label nameLabel = new Label("Title");
        nameLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        nameLabel.setStyle("-fx-background-color: gold;");
        Label weightLabel = new Label("Weight");
        weightLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        weightLabel.setStyle("-fx-background-color: gold;");

        //text fields
        TextField code = new TextField();
        code.setPromptText("Media Code");
        TextField copies = new TextField();
        copies.setPromptText("Copies Available");
        copies.setDisable(true);
        TextField name = new TextField();
        name.setPromptText("Game Name");
        name.setDisable(true);
        TextField weight = new TextField();
        weight.setPromptText("Weight");
        weight.setDisable(true);

        code.setOnKeyTyped(e -> name.setDisable(false));
        name.setOnKeyTyped(e -> copies.setDisable(false));
        copies.setOnKeyTyped(e -> weight.setDisable(false));


        //button
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");

        //Button functionality
        backButton.setOnAction(e -> window.setScene(addMediaScene));

        //Image
        ImageView addV = new ImageView(new Image("pluss.png"));
        ImageView backV = new ImageView(new Image("back.png"));
        addButton.setGraphic(addV);
        backButton.setGraphic(backV);

        addV.setFitHeight(100);
        addV.setFitWidth(100);
        backV.setFitHeight(100);
        backV.setFitWidth(100);

        //layout
        GridPane addGameBox = new GridPane();
        addGameBox.addRow(0, codeLabel, code);
        addGameBox.addRow(1, nameLabel, name);
        addGameBox.addRow(2, copiesAvailable, copies);
        addGameBox.addRow(3, weightLabel, weight);
        addGameBox.setAlignment(Pos.CENTER);
        addGameBox.setVgap(40);
        addGameBox.setHgap(12);
        HBox bottomGameBox = new HBox();
        bottomGameBox.setSpacing(15);
        bottomGameBox.getChildren().addAll(addButton, backButton);
        bottomGameBox.setAlignment(Pos.CENTER);

        VBox addGamePane = new VBox(45);
        addGamePane.getChildren().addAll(addGameBox, bottomGameBox);
        addGamePane.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> {
            boolean flag = true;
            for (int i = 0; i < manager.mediaDatabase.size(); i++) {
                if (manager.mediaDatabase.get(i).getCode().equals(code.getText()))
                    flag = false;
            }

            try {
                if (flag) {
                    manager.addGame(code.getText(), name.getText(), Integer.parseInt(copies.getText()),
                            Double.parseDouble(weight.getText()));
                } else
                    throw new IllegalArgumentException("Code must be unique");
            } catch (Exception exception) {
            }

            code.clear();
            name.clear();
            copies.clear();
            weight.clear();
        });

        addGamePane.setStyle("-fx-background-color: #CCCCFF;");
        gameScene = new Scene(addGamePane, 1600, 900);

    }

    private void addMovieScene() {

        //labels
        Label codeLabel = new Label("Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        codeLabel.setStyle("-fx-background-color: gold;");
        Label copiesAvailable = new Label("Copies Available");
        copiesAvailable.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesAvailable.setStyle("-fx-background-color: gold;");
        Label nameLabel = new Label("Title");
        nameLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        nameLabel.setStyle("-fx-background-color: gold;");

        //text fields
        TextField code = new TextField();
        code.setPromptText("Media Code");
        TextField copies = new TextField();
        copies.setPromptText("Copies Available");
        copies.setDisable(true);
        TextField name = new TextField();
        name.setPromptText("Name");
        name.setDisable(true);

        CheckBox AC = new CheckBox("AC");
        CheckBox DR = new CheckBox("DR");
        CheckBox HR = new CheckBox("HR");
        AC.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        DR.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.REGULAR, 18));
        HR.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.REGULAR, 18));


        code.setOnKeyTyped(e -> name.setDisable(false));
        name.setOnKeyTyped(e -> copies.setDisable(false));


        //button
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");

        //Button functionality
        backButton.setOnAction(e -> window.setScene(addMediaScene));

        addButton.setDisable(true);
        AC.setOnAction(e -> addButton.setDisable(false));
        HR.setOnAction(e -> addButton.setDisable(false));
        DR.setOnAction(e -> addButton.setDisable(false));

        //Image
        ImageView addV = new ImageView(new Image("pluss.png"));
        ImageView backV = new ImageView(new Image("back.png"));
        addButton.setGraphic(addV);
        backButton.setGraphic(backV);

        addV.setFitHeight(100);
        addV.setFitWidth(100);
        backV.setFitHeight(100);
        backV.setFitWidth(100);

        //layout
        GridPane addMovieGridPane = new GridPane();
        addMovieGridPane.addRow(0, codeLabel, code);
        addMovieGridPane.addRow(1, nameLabel, name);
        addMovieGridPane.addRow(2, copiesAvailable, copies);
        addMovieGridPane.addRow(4, AC, DR, HR);
        addMovieGridPane.setAlignment(Pos.CENTER);
        addMovieGridPane.setVgap(37);
        addMovieGridPane.setHgap(17);

        HBox bottomMovieBox = new HBox();
        bottomMovieBox.setSpacing(15);
        bottomMovieBox.getChildren().addAll(addButton, backButton);
        bottomMovieBox.setAlignment(Pos.CENTER);

        VBox addMoviePane = new VBox(45);
        addMoviePane.getChildren().addAll(addMovieGridPane, bottomMovieBox);
        addMoviePane.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> {

            String value = null;
            if (AC.isSelected())
                value = "AC";
            else if (DR.isSelected())
                value = "DR";
            else if (HR.isSelected())
                value = "HR";

            boolean flag = true;
            for (int i = 0; i < manager.mediaDatabase.size(); i++) {
                if (manager.mediaDatabase.get(i).getCode().equals(code.getText()))
                    flag = false;
            }

            if (value != null) {
                try {
                    if (flag) {
                        manager.addMovie(code.getText(), name.getText(), Integer.parseInt(copies.getText()), value);
                    } else
                        throw new IllegalArgumentException("Code must be unique");
                } catch (Exception exception) {
                }

                AC.setSelected(false);
                HR.setSelected(false);
                DR.setSelected(false);

                code.clear();
                name.clear();
                copies.clear();
            }
        });

        addMoviePane.setStyle("-fx-background-color: #CCCCFF;");
        movieScene = new Scene(addMoviePane, 1600, 900);

    }

    private void addAlbumScene() {

        //labels
        Label codeLabel = new Label("Code");
        codeLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        codeLabel.setStyle("-fx-background-color: gold;");
        Label copiesAvailable = new Label("Copies Available");
        copiesAvailable.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        copiesAvailable.setStyle("-fx-background-color: gold;");
        Label nameLabel = new Label("Title");
        nameLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        nameLabel.setStyle("-fx-background-color: gold;");
        Label artistLabel = new Label("Artist");
        artistLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        artistLabel.setStyle("-fx-background-color: gold;");
        Label songsLabel = new Label("Songs");
        songsLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 15));
        songsLabel.setStyle("-fx-background-color: gold;");

        //text fields
        TextField code = new TextField();
        code.setPromptText("Media Code");
        TextField copies = new TextField();
        copies.setPromptText("Copies Available");
        copies.setDisable(true);
        TextField name = new TextField();
        name.setPromptText("Name");
        name.setDisable(true);
        TextField artist = new TextField();
        artist.setPromptText("Artist");
        artist.setDisable(true);
        TextField songs = new TextField();
        songs.setPromptText("Songs");
        songs.setDisable(true);

        code.setOnKeyTyped(e -> name.setDisable(false));
        name.setOnKeyTyped(e -> copies.setDisable(false));
        copies.setOnKeyTyped(e -> artist.setDisable(false));
        artist.setOnKeyTyped(e -> songs.setDisable(false));

        //button
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");

        //Button functionality
        backButton.setOnAction(e -> window.setScene(addMediaScene));

        //Image
        ImageView addV = new ImageView(new Image("pluss.png"));
        ImageView backV = new ImageView(new Image("back.png"));
        addButton.setGraphic(addV);
        backButton.setGraphic(backV);

        addV.setFitHeight(100);
        addV.setFitWidth(100);
        backV.setFitHeight(100);
        backV.setFitWidth(100);

        //layout
        GridPane addAlbumGridPane = new GridPane();
        addAlbumGridPane.addRow(0, codeLabel, code);
        addAlbumGridPane.addRow(1, nameLabel, name);
        addAlbumGridPane.addRow(2, copiesAvailable, copies);
        addAlbumGridPane.addRow(3, artistLabel, artist);
        addAlbumGridPane.addRow(4, songsLabel, songs);
        addAlbumGridPane.setAlignment(Pos.CENTER);
        addAlbumGridPane.setVgap(45);
        addAlbumGridPane.setHgap(15);
        addAlbumGridPane.setMinSize(400, 400);
        HBox bottomAlbumBox = new HBox(20);
        bottomAlbumBox.getChildren().addAll(addButton, backButton);
        bottomAlbumBox.setAlignment(Pos.CENTER);

        VBox addAlbumPane = new VBox(50);
        addAlbumPane.getChildren().addAll(addAlbumGridPane, bottomAlbumBox);
        addAlbumPane.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> {
            boolean flag = true;
            for (int i = 0; i < manager.mediaDatabase.size(); i++) {
                if (manager.mediaDatabase.get(i).getCode().equals(code.getText()))
                    flag = false;
            }

            try {
                if (flag) {
                    manager.addAlbum(code.getText(), name.getText(), Integer.parseInt(copies.getText()),
                            artist.getText(), songs.getText());
                } else
                    throw new IllegalArgumentException("Code must be unique");
            } catch (Exception exception) {
            }

            code.clear();
            name.clear();
            copies.clear();
            artist.clear();
            songs.clear();
        });

        addAlbumPane.setStyle("-fx-background-color: #CCCCFF;");
        albumScene = new Scene(addAlbumPane, 1600, 900);
    }

    private void customerSceneDemo() {

        //buttons
        Button add = new Button("Add New Customer");
        Button delete = new Button("Delete Customer");
        Button update = new Button("Update Information About Customer");
        Button search = new Button("Search a Customer by ID");
        Button back = new Button("Return To Main Page");
        add.setPrefSize(300, 70);
        delete.setPrefSize(300, 70);
        search.setPrefSize(300, 70);
        back.setPrefSize(300, 70);
        update.setPrefSize(300, 70);
        add.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        delete.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        update.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        search.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));
        back.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 16));

        //button functionality
        add.setOnAction(e -> window.setScene(addCustomerScene));
        delete.setOnAction(e -> window.setScene(deleteCustomerScene));
        back.setOnAction(e -> window.setScene(primaryScene));
        update.setOnAction(e -> window.setScene(updateCustomerScene));
        search.setOnAction(e -> window.setScene(searchScene));

        back.setGraphic(new ImageView(new Image("Return.png")));
        //layout
        VBox customerPane = new VBox(55);
        customerPane.setPadding(new Insets(15, 25, 25, 25));
        customerPane.getChildren().addAll(add, delete, update, search, back);
        customerPane.setAlignment(Pos.CENTER);
        customerPane.setStyle("-fx-background-color: #009999;");

        customerScene = new Scene(customerPane, 1600, 900);
    }

    private void searchScene() {

        //labels
        Label searchLabel = new Label("Search Customer");
        searchLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 30));
        searchLabel.setStyle("-fx-background-color: gold;");
        Label idLabel = new Label("Customer ID ");
        idLabel.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 15));
        idLabel.setStyle("-fx-background-color: gold;");
        idLabel.setMinSize(100, 50);

        //text fields
        TextField id = new TextField();
        id.setPromptText("ID");
        id.setMinSize(100, 50);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(idLabel, id);
        hBox.setAlignment(Pos.CENTER);

        TextArea searchArea = new TextArea();
        searchArea.setMaxWidth(650);
        searchArea.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, 20));

        //button
        Button find = new Button("Find");
        Button backButton = new Button("Back");

        //button functionality
        backButton.setOnAction(e -> window.setScene(customerScene));

        //image
        ImageView searchView = new ImageView(new Image("find.png"));
        ImageView backView = new ImageView(new Image("return.jpg"));

        searchView.setFitHeight(40);
        searchView.setFitWidth(40);
        backView.setFitHeight(40);
        backView.setFitWidth(40);

        find.setGraphic(searchView);
        backButton.setGraphic(backView);

        HBox hBox2 = new HBox(20);
        hBox2.getChildren().addAll(find, backButton);
        hBox2.setAlignment(Pos.CENTER);

        VBox searchCustomerPane = new VBox(40);
        searchCustomerPane.getChildren().addAll(searchLabel, hBox, searchArea, hBox2);
        searchCustomerPane.setAlignment(Pos.CENTER);

        find.setOnAction(e -> {
            int i = manager.searchCustomer(id.getText());

            if (i != -1) {
                searchArea.setText(manager.customerDatabase.get(i).toString());
            } else
                searchArea.setText("Customer Not Found!");

            id.clear();
        });
        searchCustomerPane.setStyle("-fx-background-color: #f5f5dc;");
        searchScene = new Scene(searchCustomerPane, 1600, 900);

    }

    private void updateCustomerScene() {
        //labels
        Label newLabel = new Label("New Information");
        newLabel.setFont(Font.font("Impact", 20));
        newLabel.setStyle("-fx-background-color: gold;");

        Label idLabel = new Label("Customer ID ");
        Label nameLabel = new Label("Customer Name");
        Label addLabel = new Label("Customer Address");
        Label mobileLabel = new Label("Customer Mobile");

        //new
        TextField id = new TextField();
        id.setPromptText("Customer Id");
        TextField newName = new TextField();
        newName.setPromptText("New Name");
        TextField newAddress = new TextField();
        newAddress.setPromptText("New Address");
        TextField newMobile = new TextField();
        newMobile.setPromptText("New Mobile");

        //button
        Button update = new Button("Update");
        Button back = new Button("Back");

        TextArea textArea = new TextArea();
        textArea.setFont(Font.font("Aldhabi", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 25));
        textArea.setMaxWidth(450);
        textArea.setMaxHeight(200);

        ImageView updateView = new ImageView(new Image("update.png"));
        updateView.setFitWidth(50);
        updateView.setFitHeight(50);
        ImageView backView = new ImageView(new Image("back.png"));
        backView.setFitWidth(50);
        backView.setFitHeight(50);
        update.setGraphic(updateView);
        back.setGraphic(backView);

        //radio button
        ToggleGroup group = new ToggleGroup();
        RadioButton limited = new RadioButton("LIMITED");
        limited.setFont(Font.font("Aldhabi", 25));
        RadioButton unlimited = new RadioButton("UNLIMITED");
        unlimited.setFont(Font.font("Aldhabi", 25));
        limited.setToggleGroup(group);
        unlimited.setToggleGroup(group);

        back.setOnAction(e -> window.setScene(customerScene));

        GridPane updateCustomerPane = new GridPane();
        updateCustomerPane.setHgap(15);
        updateCustomerPane.setVgap(15);
        updateCustomerPane.add(new Label(), 0, 0);
        updateCustomerPane.add(idLabel, 0, 1);
        updateCustomerPane.add(nameLabel, 0, 2);
        updateCustomerPane.add(addLabel, 0, 3);
        updateCustomerPane.add(mobileLabel, 0, 4);
        updateCustomerPane.add(newLabel, 1, 0);
        updateCustomerPane.add(id, 1, 1);
        updateCustomerPane.add(newName, 1, 2);
        updateCustomerPane.add(newAddress, 1, 3);
        updateCustomerPane.add(newMobile, 1, 4);
        updateCustomerPane.add(limited, 0, 6);
        updateCustomerPane.add(unlimited, 1, 6);

        updateCustomerPane.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(update, back);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(updateCustomerPane, textArea, hBox);
        vBox.setAlignment(Pos.CENTER);

        update.setOnAction(e -> {

            String value = null;
            if (limited.isSelected())
                value = "LIMITED";
            else if (unlimited.isSelected())
                value = "UNLIMITED";

            if (value != null) {
                boolean answer = manager.updateCustomer(id.getText(), newName.getText(), newAddress.getText(),
                        value, newMobile.getText());


                if (answer)
                    textArea.setText("Update Customer Data Done !");
                else
                    textArea.setText("Update Failed,\n" + "Make sure you entered all the data correctly");
                id.clear();
                newName.clear();
                newAddress.clear();
                newMobile.clear();
            } else
                textArea.setText("Update Failed,\n" + "Make sure you entered all the data correctly");

            limited.setSelected(false);
            unlimited.setSelected(false);
        });

        vBox.setStyle("-fx-background-color: #f5f5dc;");
        updateCustomerScene = new Scene(vBox, 1600, 900);

    }

    private void deleteCustomerScene() {

        //labels
        Label idLabel = new Label("Customer ID ");
        idLabel.setFont(Font.font("Impact", 20));
        idLabel.setStyle("-fx-background-color: gold;");
        Label nameLabel = new Label("Customer Name");
        nameLabel.setFont(Font.font("Impact", 20));
        nameLabel.setStyle("-fx-background-color: gold;");
        Label addLabel = new Label("Customer Address");
        addLabel.setFont(Font.font("Impact", 20));
        addLabel.setStyle("-fx-background-color: gold;");
        Label mobileLabel = new Label("Customer Mobile");
        mobileLabel.setFont(Font.font("Impact", 20));
        mobileLabel.setStyle("-fx-background-color: gold;");
        //text fields
        TextField id = new TextField();
        id.setPromptText("ID");
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField address = new TextField();
        address.setPromptText("Address");
        TextField mobile = new TextField();
        mobile.setPromptText("Mobile");

        //button
        Button find = new Button("Find");
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        //button functionality
        back.setOnAction(e -> window.setScene(customerScene));

        //image
        ImageView deleteV = new ImageView(new Image("delete.png"));
        ImageView backV = new ImageView(new Image("back.png"));
        ImageView findV = new ImageView(new Image("find.png"));

        deleteV.setFitHeight(40);
        deleteV.setFitWidth(40);
        backV.setFitHeight(40);
        backV.setFitWidth(40);
        findV.setFitHeight(40);
        findV.setFitWidth(40);

        find.setGraphic(findV);
        back.setGraphic(backV);
        delete.setGraphic(deleteV);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(find, delete, back);
        hBox.setAlignment(Pos.CENTER);

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(500);
        textArea.setFont(Font.font("Impact", FontWeight.EXTRA_LIGHT, FontPosture.REGULAR, 25));

        //layout
        GridPane deleteCustomerPane = new GridPane();
        deleteCustomerPane.setVgap(10);
        deleteCustomerPane.setHgap(10);
        deleteCustomerPane.addRow(0, idLabel, id);
        deleteCustomerPane.addRow(2, nameLabel, name);
        deleteCustomerPane.addRow(4, addLabel, address);
        deleteCustomerPane.addRow(6, mobileLabel, mobile);
        deleteCustomerPane.add(textArea, 1, 8);
        deleteCustomerPane.add(hBox, 0, 10);
        deleteCustomerPane.add(back, 2, 10);
        deleteCustomerPane.setAlignment(Pos.CENTER);

        delete.setOnAction(e -> {

            boolean answer = manager.deleteCustomer(id.getText());
            if (answer)
                textArea.setText("Delete Customer Done\n");
            else
                textArea.setText("Customer Not Found, Delete Failed");

        });
        find.setOnAction(e -> {
            int i = manager.searchCustomer(id.getText());
            if (i != -1)
                textArea.setText(manager.customerDatabase.get(i).getName() + ", " + manager.customerDatabase.get(i).getMobile());

            else
                textArea.setText("Customer Not Found");

            id.clear();
            name.clear();
            address.clear();
            mobile.clear();

        });

        deleteCustomerPane.setStyle("-fx-background-color: #f5f5dc;");
        deleteCustomerScene = new Scene(deleteCustomerPane, 1600, 900);

    }

    private void addCustomerScene() {
        //labels
        Label idLabel = new Label("Customer ID ");
        idLabel.setFont(Font.font("Impact", 20));
        idLabel.setStyle("-fx-background-color: gold;");
        Label nameLabel = new Label("Customer Name");
        nameLabel.setFont(Font.font("Impact", 20));
        nameLabel.setStyle("-fx-background-color: gold;");
        Label addLabel = new Label("Customer Address");
        addLabel.setFont(Font.font("Impact", 20));
        addLabel.setStyle("-fx-background-color: gold;");
        Label mobileLabel = new Label("Customer Mobile");
        mobileLabel.setFont(Font.font("Impact", 20));
        mobileLabel.setStyle("-fx-background-color: gold;");
        //text fields
        TextField id = new TextField();
        id.setPromptText("ID");
        TextField name = new TextField();
        name.setPromptText("Name");
        name.setDisable(true);
        TextField address = new TextField();
        address.setPromptText("Address");
        address.setDisable(true);
        TextField mobile = new TextField();
        mobile.setPromptText("Mobile");
        mobile.setDisable(true);

        //button
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");

        //radio Button
        RadioButton limited = new RadioButton("LIMITED");
        limited.setFont(Font.font("Aldhabi", FontWeight.LIGHT, 25));
        RadioButton unlimited = new RadioButton("UNLIMITED");
        unlimited.setFont(Font.font("Aldhabi", FontWeight.LIGHT, 25));
        ToggleGroup plan = new ToggleGroup();
        limited.setToggleGroup(plan);
        unlimited.setToggleGroup(plan);
        limited.setDisable(true);
        unlimited.setDisable(true);
        addButton.setDisable(true);

        //enabled
        id.setOnKeyTyped(e -> name.setDisable(false));
        name.setOnKeyTyped(e -> address.setDisable(false));
        address.setOnKeyTyped(e -> mobile.setDisable(false));
        mobile.setOnKeyTyped(e -> {
            limited.setDisable(false);
            unlimited.setDisable(false);
        });
        limited.setOnAction(e -> addButton.setDisable(false));
        unlimited.setOnAction(e -> addButton.setDisable(false));

        //Button functionality
        backButton.setOnAction(e -> window.setScene(customerScene));

        //Image
        ImageView addV = new ImageView(new Image("pluss.png"));
        ImageView backV = new ImageView(new Image("back.png"));
        addButton.setGraphic(addV);
        backButton.setGraphic(backV);

        addV.setFitHeight(100);
        addV.setFitWidth(100);
        backV.setFitHeight(100);
        backV.setFitWidth(100);

        HBox bottomBoxBox = new HBox();
        bottomBoxBox.setSpacing(10);
        bottomBoxBox.getChildren().addAll(addButton, backButton);
        bottomBoxBox.setAlignment(Pos.TOP_CENTER);

        //layout
        GridPane addCustomerPane = new GridPane();
        addCustomerPane.setVgap(30);
        addCustomerPane.setHgap(10);
        addCustomerPane.addRow(0, idLabel, id);
        addCustomerPane.addRow(1, nameLabel, name);
        addCustomerPane.addRow(2, addLabel, address);
        addCustomerPane.addRow(3, mobileLabel, mobile);
        addCustomerPane.addRow(5, limited, unlimited);
        addCustomerPane.addRow(7, addButton, backButton);
        addCustomerPane.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> {

            String value = null;
            if (limited.isSelected())
                value = "LIMITED";
            else if (unlimited.isSelected())
                value = "UNLIMITED";

            try {
                boolean flag = true;
                for (int i = 0; i < manager.customerDatabase.size(); i++) {
                    if (manager.customerDatabase.get(i).getId().equals(id.getText()))
                        flag = false;
                }
                if (flag) {
                    manager.customerDatabase.add
                            (new Customer(id.getText(), name.getText(), address.getText(), value, mobile.getText()));
                } else
                    throw new IllegalArgumentException("Id must be unique");
            } catch (Exception exception) {
            }

            limited.setSelected(false);
            unlimited.setSelected(false);

            id.clear();
            name.clear();
            address.clear();
            mobile.clear();

        });

        addCustomerPane.setStyle("-fx-background-color: #f5f5dc;");
        addCustomerScene = new Scene(addCustomerPane, 1600, 900);
    }


    public static void main(String[] args) {
        launch(args);
    }

}


