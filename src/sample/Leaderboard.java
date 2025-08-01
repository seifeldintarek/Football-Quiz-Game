package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Leaderboard {

    // Define the classes for the Table rows
    public static class DataRow {
        private final String name;
        private final int firstNumber;
        private final int secondNumber;
        private final int sum;

        public DataRow(String name, int firstNumber, int secondNumber) {
            this.name = name;
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
            this.sum = firstNumber + secondNumber;  // Calculate the sum when the row is created
        }

        public String getName() {
            return name;
        }

        public int getFirstNumber() {
            return firstNumber;
        }

        public int getSecondNumber() {
            return secondNumber;
        }

        public int getSum() {
            return sum;
        }
    }


    public static void showRanking () throws SQLException {

        Stage primaryStage = new Stage();

        // Create the TableView for displaying Name and First Number
        TableView<DataRow> table1 = new TableView<>();
        TableColumn<DataRow, String> nameColumn1 = new TableColumn<>("Name");
        TableColumn<DataRow, Integer> numberColumn1 = new TableColumn<>("Death Challenge Score");

        // Set up the columns for Table 1
        nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn1.setCellValueFactory(new PropertyValueFactory<>("firstNumber"));
        table1.getColumns().addAll(nameColumn1, numberColumn1);

        // Create the TableView for displaying Name and Second Number
        TableView<DataRow> table2 = new TableView<>();
        TableColumn<DataRow, String> nameColumn2 = new TableColumn<>("Name");
        TableColumn<DataRow, Integer> numberColumn2 = new TableColumn<>("Time Challenge Score");

        // Set up the columns for Table 2
        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn2.setCellValueFactory(new PropertyValueFactory<>("secondNumber"));
        table2.getColumns().addAll(nameColumn2, numberColumn2);

        // Create the TableView for displaying Name and Sum
        TableView<DataRow> table3 = new TableView<>();
        TableColumn<DataRow, String> nameColumn3 = new TableColumn<>("Name");
        TableColumn<DataRow, Integer> sumColumn = new TableColumn<>("Sum of Challenges Score");

        // Set up the columns for Table 3
        nameColumn3.setCellValueFactory(new PropertyValueFactory<>("name"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
        table3.getColumns().addAll(nameColumn3, sumColumn);

        // Sample data to populate the tables
        ArrayList<DataRow> dataList = new ArrayList<>();
        List<PlayerwithAccount> users = PlayerwithAccount.getAllPlayers();

        users.forEach(
                player -> {

                    dataList.add(new DataRow(player.getUsername(), player.getDeathScore(), player.getTimeScore()));
                }

        );


        // Convert the ArrayList to ObservableList
        ObservableList<DataRow> firstNumberData = FXCollections.observableArrayList(dataList);
        ObservableList<DataRow> secondNumberData = FXCollections.observableArrayList(dataList);
        ObservableList<DataRow> sumData = FXCollections.observableArrayList(dataList);

        // Sort the data in descending order independently for each table
        firstNumberData.sort((row1, row2) -> Integer.compare(row2.getFirstNumber(), row1.getFirstNumber()));
        secondNumberData.sort((row1, row2) -> Integer.compare(row2.getSecondNumber(), row1.getSecondNumber()));
        sumData.sort((row1, row2) -> Integer.compare(row2.getSum(), row1.getSum()));

        // Set the sorted data in the tables
        table1.setItems(firstNumberData);
        table2.setItems(secondNumberData);
        table3.setItems(sumData);

        // Create the "Load Data" button
        Button loadDataButton = new Button("Leaderboard");

        // VBox layout for the button
        VBox layout = new VBox(10);
        layout.getChildren().add(loadDataButton);

        // Create the scene
        Scene scene = new Scene(layout, 600, 600);

        // Set the title of the stage (window)
        primaryStage.setTitle("Challenge Score Application");

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handler for the button
        loadDataButton.setOnAction(e -> {
            // Remove the "Load Data" button and add table selection buttons
            layout.getChildren().clear();

            // Create buttons for each table
            Button viewTable1Button = new Button("View Death Challenge Score");
            Button viewTable2Button = new Button("View Time Challenge Score");
            Button viewTable3Button = new Button("View Sum of Challenge Scores");

            // Add buttons to the layout
            VBox buttonLayout = new VBox(10);
            buttonLayout.getChildren().addAll(viewTable1Button, viewTable2Button, viewTable3Button);

            // Create the scene with buttons for table selection
            Scene tableSelectionScene = new Scene(buttonLayout, 600, 600);
            primaryStage.setScene(tableSelectionScene);

            // Event handlers for each table button
            viewTable1Button.setOnAction(view1Event -> {
                // Create a new layout for table1
                HBox tableLayout1 = new HBox();
                tableLayout1.getChildren().add(table1);

                // Make the table fill the screen (both horizontally and vertically)
                HBox.setHgrow(table1, Priority.ALWAYS);
                tableLayout1.setFillHeight(true);

                Button backToSelectionButton1 = new Button("Back");
                backToSelectionButton1.setOnAction(e1 -> primaryStage.setScene(tableSelectionScene));

                VBox vbox1 = new VBox(10, tableLayout1, backToSelectionButton1);
                Scene table1Scene = new Scene(vbox1, 600, 600);
                primaryStage.setScene(table1Scene);
            });

            viewTable2Button.setOnAction(view2Event -> {
                // Create a new layout for table2
                HBox tableLayout2 = new HBox();
                tableLayout2.getChildren().add(table2);

                // Make the table fill the screen (both horizontally and vertically)
                HBox.setHgrow(table2, Priority.ALWAYS);
                tableLayout2.setFillHeight(true);

                Button backToSelectionButton2 = new Button("Back");
                backToSelectionButton2.setOnAction(e2 -> primaryStage.setScene(tableSelectionScene));

                VBox vbox2 = new VBox(10, tableLayout2, backToSelectionButton2);
                Scene table2Scene = new Scene(vbox2, 600, 600);
                primaryStage.setScene(table2Scene);
            });

            viewTable3Button.setOnAction(view3Event -> {
                // Create a new layout for table3
                HBox tableLayout3 = new HBox();
                tableLayout3.getChildren().add(table3);

                // Make the table fill the screen (both horizontally and vertically)
                HBox.setHgrow(table3, Priority.ALWAYS);
                tableLayout3.setFillHeight(true);

                Button backToSelectionButton3 = new Button("Back");
                backToSelectionButton3.setOnAction(e3 -> primaryStage.setScene(tableSelectionScene));

                VBox vbox3 = new VBox(10, tableLayout3, backToSelectionButton3);
                Scene table3Scene = new Scene(vbox3, 600, 600);
                primaryStage.setScene(table3Scene);
            });
        });
    }


}