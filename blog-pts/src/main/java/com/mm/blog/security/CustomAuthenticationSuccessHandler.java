package com.mm.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: manman
 * @Date: 2018/9/7 11:10
 * @Description:登录成功Handler
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	
	private ObjectMapper objectMapper = new ObjectMapper();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, String> info = new HashMap<String, String>();
		info.put("loginName",userDetails.getUsername());
		info.put("statusCode","1000200");
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		//生成uuid作为token
		String token= UUID.randomUUID().toString();
		Cookie cookie=new Cookie("token",token);
		response.addCookie(cookie);
		request.getSession().setAttribute("token",token);
		response.getWriter().write(objectMapper.writeValueAsString(info));

	}

}
