package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.Category;
import com.geekbrains.geekmarketwinter.data_html.ProdImg;
import com.geekbrains.geekmarketwinter.entites.Product;
import com.geekbrains.geekmarketwinter.entites.ProductImage;
import com.geekbrains.geekmarketwinter.services.CategoryService;
import com.geekbrains.geekmarketwinter.services.ImageSaverService;
import com.geekbrains.geekmarketwinter.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    private  ImageSaverService imageSaverService;

    @Autowired
    public void setImageSaverService(ImageSaverService imageSaverService) {
        this.imageSaverService = imageSaverService;
    }

    @RequestMapping("/")
    public String showHomePage(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @GetMapping("/add")
    public String addNewProduct(Model model) {
        //ProdImg product = new ProdImg();

        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "add-product";
    }

    @PostMapping("/add")
    public String addNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult theBindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String pathToSavedImage = imageSaverService.saveFile(file);
            ProductImage productImage = new ProductImage();
            productImage.setPath(pathToSavedImage);
            productImage.setProduct(product);
            product.addImage(productImage);
        }

        productService.saveProduct(product);

        return "redirect:/";
    }

//    @RequestMapping(path="/add", method=RequestMethod.GET)
//    public String addNewProduct(Model model) {
//        ProdImg product = new ProdImg();
//
//        model.addAttribute("product", product);
//
//        return "add-product";
//    }

//    @RequestMapping(path="/add", method=RequestMethod.POST)
//    public String addNewProduct(ProdImg prodImg) {
//        Category category = categoryService.findByTitle("Телевизоры");
//        prodImg.setCategory(category);
//        prodImg.setFullDescription(prodImg.getTitle());
//        prodImg.setShortDescription(prodImg.getTitle());
//
//        Product product = new Product();
//        product.setTitle(prodImg.getTitle());
//        product.setVendorCode(prodImg.getVendorCode());
//        product.setPrice(prodImg.getPrice());
//        product.setCategory(category);
//        product.setFullDescription(prodImg.getTitle());
//        product.setShortDescription(prodImg.getTitle());
//
//        ProductImage productImage = new ProductImage();
//        productImage.setPath(prodImg.getPath());
//        productImage.setProduct(product);
//        List<ProductImage> images = new ArrayList<>();
//        images.add(productImage);
//
//        product.setImages(images);
//
//        productService.saveProduct(product);
//
//        return "redirect:/";
//    }
}
