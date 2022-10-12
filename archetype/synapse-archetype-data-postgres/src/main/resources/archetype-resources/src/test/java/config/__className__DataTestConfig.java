package ${package}.config;

import io.americanexpress.synapse.utilities.common.config.UtilitiesCommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({${className}DataConfig.class, UtilitiesCommonConfig.class})
public class ${className}DataTestConfig {

}
