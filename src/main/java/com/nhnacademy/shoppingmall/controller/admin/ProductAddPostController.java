package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/addAction.do")
@Controller
public class ProductAddPostController implements BaseController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductAddPostController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String modelName = req.getParameter("modelName");
        String modelNumber = req.getParameter("modelNumber");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int unitPrice = Integer.parseInt(req.getParameter("unitPrice"));
        String productImage = req.getParameter("productImage");
        String description = req.getParameter("description");

        Category category = categoryService.getCategory(categoryId);
        Product newProduct = new Product(0, modelNumber, modelName, productImage, unitPrice, description, category);
        productService.saveProduct(newProduct);
        return "redirect:/admin/products.do";
    }
}