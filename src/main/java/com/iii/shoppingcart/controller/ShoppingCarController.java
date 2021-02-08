package com.iii.shoppingcart.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iii.shoppingcart.dao.ProductDAO;
import com.iii.shoppingcart.dao.ShoppingCarDAO;
import com.iii.shoppingcart.domain.ShoppingCar;

@Controller
public class ShoppingCarController {
	@Autowired
	private ProductDAO pDAO;
	@Autowired
	private ShoppingCarDAO shopDAO;

//	@RequestMapping(path="/AddShoppingCar",method=RequestMethod.GET)
//	public String pcocessTestMainGo() {
//		return "AddShoppingCar";
//	}

//	@GetMapping(path = "/AddShoppingCar")
//	public String addAction(@RequestParam(name = "id") Long id, 
//			@RequestParam(name = "name") String Name,
//			@RequestParam(name = "price") int price,
//			@RequestParam(name = "qty") int qty) {
//		ShoppingCar sBean = new ShoppingCar();	
//		sBean.setId(id);
//		sBean.setName(Name);
//		sBean.setPrice(price);
//		sBean.setQty(qty);
//		shopDAO.save(sBean);
//		return "ShoppingCarList";
//	}
	
//
//	@GetMapping(value = "/AddShoppingCar")
//	public Map<String, Object> addShoppingCar(String name, int price, int qty) {
//		ShoppingCar shoppingCar = shopDAO.getShoppingCar(name, price, qty);
//		
//			ShoppingCar shoppingCar1 = new ShoppingCar();
//			shoppingCar1.setName(name);
//			shoppingCar1.setPrice(price);
//			shoppingCar1.setQty(qty);			
//			shopDAO.addShoppingCar(shoppingCar1);
//
//		return null;
//	}

//	 新增購物車
	@GetMapping(value = "/AddShoppingCar")
	public ModelAndView processFormCreate(@ModelAttribute ShoppingCar car) throws SQLException {
		ModelAndView model = new ModelAndView("redirect:/AllProduct");
		shopDAO.save(car);
		model.addObject(car);
		System.out.println("新增購物車");
		return model;
	}

	// 顯示購物
	@GetMapping(value = "/ShoppingCarList")
//	@RequestMapping(value = { "/ShoppingCarList", "/" }, method = RequestMethod.GET)
	public ModelAndView retrieveShoppingCar() throws SQLException {

		Iterable<ShoppingCar> shoppingcar = shopDAO.findAll();
		ModelAndView model = new ModelAndView("ShoppingCarList");
		model.addObject("shoppingcars", shoppingcar);

		return model;
	}

	// 刪除
	@GetMapping(value = "/DeleteCarItem")
//	@RequestMapping(value = "/DeleteCarItem", method = RequestMethod.GET)
	public ModelAndView deleteCarItem(
			@RequestParam(value = "id", required = false, defaultValue = "1") ShoppingCar id) {
		ModelAndView model = new ModelAndView("redirect:/ShoppingCarList");
		shopDAO.delete(id);
		System.out.println("刪除購物車商品");
		return model;
	}
}
