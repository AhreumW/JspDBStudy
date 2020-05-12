package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	FilterConfig config = null;
	
	//처음
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.config = filterConfig;
	}

	//무조건 여기서 처리가 되고 요청이 넘어가기 때문에 
	//공통으로 인코딩을 처리할 수 있다. web.xml에 <filter> 선언&매핑 필수
	@Override	//doFilter 실행부
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		
		String encoding = this.config.getInitParameter("encoding");
		
		request.setCharacterEncoding(encoding);
		
		fc.doFilter(request, response);
		
	}
	
	//끝
	@Override
	public void destroy() {
		
	}

	
}
