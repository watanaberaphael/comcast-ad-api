package com.rnaka.comcast.ad.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rnaka.comcast.ad.controller.dto.MessageDTO;
import com.rnaka.comcast.ad.enumeration.MessageEnum;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Naka on 9/26/17
 * watanabe.raphael@gmail.com
 *
 *
 *  Create Ad Campaign via HTTP POST
 *
 * - A user should be able to create an ad campaign by sending a POST request to the ad server at http://<host>/ad.
 * - Only one active campaign can exist for a given partner.
 *          If an error is encountered, the ad server must return an appropriate response and indicate the problem
 *          to the user.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAdCampaignViaPOSTTest {

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
     * Verifies if ad can be created
     *
     * @throws Exception
     */
    @Test
    public void shouldInsert() throws Exception {

        Ad ad = MockUtils.getAdMock();

        HttpEntity<Ad> httpEntity = new HttpEntity<>(ad);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("Ad was created with success."));
    }

    /**
     * Verifies with multiple inserts of different partners
     * @throws Exception
     */
    @Test
    public void shouldInsert2nd() throws Exception {

        Ad ad = MockUtils.getAdMock();

        // 1st creation - It can be created - success
        HttpEntity<Ad> httpEntity1 = new HttpEntity<>(ad);
        ResponseEntity<MessageDTO> response1 = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity1, MessageDTO.class);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals("Ad was created with success.", response1.getBody().getMessage());


        // 2nd creation - It can be created because that add is of other partner - success
        ad = MockUtils.getAdMock();

        HttpEntity<Ad> httpEntity2 = new HttpEntity<>(ad);
        ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity2, String.class);

        MessageDTO messageDTO = new MessageDTO("Ad was created with success.", null);

        // Compare JSON response
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals(jsonTester.write(messageDTO).getJson(), response2.getBody());
    }

    /**
     * Verifies when it is already an active ad
     * @throws Exception
     */
    @Test
    public void shouldNotInsert2nd() throws Exception {

        Ad ad = MockUtils.getAdMock();

        HttpEntity<Ad> httpEntity = new HttpEntity<>(ad);

        // 1st creation - It can be created - success
        ResponseEntity<MessageDTO> response = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity, MessageDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Ad was created with success.", response.getBody().getMessage());


        // 2nd creation - It cannot be created because there is already an active ad - fail
        ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity, String.class);

        MessageDTO messageDTO = new MessageDTO(MessageEnum.B1000.getMessage(), MessageEnum.B1000.getId());

        // Compare JSON response
        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        assertEquals(jsonTester.write(messageDTO).getJson(), response2.getBody());
    }

    /**
     * Verifies with multiple inserts of different partners
     * @throws Exception
     */
    @Test
    public void shouldInsert2ndAfterActiveExpires() throws Exception {

        Ad ad = MockUtils.getAdMock();
        ad.setDuration(1);

        // 1st creation - It can be created - success
        HttpEntity<Ad> httpEntity1 = new HttpEntity<>(ad);
        ResponseEntity<MessageDTO> response1 = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity1, MessageDTO.class);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals("Ad was created with success.", response1.getBody().getMessage());

        // Wait for expiration
        Thread.sleep(1000);

        // 2nd creation - It cannot be created because the active ad has expired - success
        ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity1, String.class);

        MessageDTO messageDTO = new MessageDTO("Ad was created with success.", null);

        // Compare JSON response
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals(jsonTester.write(messageDTO).getJson(), response2.getBody());
    }

}
