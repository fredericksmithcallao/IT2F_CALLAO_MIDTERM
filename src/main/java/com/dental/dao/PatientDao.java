package com.dental.dao;

import com.dental.model.PatientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {

    public boolean insert(PatientEntity p) {
        String sql = "INSERT INTO patients (patient_name, treatment, amount_due, payment) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getPatientName());
            ps.setString(2, p.getTreatment());
            ps.setDouble(3, p.getAmountDue());
            ps.setString(4, p.getPayment());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }

    public List<PatientEntity> findAll() {
        List<PatientEntity> list = new ArrayList<>();
        String sql = "SELECT id, patient_name, treatment, amount_due, payment FROM patients ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new PatientEntity(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getString("treatment"),
                        rs.getDouble("amount_due"),
                        rs.getString("payment")));
            }
        } catch (SQLException e) {
            System.out.println("Query error: " + e.getMessage());
        }
        return list;
    }
}
