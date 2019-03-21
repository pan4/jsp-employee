package org.panch.dao;

import org.panch.entity.Employee;
import org.panch.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String DELETE_QUERY = "DELETE FROM EMPLOYEE WHERE EMPID = ?";

    Connection connection;
    Statement stmt;
    private int noOfRecords;

    public EmployeeDAO() {
    }

    private static Connection getConnection() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        return con;
    }

    public List<Employee> viewAllEmployees(int depid, int offset, int noOfRecords, String sort, String orderBy) {

        String select_query = "select e.EMPID, e.EMPNAME, e.EMPSALARY from employee e" +
                " where e.depid = " + depid +
                " order by " + sort + " " + orderBy +
                " limit " + noOfRecords +
                " offset " + offset;

        List<Employee> list = new ArrayList<Employee>();
        Employee employee = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(select_query);
            while (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt(1));
                employee.setEmployeeName(rs.getString(2));
                employee.setSalary(rs.getInt(3));
//                employee.setDeptName(rs.getString(4));
                list.add(employee);
            }
            rs.close();
            String count_query = "select count(*) from employee e" +
                    " join department d on (e.depid = d.depid)" +
                    " where d.depid = " + depid;
            rs = stmt.executeQuery(count_query);
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void delete(int empid) {
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(DELETE_QUERY);
            ((PreparedStatement) stmt).setInt(1, empid);
            ((PreparedStatement) stmt).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
