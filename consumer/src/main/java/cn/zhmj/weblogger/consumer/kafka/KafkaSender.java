package cn.zhmj.weblogger.consumer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send() {
		kafkaTemplate.send("logs", "消息测试");
	}
}
