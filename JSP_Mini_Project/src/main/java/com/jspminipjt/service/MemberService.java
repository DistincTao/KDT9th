package com.jspminipjt.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.member.MemberFactory;

public interface MemberService {
	public abstract MemberFactory executeService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
