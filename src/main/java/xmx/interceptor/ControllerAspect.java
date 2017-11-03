package xmx.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xmx.dao.LogMapper;
import xmx.model.Log;
import xmx.util.IPUtil;

/**
 * 控制器切面
 * 
 * @author The_onE
 *
 */
@Component
@Aspect
public class ControllerAspect {

	@Resource
	private LogMapper logDao;

	@Pointcut("execution (* xmx.controller..*.*(..))")
	private void point() {
	}

	@After("point()")
	public void doAfter(JoinPoint joinPoint) {
		// 当前请求
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = IPUtil.getIP(request); // 请求IP
		String target = joinPoint.getTarget().getClass().getSimpleName(); // 所在类类名
		String method = joinPoint.getSignature().toString(); // 方法名
		Object[] params = joinPoint.getArgs(); // 参数
		StringBuffer param = new StringBuffer();
		for (Object o : params) {
			if (o == null) {
				param.append("?_?");
			} else if (o instanceof String) {
				param.append(o);
			} else if (o.getClass().isPrimitive()) {
				param.append(o);
			} else {
				param.append(o.getClass().getSimpleName());
			}
			param.append(" ");
		}
		String user = ""; // 用户名
		String userType = ""; // 用户类型

		// 生成日志
		Log log = new Log();
		log.setType("controller");
		log.setTarget(target);
		log.setMethod(method);
		log.setParam(param.toString());
		log.setUser(user);
		log.setUserType(userType);
		log.setIp(ip);
		// 记录日志
		logDao.insertSelective(log);
	}
}
