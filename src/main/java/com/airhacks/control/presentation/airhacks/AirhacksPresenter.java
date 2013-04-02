package com.airhacks.control.presentation.airhacks;

import com.airhacks.control.business.registrations.boundary.RegistrationService;
import com.airhacks.control.business.registrations.entity.Attendee;
import com.airhacks.control.presentation.attendeeinput.AttendeeInputPresenter;
import com.airhacks.control.presentation.attendeeinput.AttendeeInputView;
import com.airhacks.control.presentation.workshops.WorkshopsPresenter;
import com.airhacks.control.presentation.workshops.WorkshopsView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 *
 * @author adam-bien.com
 */
public class AirhacksPresenter implements Initializable {

    @FXML
    AnchorPane input;
    @FXML
    AnchorPane overview;
    AttendeeInputPresenter attendeeInputPresenter;
    WorkshopsPresenter workshopsPresenter;
    @Inject
    RegistrationService rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AttendeeInputView inputView = new AttendeeInputView();
        this.attendeeInputPresenter = (AttendeeInputPresenter) inputView.getPresenter();
        this.attendeeInputPresenter.newAttendeeProperty().addListener(new ChangeListener<Attendee>() {
            @Override
            public void changed(ObservableValue<? extends Attendee> ov, Attendee old, Attendee newAttendee) {
                if (newAttendee != null) {
                    workshopsPresenter.newAttendeeProperty().set(newAttendee);
                }
            }
        });
        WorkshopsView workshopsView = new WorkshopsView();
        this.workshopsPresenter = (WorkshopsPresenter) workshopsView.getPresenter();
        this.attendeeInputPresenter.selectedAttendeeProperty().bind(workshopsPresenter.selectedAttendeeProperty());
        input.getChildren().add(inputView.getView());
        overview.getChildren().add(workshopsView.getView());
    }

    public void save() {
        rs.save();
    }
}
