package com.chen.web;

import com.chen.pojo.Brand;
import com.chen.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {

    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");

        String brandName = request.getParameter("brandName");

        String companyName = request.getParameter("companyName");

        String ordered = request.getParameter("ordered");

        String description = request.getParameter("description");

        String status = request.getParameter("status");


        Brand brand = new Brand();
        brand.setId(Integer.parseInt(id));
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));

        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));

        brandService.update(brand);

        request.getRequestDispatcher("/selectAllServlet").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
