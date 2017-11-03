package xmx.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@ComponentScan(basePackages = "xmx")
public class SpringMybatisConfiguration {

	@Bean
	public BasicDataSource dataSource() throws IOException {
		BasicDataSource source = new BasicDataSource();
		
		Resource resource = new ClassPathResource("jdbc.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		
		source.setDriverClassName(props.getProperty("jdbc.driver"));
		source.setUrl(props.getProperty("jdbc.url"));
		source.setUsername(props.getProperty("jdbc.username"));
		source.setPassword(props.getProperty("jdbc.password"));
		source.setInitialSize(Integer.parseInt(props.getProperty("jdbc.initialSize")));
		source.setMaxActive(Integer.parseInt(props.getProperty("jdbc.maxActive")));
		source.setMaxIdle(Integer.parseInt(props.getProperty("jdbc.maxIdle")));
		source.setMinIdle(Integer.parseInt(props.getProperty("jdbc.minIdle")));
		source.setMaxWait(Integer.parseInt(props.getProperty("jdbc.maxWait")));

		return source;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:xmx/mapping/*.xml"));
		return bean;
	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage("xmx.dao");
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return configurer;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() throws IOException {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource());
		return manager;
	}
}
