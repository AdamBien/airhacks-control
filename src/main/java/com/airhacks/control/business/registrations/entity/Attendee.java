package com.airhacks.control.business.registrations.entity;

import java.util.Objects;
import java.util.UUID;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author adam-bien.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Attendee.findAll, query = "SELECT a from Attendee a")
})
public class Attendee {

    public final static String PREFIX = "com.airhacks.control.business.registrations.entity.Attendee.";
    public final static String findAll = PREFIX + "findAll";
    private StringProperty idProperty;
    private StringProperty firstNameProperty;
    private StringProperty lastNameProperty;
    private StringProperty companyProperty;
    private BooleanProperty paidProperty;
    private BooleanProperty bootstrapProperty;
    private BooleanProperty effectiveProperty;
    private BooleanProperty architectureProperty;
    private BooleanProperty uiProperty;
    private BooleanProperty javaeeProperty;

    public Attendee() {
        this.idProperty = new SimpleStringProperty(UUID.randomUUID().toString());
        this.firstNameProperty = new SimpleStringProperty();
        this.lastNameProperty = new SimpleStringProperty();
        this.companyProperty = new SimpleStringProperty();
        this.paidProperty = new SimpleBooleanProperty();
        this.bootstrapProperty = new SimpleBooleanProperty();
        this.effectiveProperty = new SimpleBooleanProperty();
        this.architectureProperty = new SimpleBooleanProperty();
        this.uiProperty = new SimpleBooleanProperty();
        this.javaeeProperty = new SimpleBooleanProperty();
    }

    public Attendee(String firstName, String lastName, String company, boolean paid) {
        this();
        this.firstNameProperty.set(firstName);
        this.lastNameProperty.set(lastName);
        this.companyProperty.set(company);
        this.paidProperty.set(paid);
    }

    @Id
    public String getId() {
        return idProperty.get();
    }

    public void setId(String id) {
        this.idProperty.set(id);
    }

    public String getFirstName() {
        return firstNameProperty.get();
    }

    public void setFirstName(String firstName) {
        this.firstNameProperty.set(firstName);
    }

    public String getLastName() {
        return lastNameProperty.get();
    }

    public void setLastName(String lastName) {
        this.lastNameProperty.set(lastName);
    }

    public String getCompany() {
        return companyProperty.get();
    }

    public void setCompany(String company) {
        this.companyProperty.set(company);
    }

    public boolean isPaid() {
        return paidProperty.get();
    }

    public void setPaid(boolean paid) {
        this.paidProperty.set(paid);
    }

    public boolean isBootstrap() {
        return bootstrapProperty.get();
    }

    public void setBootstrap(boolean bootstrap) {
        this.bootstrapProperty.set(bootstrap);
    }

    public boolean isEffective() {
        return effectiveProperty.get();
    }

    public void setEffective(boolean effective) {
        this.effectiveProperty.set(effective);
    }

    public boolean isArchitecture() {
        return architectureProperty.get();
    }

    public void setArchitecture(boolean architecture) {
        this.architectureProperty.set(architecture);
    }

    public boolean isUi() {
        return uiProperty.get();
    }

    public void setUi(boolean ui) {
        this.uiProperty.set(ui);
    }

    public boolean isJavaee() {
        return javaeeProperty.get();
    }

    public void setJavaee(boolean javaee) {
        this.javaeeProperty.set(javaee);
    }

    public StringProperty firstNameProperty() {
        return this.firstNameProperty;
    }

    public StringProperty lastNameProperty() {
        return this.lastNameProperty;
    }

    public StringProperty companyProperty() {
        return this.companyProperty;
    }

    public BooleanProperty paidProperty() {
        return this.paidProperty;
    }

    public BooleanProperty bootstrapProperty() {
        return this.bootstrapProperty;
    }

    public BooleanProperty effectiveProperty() {
        return this.effectiveProperty;
    }

    public BooleanProperty architectureProperty() {
        return this.architectureProperty;
    }

    public BooleanProperty uiProperty() {
        return this.uiProperty;
    }

    public BooleanProperty javaeeProperty() {
        return this.javaeeProperty;
    }
}
