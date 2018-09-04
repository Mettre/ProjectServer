package com.example.myproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 创建者：梁建军
 * 创建日期： 2017/11/27
 * 创建时间： 14:52
 * ExceptionHandle
 * 版本：1.0
 * 说明：处理controller异常的
 */
@RestControllerAdvice
public class ExceptionHandle {

	public static final String TAG = "--" + ExceptionHandle.class.getSimpleName();
	private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {}

	/**
	 * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("author", "Magical Sam");
	}

	/**
	 * 全局异常捕捉处理
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Map errorHandler(Exception ex) {
		Map map = new HashMap();
		map.put("code", 100);
		map.put("msg", ex.getMessage());
		return map;
	}
}
