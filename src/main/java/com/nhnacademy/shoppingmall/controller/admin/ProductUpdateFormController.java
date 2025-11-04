package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/product/update.do")
@Controller
public class ProductUpdateFormController implements BaseController {
    private final ProductService productService;

    @Autowired
    public ProductUpdateFormController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productIdStr = req.getParameter("productId");
        if (Objects.isNull(productIdStr)) {
            return "redirect:/admin/products.do";
        }

        int productId = Integer.parseInt(productIdStr);
        Product product = productService.getProduct(productId);

        req.setAttribute("product", product);

        return "admin/product/product_form";
    }
}