/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.control.presentation.attendeeinput;

import com.airhacks.control.business.registrations.entity.Attendee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author adam-bien.com
 */
public class AttendeeInputPresenter implements Initializable {

    @FXML
    Button saveButton;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField company;
    @FXML
    CheckBox paid;
    @FXML
    CheckBox boot;
    @FXML
    CheckBox effect;
    @FXML
    CheckBox architect;
    @FXML
    CheckBox ui;
    @FXML
    CheckBox javaee;
    private ObjectProperty<Attendee> selectedAttendee;
    private ObjectProperty<Attendee> newAttendee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanProperty nameEntered = new SimpleBooleanProperty();
        nameEntered.bind(firstName.textProperty().isNotEmpty().or(lastName.textProperty().isNotEmpty()).or(company.textProperty().isNotEmpty()));

        BooleanProperty dayChoosen = new SimpleBooleanProperty();
        dayChoosen.bind(boot.selectedProperty().or(effect.selectedProperty()).or(architect.selectedProperty()).or(javaee.selectedProperty()));

        this.saveButton.disableProperty().bind(dayChoosen.and(nameEntered).not());

        this.selectedAttendee = new SimpleObjectProperty<>();
        this.newAttendee = new SimpleObjectProperty<>();

        this.selectedAttendee.addListener(new ChangeListener<Attendee>() {
            @Override
            public void changed(ObservableValue<? extends Attendee> observable, Attendee oldValue, Attendee newValue) {
                if (newValue != null) {
                    boot.setSelected(newValue.isBootstrap());
                    effect.setSelected(newValue.isEffective());
                    architect.setSelected(newValue.isArchitecture());
                    ui.setSelected(newValue.isUi());
                    javaee.setSelected(newValue.isJavaee());

                    firstName.setText(newValue.getFirstName());
                    lastName.setText(newValue.getLastName());
                    company.setText(newValue.getCompany());
                    paid.setSelected(newValue.isPaid());
                } else {
                    resetUI();
                }
            }
        });
    }

    public void resetUI() {
        architect.setSelected(false);
        boot.setSelected(false);
        effect.setSelected(false);
        ui.setSelected(false);
        javaee.setSelected(false);
        paid.setSelected(false);

        resetTextField(firstName);
        resetTextField(lastName);
        resetTextField(company);
    }

    void resetTextField(TextField tf) {
        tf.setText(tf.getPromptText());
    }

    public void save() {

        Attendee attendee = selectedAttendee.get();
        if (attendee == null) {
            attendee = new Attendee();
        }
        attendee.setFirstName(firstName.getText());
        attendee.setLastName(lastName.getText());
        attendee.setCompany(company.getText());
        attendee.setPaid(paid.isSelected());
        attendee.setArchitecture(architect.isSelected());
        attendee.setBootstrap(boot.isSelected());
        attendee.setEffective(effect.isSelected());
        attendee.setUi(ui.isSelected());
        attendee.setJavaee(javaee.isSelected());

        this.newAttendee.set(attendee);
    }

    public ObjectProperty<Attendee> selectedAttendeeProperty() {
        return selectedAttendee;
    }

    public ObjectProperty<Attendee> newAttendeeProperty() {
        return newAttendee;
    }
}
