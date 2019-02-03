package org.panch.dao;

import org.panch.entity.Department;
import org.panch.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public static final String QUEARY = "select depid, depname from department";

    public DepartmentDAO() {    }

    private static Connection getConnection() throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        return con;
    }

    public List<Department> getAll(){
        List<Department> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QUEARY);
            while (rs.next()){
                Department department = new Department();
                department.setId(rs.getInt(1));
                department.setName(rs.getString(2));
                list.add(department);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
