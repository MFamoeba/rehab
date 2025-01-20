package pl.wk.rehab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.wk.rehab.config.datasource.DataSourceAdmin;
import pl.wk.rehab.config.datasource.DataSourceAuth;
import pl.wk.rehab.config.datasource.DataSourceAsm;
import pl.wk.rehab.config.datasource.DataSourceRdr;

@Configuration
@Import({DataSourceAdmin.class, DataSourceAsm.class, DataSourceAuth.class
        , DataSourceRdr.class, AtomikosConfig.class})
public class RootConfig {
}
