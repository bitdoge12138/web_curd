package com.chen.web;

import com.chen.pojo.Brand;
import com.chen.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {

    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        System.out.println(id);


        Brand brand = brandService.selectById(Integer.parseInt(id));

        request.setAttribute("brand", brand);

        System.out.println(brand);

        request.getRequestDispatcher("/update.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
