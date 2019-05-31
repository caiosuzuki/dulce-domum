package com.dulcedomum.base;

import com.dulcedomum.SpringBootConfiguracao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguracao.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-teste.properties")
public abstract class TesteDeIntegracao {

    @Autowired
    private DataBaseUtil dataBaseUtil;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Before
    public void configuracaoInicial() {
        dataBaseUtil.clean();
    }
}
