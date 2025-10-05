// ProductUpdatePostController.java
package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.service.impl.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/updateAction.do")
public class ProductUpdatePostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId   = Integer.parseInt(req.getParameter("productId"));
        String modelName = req.getParameter("modelName");
        String modelNumber = req.getParameter("modelNumber");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int unitPrice  = Integer.parseInt(req.getParameter("unitPrice"));
        String productImage = req.getParameter("productImage");
        String description  = req.getParameter("description");

        Product productToUpdate = new Product(productId, modelNumber, modelName, productImage, unitPrice, description, categoryId);
        productService.updateProduct(productToUpdate);
        return "redirect:/admin/products.do";
    }
}
