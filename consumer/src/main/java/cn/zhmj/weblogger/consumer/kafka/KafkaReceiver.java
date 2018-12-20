package cn.zhmj.weblogger.consumer.kafka;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {
	//private final static Logger log = LoggerFactory.getLogger(KafkaReceiver.class);

	@Autowired
	private SimpMessagingTemplate template;
	
	@KafkaListener(topics = { "logs" })
	public void listen(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {
			Object message = kafkaMessage.get();
			template.convertAndSend("/topic/notice", message);
			//log.info("<<<<<<<<<<<<<<<<<<< {}", message);
		}
	}

}
