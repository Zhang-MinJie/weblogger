package cn.zhmj.weblogger.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value = "/info", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String info() {
		logger.info("<<<<<<<<<<<<<<<  This is test log.");
		return "Test";
	}
	
	@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String error() throws Exception {
		throw new Exception("<<<<<<<<<<<<<<<  This is test log.");
	}
	
	@RequestMapping(value = "/warn", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String warn() {
		logger.warn("<<<<<<<<<<<<<<<  This is test log.");
		return "Test";
	}
}
