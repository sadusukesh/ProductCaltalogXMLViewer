package com.example.Controller;

import com.example.Model.Product;
import com.example.util.XMLParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductServlet")
@MultipartConfig
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {


        Part filePart = request.getPart("xmlFile");


        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("error", "Please select an XML file.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

           InputStream inputStream = filePart.getInputStream();


        XMLParser parser = new XMLParser();
        List<Product> products = parser.parse(inputStream);

        HttpSession session = request.getSession();

        session.setAttribute("products", products);

        // Send data to JSP
        request.setAttribute("products", products);

        // Display products
        request.getRequestDispatcher("products.jsp")
              .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Product> products =
                (List<Product>) session.getAttribute("products");

        String category = request.getParameter("category");

        if (category != null && !category.equals("All")) {

            List<Product> filtered = new ArrayList<>();

            for (Product p : products) {
                if (p.getCategory().equals(category)) {
                    filtered.add(p);
                }
            }

            request.setAttribute("products", filtered);

        } else {

            request.setAttribute("products", products);
        }

        request.getRequestDispatcher("products.jsp")
                .forward(request, response);
    }
}