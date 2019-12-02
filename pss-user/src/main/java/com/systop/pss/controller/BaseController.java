package com.systop.pss.controller;

import com.systop.pss.common.error.BusinessException;
import com.systop.pss.common.vo.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class BaseController {
    
    public static final String CONTENT_TYPE_FORMEND="application/x-www-form-urlencoded";
    
    // 定义ExceptionHandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        
        /*if(isAjax(request)){
            ModelAndView mv = new ModelAndView();
            mv.addObject("exception" ,ex);
            mv.addObject("url",request.getRequestURI());
            mv.setViewName("error");
            return null;
        }*/
        
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            return R.error().code(businessException.getErrCode()).message(businessException.getErrMsg());
        }else {
            return R.error();
        }
    }

    public static boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null
                    && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    
    }
    
}
