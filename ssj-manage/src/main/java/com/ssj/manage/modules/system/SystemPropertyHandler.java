package com.ssj.manage.modules.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.Properties;

public class SystemPropertyHandler {
	protected static Properties localProperties;

	private Resource location;

	private static Logger logger = LoggerFactory.getLogger(SystemPropertyHandler.class);

	public void setLocation(Resource location) {
		this.location = location;
	}

	public void init() {
		logger.info("开始加载自定义系统配置信息");

		try {
			loadProperties(localProperties);
		} catch (IOException e) {
			logger.error("load errormsg property error!");
		}
		logger.info("系统配置信息加载完成");

	}

	/**
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		String value = localProperties.getProperty(key);
		if (StringUtils.isEmpty(value)) {
			logger.warn("Warning! No Message Had Defined For errorId:" + key);
		}
		return value;
	}

	/**
	 * @param props
	 * @throws IOException
	 */
	private void loadProperties(Properties props) throws IOException {
		if (this.location != null) {
			if (logger.isInfoEnabled()) {
				logger.info("Loading properties file from " + location);
			}

			try {
				this.localProperties = PropertiesLoaderUtils.loadProperties(location);
			} catch (IOException e) {
				throw e;
			}
		}
	}

}
