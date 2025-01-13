package pl.wk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.wk.config.datasource.DataSourceAdmin;
import pl.wk.config.datasource.DataSourceAuth;
import pl.wk.config.datasource.DataSourceAsm;
import pl.wk.config.datasource.DataSourceRdr;

@Configuration
@Import({DataSourceAdmin.class, DataSourceAsm.class, DataSourceAuth.class
        , DataSourceRdr.class, AtomikosConfig.class})
public class RootConfig {
}
