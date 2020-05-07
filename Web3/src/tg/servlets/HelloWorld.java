package tg.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet{

	ServletConfig config;
	
	/* 서블릿의 생명주기  
	 * 서블릿의 생성과 실행, 소멸
	 * init(), service(), destroy() */
	
	//init() : 초기화 작업, (주소 호출 시)처음이자 마지막으로 한 번만 실행된다. 가장 빠르게 무조건 수행된다.
	@Override
	public void init(ServletConfig config) throws ServletException {	//*중요*
		
		System.out.println("init(ServletConfig config) 호출됨");
		this.config = config;
	}

	//service() : 실질적으로 서비스 작업을 수행
	@Override
	public void service(ServletRequest req, ServletResponse res) 
			throws ServletException, IOException {	//*중요*
			 
		System.out.println("service() 호출됨");
			
	}
	
	//destroy() : 딱 한 번만 수행됨, init이 수행되어야지만 수행할 수 있다.
	//서블릿 컨테이너가 종료되거나 웹 애플리케이션이 멈출 때, 또는 해당 서블릿을 비활성화 시킬 때 호출
	@Override
	public void destroy() {		//*중요*

		System.out.println("destroy() 호출됨");
	}

	@Override
	public ServletConfig getServletConfig() {
		 
		System.out.println("getServletConfig() 호출됨");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		 
		System.out.println("getServletInfo() 호출됨");
		return "getServletInfo() myname=war";
	}


	

	
}
