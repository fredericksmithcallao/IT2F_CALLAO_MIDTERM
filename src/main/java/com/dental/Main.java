package com.dental;

import com.dental.dao.DatabaseConnection;
import com.dental.ui.PatientMenu;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.initialize();
        new PatientMenu().start();
    }
}
