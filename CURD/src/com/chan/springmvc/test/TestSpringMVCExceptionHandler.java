package com.chan.springmvc.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理器
 *
 * @author Administrator
 */
@ControllerAdvice
public class TestSpringMVCExceptionHandler {

    /**
     * ArithmeticException处理
     *
     * @param ex Exception
     * @return ModelAndView
     */
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView arithmeticExceptionHandler(Exception ex) {
        System.out.println(ex.getMessage());
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        return mv;
    }
}
