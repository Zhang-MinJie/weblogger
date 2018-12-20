package cn.zhmj.weblogger.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebLogsController {
	private static final Logger log = LoggerFactory.getLogger(WebLogsController.class);

	@Autowired
	private SimpMessagingTemplate template;
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index() {
		return "index";
	}

	// 注入其它Service

	// 群聊天
	@MessageMapping("/notice")
	public void notice(String message) {
		// 参数说明 principal 当前登录的用户， message 客户端发送过来的内容
		// principal.getName() 可获得当前用户的username
		log.info("手动消息发送   {}", message);
		// 发送消息给订阅 "/topic/notice" 且在线的用户
		template.convertAndSend("/topic/notice", message);
	}
}
