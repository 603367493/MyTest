package com.cm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/toIndexUI.do")
	public String toHomeUI(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}

}
