package interceptors;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import beans.User;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext(); // 取得被拦截的ActionContext
		Map session = ctx.getSession(); // 获得session
		String returnUrl = ctx.getName(); // 获取请求的action名称
		String queryInfo = ServletActionContext.getRequest().getQueryString(); // 参数
		if (queryInfo != null && (!queryInfo.equals(""))) {
			returnUrl += "?" + queryInfo; // 得到类似"edit?id=1"结果
		}
		session.put("returnUrl", returnUrl); // 存在session的returnUrl属性中
		// 接后
		ActionSupport action = (ActionSupport) invocation.getAction(); // 获得拦截的Action
		User user = (User) session.get("user"); // 这个user属性是在LoginAction中定义的
		// 如果没有登录或用户名不是admin，则返回重新登录
		if (user == null) {
			action.addActionError("您还没有登录，请登录系统");
		} else if (!user.getUsername().equals("admin")) {
			action.addActionError("您没有修改的权限");
		} else {
			return invocation.invoke(); // 符合条件后才运行被拦截action
		}
		return Action.LOGIN; // 不符合条件返回"login"
	} // end intercept
} // end class