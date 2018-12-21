package cn.zhmj.weblogger.producer.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogIpConfig extends ClassicConverter {
	private static final Logger log = LoggerFactory.getLogger(LogIpConfig.class);

	@Override
	public String convert(ILoggingEvent event) {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("获取日志Ip异常", e);
		}
		return null;
	}
}
