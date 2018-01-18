package cn.krim.gp.front.interceptor;

import java.time.Clock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.krim.gp.front.model.users.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User u =(User) request.getSession().getAttribute("User");
		if(u==null){
			request.getRequestDispatcher("login").forward(request, response); 
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Long usedTime = Clock.systemDefaultZone().millis()-(long)request.getAttribute("requestStart");
		log.info(String.format("请求处理完毕，共耗时:%d", usedTime));

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
