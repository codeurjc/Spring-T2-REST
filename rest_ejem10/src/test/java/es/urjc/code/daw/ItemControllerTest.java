package es.urjc.code.daw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemControllerTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void test() {

		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/anuncio", new Item("desc", false), ResponseEntity.class));
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/anuncio",
                Item.class)).isNotNull();
	}

}
