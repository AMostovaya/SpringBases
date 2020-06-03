package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;

@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public String productList(Model model){
        log.info("Product list");
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        log.info("Create new product");
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product){
        log.info("Product saved");
        productRepository.saveProduct(product);
        return "redirect:/product";
    }
}
