package com.airhacks.control.presentation.workshops;

import com.airhacks.control.business.registrations.boundary.RegistrationService;
import com.airhacks.control.business.registrations.entity.Attendee;
import com.airhacks.control.presentation.days.DayPresenter;
import com.airhacks.control.presentation.days.DayView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.util.List;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author adam-bien.com
 */
public class WorkshopsPresenter implements Initializable {

    private ObjectProperty<Attendee> attendees;
    @FXML
    private AnchorPane bootstrapPane;
    @FXML
    private AnchorPane effectivePane;
    @FXML
    private AnchorPane architecturePane;
    @FXML
    private AnchorPane uiPane;
    @FXML
    private AnchorPane javaeePane;
    private DayPresenter bootstrapPresenter;
    private DayPresenter effectivePresenter;
    private DayPresenter architecturePresenter;
    private DayPresenter uiPresenter;
    private DayPresenter javaeePresenter;
    private DayView bootstrapView;
    private DayView effectiveView;
    private DayView architectureView;
    private DayView uiView;
    private DayView javaeeView;
    @Inject
    RegistrationService service;
    private ObjectProperty<Attendee> selectedAttendee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ChangeListener<Attendee> storingListener = new ChangeListener<Attendee>() {
            @Override
            public void changed(ObservableValue<? extends Attendee> ov, Attendee old, Attendee newAttendee) {
                if (newAttendee != null) {
                    service.save(newAttendee);
                    loadFromStore();
                }
            }
        };
        this.selectedAttendee = new SimpleObjectProperty<>();

        this.attendees = new SimpleObjectProperty<>();
        this.attendees.addListener(storingListener);


        this.bootstrapView = new DayView();
        this.bootstrapPresenter = (DayPresenter) this.bootstrapView.getPresenter();

        this.effectiveView = new DayView();
        this.effectivePresenter = (DayPresenter) this.effectiveView.getPresenter();

        this.architectureView = new DayView();
        this.architecturePresenter = (DayPresenter) this.architectureView.getPresenter();

        this.uiView = new DayView();
        this.uiPresenter = (DayPresenter) this.uiView.getPresenter();

        this.javaeeView = new DayView();
        this.javaeePresenter = (DayPresenter) this.javaeeView.getPresenter();
        this.registerAttendeeListeners();
        this.createPanes();
        this.loadFromStore();
    }

    void createPanes() {
        this.bootstrapPane.getChildren().add(this.bootstrapView.getView());
        this.effectivePane.getChildren().add(this.effectiveView.getView());
        this.architecturePane.getChildren().add(this.architectureView.getView());
        this.uiPane.getChildren().add(this.uiView.getView());
        this.javaeePane.getChildren().add(this.javaeeView.getView());
    }

    public ObjectProperty<Attendee> newAttendeeProperty() {
        return attendees;
    }

    void add(Attendee newAttendee) {
        if (newAttendee.isBootstrap()) {
            this.bootstrapPresenter.add(newAttendee);
        }
        if (newAttendee.isEffective()) {
            this.effectivePresenter.add(newAttendee);
        }
        if (newAttendee.isArchitecture()) {
            this.architecturePresenter.add(newAttendee);
        }
        if (newAttendee.isUi()) {
            this.uiPresenter.add(newAttendee);
        }
        if (newAttendee.isJavaee()) {
            this.javaeePresenter.add(newAttendee);
        }
    }

    void loadFromStore() {
        this.bootstrapPresenter.clearAll();
        this.effectivePresenter.clearAll();
        this.architecturePresenter.clearAll();
        this.uiPresenter.clearAll();
        this.javaeePresenter.clearAll();
        List<Attendee> all = service.all();
        for (Attendee attendee : all) {
            add(attendee);
        }
    }

    void registerAttendeeListeners() {
        ChangeListener<Attendee> deletionListener = new ChangeListener<Attendee>() {
            @Override
            public void changed(ObservableValue<? extends Attendee> observable, Attendee oldValue, Attendee newValue) {
                if (newValue != null) {
                    service.remove(newValue);
                    loadFromStore();
                }
            }
        };
        ChangeListener<Attendee> selectionListener = new ChangeListener<Attendee>() {
            @Override
            public void changed(ObservableValue<? extends Attendee> observable, Attendee oldValue, Attendee newValue) {
                selectedAttendee.set(newValue);
            }
        };
        this.effectivePresenter.deletedAttendeeProperty().addListener(deletionListener);
        this.effectivePresenter.selectedAttendeeProperty().addListener(selectionListener);

        this.bootstrapPresenter.deletedAttendeeProperty().addListener(deletionListener);
        this.bootstrapPresenter.selectedAttendeeProperty().addListener(selectionListener);

        this.architecturePresenter.deletedAttendeeProperty().addListener(deletionListener);
        this.architecturePresenter.selectedAttendeeProperty().addListener(selectionListener);

        this.javaeePresenter.deletedAttendeeProperty().addListener(deletionListener);
        this.javaeePresenter.selectedAttendeeProperty().addListener(selectionListener);

        this.uiPresenter.deletedAttendeeProperty().addListener(deletionListener);
        this.uiPresenter.selectedAttendeeProperty().addListener(selectionListener);
    }

    public ObjectProperty<Attendee> selectedAttendeeProperty() {
        return selectedAttendee;
    }
}
