package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/product/delete.do")
@Controller
public class ProductDeleteController implements BaseController {
    private final ProductService productService;

    @Autowired
    public ProductDeleteController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productIdStr = req.getParameter("productId");

        if (Objects.isNull(productIdStr)) {
            log.warn("productId parameter is null.");
            return "redirect:/admin/products.do";
        }
        try {
            int productId = Integer.parseInt(productIdStr);
            productService.deleteProduct(productId);
            log.info("Product deleted successfully: productId={}", productId);
        } catch (NumberFormatException e) {
            log.error("Invalid productId format: {}", productIdStr);
        }
        return "redirect:/admin/products.do";
    }
}