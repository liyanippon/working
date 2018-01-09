package com.ifeng.support;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

public class ConfigHolder {
	private Properties prop = new Properties();
	private String config;
	private ServletContext context;

	public void setConfig(String config) {
		this.config = config;
	}

	public void init() {
		System.out.println("ConfigHolder init...");
		if (config == null || "".equals(config)) {
			System.err.println("Can not load config : Config file is empty.");
			return;
		}

		try {
			if (!ResourceUtils.isUrl(config)) {
				config = SystemPropertyUtils.resolvePlaceholders(config);
				config = context.getRealPath(config);
			}
			System.out.println("ConfigHolder loading from file [" + config + "]");

			File file = new File(config);
			if (file.exists()) {
				prop.load(new FileInputStream(file));
			} else {
				System.err.println("Can not load config : File [" + config + "] is not exist.");
			}
			System.out.println("ConfigHolder loaded.");
		} catch (Exception e) {
			System.err.println("Can not load config : " + e.getMessage());
		}
	}

	public String getConfig(String key) {
		return prop.getProperty(key);
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}
