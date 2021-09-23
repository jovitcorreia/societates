package dev.castanhocorreia.societates.controller;

import dev.castanhocorreia.societates.database.Memory;
import dev.castanhocorreia.societates.model.Company;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/update-company")
public class UpdateCompany extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idParameter = request.getParameter("id");
    Long parsedIdParameter = Long.parseLong(idParameter);
    Company companyToBeUpdated = Memory.getCompany(parsedIdParameter);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit-company.jsp");
    request.setAttribute("companyToBeUpdated", companyToBeUpdated);
    requestDispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idParameter = request.getParameter("id");
    Long parsedIdParameter = Long.parseLong(idParameter);
    String newCompanyName = request.getParameter("newCompanyName");
    String newCompanyFoundingDateParameter = request.getParameter("newCompanyFoundingDate");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date newCompanyFoundingDate;
    try {
      newCompanyFoundingDate = simpleDateFormat.parse(newCompanyFoundingDateParameter);
    } catch (ParseException newFoundingDateIsInvalid) {
      throw new ServletException(newFoundingDateIsInvalid);
    }
    Memory.updateCompanyById(parsedIdParameter, newCompanyName, newCompanyFoundingDate);
    response.sendRedirect("list-companies");
  }
}