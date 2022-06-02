package com.example.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.example.aop.CustomDruidStatLogFilter;
import com.example.aop.logging.PersistentStatLogger;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfiguration {

    @Bean
    public CustomDruidStatLogFilter customDruidStatLogFilter() {
        return new CustomDruidStatLogFilter();
    }

    /**
     * 配置绑定
     * <p>
     * 这个玩意儿一定得有不然坑爹啊， sql监控的配置加载不进来
     * 感谢{@see https://www.tuicool.com/articles/MVbYriY}  配置类绑定数据源和配置信息。
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource druid() {
        final DruidDataSource druidDataSource = new DruidDataSource();
        //        这个配置以后, sql监控会给这个里写值, 可能导致druid本身提供的sql监控界面没数据
        //        druidDataSource.setStatLogger(new PersistentStatLogger());
        //        增加自己的过滤器
        final List<Filter> proxyFilters = druidDataSource.getProxyFilters();
        proxyFilters.add(customDruidStatLogFilter());
        druidDataSource.setProxyFilters(proxyFilters);
        return druidDataSource;
    }
}
