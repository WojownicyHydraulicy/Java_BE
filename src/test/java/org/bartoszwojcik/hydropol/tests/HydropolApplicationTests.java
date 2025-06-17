package org.bartoszwojcik.hydropol.tests;

import org.bartoszwojcik.hydropol.tests.config.DotenvInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = DotenvInitializer.class)
public class HydropolApplicationTests {

    @Test
    public void contextLoads() {

    }
}
