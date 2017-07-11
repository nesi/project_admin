package nz.org.nesi.collaboration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
//@PropertySource("file:E:\\etc\\nesi\\proadmin\\application.properties")

@Configuration
@PropertySource("file:/etc/nesi/proadmin/application.properties")
public class AppConfig {
	@Autowired
	Environment env;
}