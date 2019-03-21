package org.panch.servlets;


import org.panch.dao.DepartmentDAO;
import org.panch.dao.EmployeeDAO;
import org.panch.entity.Department;
import org.panch.entity.Employee;
import org.panch.util.UserType;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        int depid = 1;
        int empid = -1;
        String sort = "empid";
        String order = "ASC";
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(request.getParameter("recordsPerPage") != null){
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }
        if(request.getParameter("depid") != null){
            depid = Integer.parseInt(request.getParameter("depid"));
        }
        if(request.getParameter("empid") != null){
            empid = Integer.parseInt(request.getParameter("empid"));
        }
        if(request.getParameter("sort") != null){
            sort = request.getParameter("sort");
            request.getSession().setAttribute("sort", sort);
            order = getOrder(sort, request.getSession());
            request.getSession().setAttribute("order", order);
        }
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Department> departments = departmentDAO.getAll();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        if(empid > 0 && UserType.Admin.equals(request.getSession().getAttribute("userType"))){
            employeeDAO.delete(empid);
        }
        List<Employee> employees = employeeDAO.viewAllEmployees(depid, (page-1)*recordsPerPage, recordsPerPage, sort, order);
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

    private String getOrder(String sort, HttpSession session){
        String sort1 = (String)session.getAttribute("sort");
        String order = (String)session.getAttribute("order");
        if(sort1 == null || order == null){
            return "DESC";
        }
        if(sort1.equals(sort)){
            if(order.equals("ASC")) {
                return "DESC";
            } else {
                return "ASC";
            }
        } else {
            return "ASC";
        }
    }
}