package com.iii.shoppingcart.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iii.shoppingcart.dao.ProductDAO;
import com.iii.shoppingcart.domain.Product;



@Controller
public class ProductController {
	@Autowired
	ProductDAO pDAO;
	@GetMapping(value = "/AddProduct")
//	@RequestMapping(value = "/AddProduct", method = RequestMethod.GET)
	public ModelAndView openFormCreate() {
		ModelAndView model = new ModelAndView("AddProduct");
		return model;
	}
	@PostMapping(value = "/AddProduct")
//	@RequestMapping(value = "/AddProduct", method = RequestMethod.POST)
	public ModelAndView processFormCreate(@ModelAttribute Product pro) throws SQLException {
		ModelAndView model = new ModelAndView("redirect:/AllProduct");

		pDAO.save(pro);
		model.addObject(pro);
		System.out.println("新增商品成功！");
		return model;
	}

	// 顯示資料
	@GetMapping(value = "/AllProduct")
//	@RequestMapping(value = { "/AllProduct", "/" }, method = RequestMethod.GET)
	public ModelAndView retrieveProducts() throws SQLException {

		Iterable<Product> products = pDAO.findAll();
		ModelAndView model = new ModelAndView("ProductList");
		model.addObject("products", products);
		return model;
	}

	// 修改資料
	@RequestMapping(value = "/ProductUpdate", method = RequestMethod.GET)
	public ModelAndView openFormUpdate(@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("ProductUpdate");
		Product product = pDAO.findById(id).get();
		model.addObject(product);
		return model;
	}

	// 修改後回存
	@RequestMapping(value = "/ProductUpdate", method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Product pro) throws SQLException {
		ModelAndView model = new ModelAndView("redirect:/AllProduct");
		pDAO.save(pro);
		return model;
	}

	// 刪除
	@RequestMapping(value = "/ProductDelete", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam(value = "id", required = false, defaultValue = "1") Product id) {
		ModelAndView model = new ModelAndView("redirect:/AllProduct");
		pDAO.delete(id);
		System.out.println("刪除商品");
		return model;
	}
}
