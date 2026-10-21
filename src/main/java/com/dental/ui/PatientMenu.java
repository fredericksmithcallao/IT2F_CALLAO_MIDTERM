package com.dental.ui;

import com.dental.dao.PatientDao;
import com.dental.model.PatientEntity;

import java.util.List;
import java.util.Scanner;

public class PatientMenu {
    private static final String LINE = "=".repeat(52);
    private final Scanner scanner = new Scanner(System.in);
    private final PatientDao dao = new PatientDao();

    public void start() {
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> recordTreatment();
                case "2" -> viewRecords();
                case "3" -> {
                    System.out.println("\nExiting system... Thank you!");
                    running = false;
                }
                default -> System.out.println("\nERROR: Invalid choice. Please enter 1, 2, or 3.\n");
            }
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println(LINE);
        System.out.println("          DENTAL TREATMENT PATIENT REGISTRY");
        System.out.println(LINE);
        System.out.println("[1] Record Patient Treatment");
        System.out.println("[2] View Patient Records");
        System.out.println("[3] Exit System");
        System.out.println(LINE);
    }

    private void recordTreatment() {
        System.out.println("\n--- RECORD PATIENT TREATMENT ---");

        String name;
        while (true) {
            System.out.print("Enter Patient Name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("ERROR: Name cannot be empty.");
        }

        String treatment;
        while (true) {
            System.out.print("Enter Treatment (CLEANING / EXTRACTION / FILLING): ");
            treatment = scanner.nextLine().trim().toUpperCase();
            if (treatment.equals("CLEANING") || treatment.equals("EXTRACTION") || treatment.equals("FILLING")) break;
            System.out.println("ERROR: Treatment must be CLEANING, EXTRACTION, or FILLING.");
        }

        double amount;
        while (true) {
            System.out.print("Enter Amount Due: ");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                if (amount >= 0) break;
                System.out.println("ERROR: Amount cannot be negative.");
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number (e.g., 1500.00).");
            }
        }

        String payment;
        while (true) {
            System.out.print("Enter Payment Status (PAID / PENDING): ");
            payment = scanner.nextLine().trim().toUpperCase();
            if (payment.equals("PAID") || payment.equals("PENDING")) break;
            System.out.println("ERROR: Payment status must be PAID or PENDING.");
        }

        PatientEntity p = new PatientEntity(name, treatment, amount, payment);
        if (dao.insert(p)) {
            System.out.println("\nSUCCESS: Patient treatment recorded!\n");
        } else {
            System.out.println("\nERROR: Failed to record patient treatment.\n");
        }
    }

    private void viewRecords() {
        List<PatientEntity> list = dao.findAll();
        String border = "+----+--------------------+------------------+------------+----------------+";
        System.out.println("\n" + "=".repeat(75));
        System.out.println("                           DENTAL PATIENT RECORDS");
        System.out.println("=".repeat(75));
        System.out.println(border);
        System.out.printf("| %-2s | %-18s | %-16s | %-10s | %-14s |%n",
                "ID", "PATIENT NAME", "TREATMENT", "AMOUNT DUE", "PAYMENT");
        System.out.println(border);
        for (PatientEntity p : list) {
            System.out.printf("| %-2d | %-18s | %-16s | %-10.2f | %-14s |%n",
                    p.getId(), p.getPatientName(), p.getTreatment(), p.getAmountDue(), p.getPayment());
        }
        System.out.println(border);
        System.out.println("Total Records Displayed: " + list.size() + "\n");
    }
}
