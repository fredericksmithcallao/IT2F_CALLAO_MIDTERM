package com.dental.model;

public class PatientEntity {
    private int id;
    private String patientName;
    private String treatment;   // CLEANING, EXTRACTION, FILLING
    private double amountDue;
    private String payment;     // PAID, PENDING

    public PatientEntity() {}

    public PatientEntity(String patientName, String treatment, double amountDue, String payment) {
        this.patientName = patientName;
        this.treatment = treatment;
        this.amountDue = amountDue;
        this.payment = payment;
    }

    public PatientEntity(int id, String patientName, String treatment, double amountDue, String payment) {
        this(patientName, treatment, amountDue, payment);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public double getAmountDue() { return amountDue; }
    public void setAmountDue(double amountDue) { this.amountDue = amountDue; }
    public String getPayment() { return payment; }
    public void setPayment(String payment) { this.payment = payment; }
}
