package com.cm.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cm.contant.PropertiesContant;

public class InitDataFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String root = PropertiesUtils.getValue(PropertiesContant.INITDATA_FILEANME, "root");
		String css = PropertiesUtils.getValue(PropertiesContant.INITDATA_FILEANME, "css");

		String js = PropertiesUtils.getValue(PropertiesContant.INITDATA_FILEANME, "js");

		String img = PropertiesUtils.getValue(PropertiesContant.INITDATA_FILEANME, "img");

		request.setAttribute("rootPath",root);
		request.setAttribute("jsPath", js);
		request.setAttribute("cssPath", css);
		request.setAttribute("imgPath", img);
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
