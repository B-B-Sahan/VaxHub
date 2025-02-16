package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import lk.ijse.vaxhub.model.*;
import lk.ijse.vaxhub.model.Tm.AppoimentTm;
import lk.ijse.vaxhub.repository.*;
import lk.ijse.vaxhub.util.ValidationUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AppoimentFormController {

    @FXML
    private TextField ApooimentIdTextField;

    @FXML
    private Label AppoimentDateLable;

    @FXML
    private TableView<AppoimentTm> AppoimentTable;

    @FXML
    private TableColumn<?, ?> AppoimentTimeColumn;

    @FXML
    private Label AppoimentTimeLable;

    @FXML
    private TextField AppoimentTimeTextField;

    @FXML
    private Label DateLable;

    @FXML
    private JFXComboBox<String> EmployeeIdCMB;

    @FXML
    private TableColumn<?, ?> EmployeeIdColumn;

    @FXML
    private Label EmployeeIdLBL;

    @FXML
    private JFXComboBox<String> ParentNICCMB;

    @FXML
    private Label ParentNicLBL;

    @FXML
    private TextField PaymentIdTextField;

    @FXML
    private TableColumn<?, ?> appoimentDateTimeColumn;

    @FXML
    private Label appoimentIdLable;

    @FXML
    private TableColumn<?, ?> appoimentidColumn;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private AnchorPane paneHolder;

    @FXML
    private Label patientIdLable1;

    @FXML
    private TableColumn<?, ?> patientidColumn;

    @FXML
    private TableColumn<?, ?> paymentColumn;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton updateButton;
    private ObservableList<AppoimentTm> appoimentTm = FXCollections.observableArrayList();

    @FXML
    void EmployeeIdCMBOnAction(ActionEvent event) {
        String id = EmployeeIdCMB.getValue();
        try {
            Employee employee = EmployeeRepo.searchById(id);
            if (employee != null) {
                EmployeeIdCMB.setValue(employee.getEmployee_id());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ParentNICCMBOnAction(ActionEvent event) {
        String id = ParentNICCMB.getValue();
        try {
            Patient patient = PatientRepo.searchById(id);
            if (patient != null) {
                ParentNICCMB.setValue(patient.getPatient_id());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private List<Appoiment> appoimentList = new ArrayList<>();
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    private void setCellDataFactory() {
        appoimentidColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_id"));
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        patientidColumn.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        appoimentDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_date"));
        AppoimentTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appoiment_time"));





    }





    public void initialize() {
        this.appoimentList = getAllAppoiment();
        setCellDataFactory();
        loadAllAppoiment();
        setDate();
        getEmployeeId();
        getParentId();
    }

    private void getParentId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> pIdList = PatientRepo.getPIds();

            for (String id : pIdList) {
                obList.add(id);
            }
            ParentNICCMB.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> IdList = EmployeeRepo.getIds();

            for (String id : IdList) {
                obList.add(id);
            }
            EmployeeIdCMB.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        DateLable.setText(String.valueOf(now));
    }

    private void loadAllAppoiment() {
        ObservableList<AppoimentTm> tmList = FXCollections.observableArrayList();

        for (Appoiment appoiment : appoimentList) {
            AppoimentTm appoimentTm = new AppoimentTm(
                    appoiment.getAppoiment_id(),
                    appoiment.getEmployee_id(),
                    appoiment.getPatient_id(),
                    appoiment.getAppoiment_date(),
                    appoiment.getAppoiment_time()
            );
            tmList.add(appoimentTm);
        }
        AppoimentTable.setItems(tmList);
        AppoimentTm selectedItem = AppoimentTable.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);


    }


    private List<Appoiment> getAllAppoiment() {
        List<Appoiment> appoimentList = null;
        try {
            appoimentList = AppoimentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appoimentList;
    }


    @FXML
    void ClearButtonOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void DeleteButtonOnAction(ActionEvent event) {
        String appoiment_id = ApooimentIdTextField.getText();

        try {
            boolean isDeleted = AppoimentRepo.delete(appoiment_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                loadAllAppoiment();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void PatientIdSearchOnAction(ActionEvent event) {
        String patient_id = ParentNICCMB.getValue();

        System.out.println(patient_id);
        try {
            Appoiment appoiment = AppoimentRepo.searchById(patient_id);

            if (appoiment != null) {
                ApooimentIdTextField.setText(appoiment.getAppoiment_id());
                EmployeeIdCMB.setValue(appoiment.getEmployee_id());
                ParentNICCMB.setValue(appoiment.getPatient_id());
                DateLable.setText(appoiment.getAppoiment_date());
                AppoimentTimeTextField.setText(appoiment.getAppoiment_time());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void SaveButtonOnAction() throws SQLException {
        String appoiment_id = ApooimentIdTextField.getText();
        String employee_id = EmployeeIdCMB.getValue();
        String patient_id = ParentNICCMB.getValue();
        String appoiment_date = DateLable.getText();
        String appoiment_time = AppoimentTimeTextField.getText();
        if (appoiment_id.trim().length() == 0 || employee_id.trim().length() == 0 || patient_id.trim().length() == 0 || appoiment_date.trim().length() == 0 || appoiment_time.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }



        Appoiment appoiment = new Appoiment(appoiment_id, employee_id, patient_id,  appoiment_date, appoiment_time);


        try {
            boolean isSaved = AppoimentRepo.save(appoiment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved!").show();
                loadAllAppoiment();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        }



    @FXML
    void UpdateButtonOnAction(ActionEvent event) {
        String appoiment_id = ApooimentIdTextField.getText();
        String employee_id = EmployeeIdCMB.getValue();
        String patient_id = ParentNICCMB.getValue();
        String appoiment_date = DateLable.getText();
        String appoiment_time = AppoimentTimeTextField.getText();

        Appoiment appoiment = new Appoiment(appoiment_id, employee_id, patient_id, appoiment_date, appoiment_time);

        try {
            boolean isUpdated = AppoimentRepo.update(appoiment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                loadAllAppoiment();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void clearFields() {
        ApooimentIdTextField.setText("");
        EmployeeIdCMB.setValue("");
        ParentNICCMB.setValue("");
        DateLable.setText("");
        AppoimentTimeTextField.setText("");
    }

    public void AppoimentSearchOnAction(ActionEvent event) {

    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }


    @FXML
    void APidOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(AP)([A-z0-9]){1,}$");
        if (!idPattern.matcher(ApooimentIdTextField.getText()).matches()) {
            addError(ApooimentIdTextField);
            saveButton.setDisable(true);
        }else{
            removeError(ApooimentIdTextField);
            saveButton.setDisable(false);
        }
    }










}