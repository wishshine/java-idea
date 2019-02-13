package com.lemeng.lecloud.netdisk.interceptor;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.constants.LoginConstants;
import com.lemeng.lecloud.model.common.exception.SystemException;
import com.lemeng.lecloud.model.user.UserLogin;
import com.lemeng.lecloud.utils.cache.LoginCacheHelper;
import com.lemeng.lecloud.utils.cache.RedisService;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Log LOGGER = LogFactory.getLog(LoginInterceptor.class);

    private static List<String> OUT_URL_LIST = new ArrayList<String>();

    @Autowired
    private RedisService redisService;

    static {
        OUT_URL_LIST.add(LoginConstants.LOGIN_URL);
        OUT_URL_LIST.add(LoginConstants.REGISTER_URL);
        OUT_URL_LIST.add("*.js|*.css|*.png|*.jpg|*.jpeg|*.mp3|*.mp4");
        OUT_URL_LIST.add("*/error");
        OUT_URL_LIST.add("*index.html");
        OUT_URL_LIST.add("*login.html");
        OUT_URL_LIST.add("*/lib/*");
        OUT_URL_LIST.add("*/pages/*");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = (String) request.getParameter(LoginConstants.LOGIN_TOKEN_KEY);
        if (StringUtils.isEmpty(token)) {
            if (this.isOutUrl(request)) {
                return true;
            }
            LOGGER.info("请求地址：" + request.getRequestURI() + "，未找到token，被拦截");
            ResponseData result = ServerInteractionsUtils.getLoginFailReturn("未找到参数token,请重新登录");
            ServerInteractionsUtils.setResponseJSON(response, result);//设置response返回
            return false;
        }
        UserLogin userLogin = null;
        try {
            userLogin = redisService.getUserLogin(token);
        } catch (SystemException e) {
            LOGGER.error(e.getMessage(), e);
            ResponseData result = ServerInteractionsUtils.getFailReturn("系统错误");
            ServerInteractionsUtils.setResponseJSON(response, result);//设置response返回
        }
        if (userLogin == null) {
            ResponseData result = ServerInteractionsUtils.getLoginFailReturn("未登录或登录已失效，请重新登录");
            ServerInteractionsUtils.setResponseJSON(response, result);//设置response返回
        }
        LoginCacheHelper.setCurrentUserLogin(userLogin);// 讲用户信息放到当前线程变量中
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 判断是否为非验证路径
     *
     * @param request
     * @return
     */
    private boolean isOutUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        String path = request.getContextPath();
        String input = url.replace(path, "");
        for (String reg : OUT_URL_LIST) {
            reg = reg.replace("*", ".*");// 替换*变成正则
            if (Pattern.matches(reg, input)) {
                return true;
            }
        }
        return false;
    }

}
