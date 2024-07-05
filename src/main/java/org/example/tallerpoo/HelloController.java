package org.example.tallerpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.tallerpoo.db.DB;

public class HelloController {

    final DB db = DB.getDBInstance();

    @FXML
    private TextField EmployeeLastName;

    @FXML
    private SplitMenuButton category;

    @FXML
    private TextField date;

    @FXML
    private TextArea description;

    @FXML
    private TextField employeeName;

    @FXML
    private TextField name;

    @FXML
    private TextField newCategory;

    @FXML
    private TextField newEmployeeLastName;

    @FXML
    private TextField newEmployeeName;

    @FXML
    void submit(ActionEvent event) {
    }

    @FXML
    void submitNewCategory(ActionEvent event) {
        String categoryName = newCategory.getText();
        db.insertCategory(categoryName);
    }

    @FXML
    void submitNewEmployee(ActionEvent event) {
        String employeeName = newEmployeeName.getText();
        String employeeLastName = newEmployeeLastName.getText();

        db.insertEmployee(employeeName, employeeLastName);
    }
    private void updateCategory(){

    }
}


