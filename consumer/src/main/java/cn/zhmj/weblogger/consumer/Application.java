package cn.zhmj.weblogger.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import cn.zhmj.weblogger.consumer.kafka.KafkaSender;

/**
 * @author
 * @date 2018年3月22日
 * @version 0.0.1
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableWebSocket
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		// 用于消息测试
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		KafkaSender kafkaSender = context.getBean(KafkaSender.class);
		kafkaSender.send();
		logger.info("启动成功。");
	}
}
