package com.rnaka.comcast.ad.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rnaka.comcast.ad.controller.dto.MessageDTO;
import com.rnaka.comcast.ad.model.Ad;
import com.rnaka.comcast.ad.test.utils.MockUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 *
 *
 *  Add a URL to return a list of all campaigns as JSON.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlusListAllAdTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<Object> jsonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
    }

    /**
     * Verifies if ad can be found
     *
     * @throws Exception
     */
    @Test
    public void shouldList() throws Exception {

        List<Ad> ads = MockUtils.getListAdMock();
        HttpEntity<Ad> httpEntity;
        ResponseEntity<MessageDTO> responsePost;

        for (Ad item : ads) {
            httpEntity = new HttpEntity<>(item);
            responsePost = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity, MessageDTO.class);
            assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
            assertEquals("Ad was created with success.", responsePost.getBody().getMessage());
        }

        // Get by partner_id
        ResponseEntity<String> responseGet = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.GET, null, String.class);

        // Compare JSON response
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertTrue(responseGet.getBody().contains(jsonTester.write(ads.get(0)).getJson()));
        assertTrue(responseGet.getBody().contains(jsonTester.write(ads.get(1)).getJson()));
        assertTrue(responseGet.getBody().contains(jsonTester.write(ads.get(2)).getJson()));
    }

}
