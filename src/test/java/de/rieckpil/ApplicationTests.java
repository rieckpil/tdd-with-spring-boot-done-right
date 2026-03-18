package de.rieckpil;

import de.rieckpil.config.LocalDevTestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(LocalDevTestcontainersConfig.class)
class ApplicationTests {

  @Test
  void contextLoads() {}
}
