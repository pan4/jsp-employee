package org.panch.servlets;


import org.panch.dao.DepartmentDAO;
import org.panch.dao.EmployeeDAO;
import org.panch.entity.Department;
import org.panch.entity.Employee;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        int depid = 1;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(request.getParameter("recordsPerPage") != null){
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if(request.getParameter("depid") != null){
            depid = Integer.parseInt(request.getParameter("depid"));
        }
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departments = departmentDAO.getAll();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = employeeDAO.viewAllEmployees(depid, (page-1)*recordsPerPage, recordsPerPage);
        int noOfRecords = employeeDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("departmentList", departments);
        request.setAttribute("employeeList", employees);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("depid", depid);
        RequestDispatcher view = request.getRequestDispatcher("display.jsp");
        view.forward(request, response);
    }
}