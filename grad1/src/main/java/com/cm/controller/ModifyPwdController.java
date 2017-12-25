package com.cm.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * ÐÞ¸ÄÃÜÂë
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="modifyPwd")
public class ModifyPwdController {

	@RequestMapping("toModifyUI")
	public String toModifyUI(){
		return "modifyPwd";
	}
	
	public HashMap<String, Object> modifyPwd(){
		return null;
	}
}
