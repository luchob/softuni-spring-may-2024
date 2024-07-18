package bg.softuni.mobilele.config;

import bg.softuni.mobilele.repository.UserRoleRepository;
import javax.sql.DataSource;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AppConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
      UserRoleRepository userRoleRepository,
      ResourceLoader resourceLoader) {
    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);

    if (userRoleRepository.count() == 0) {
      ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
      populator.addScript(resourceLoader.getResource("classpath:data.sql"));
      initializer.setDatabasePopulator(populator);
    }

    return initializer;
  }

}
