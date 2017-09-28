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
 *  Fetch Ad Campaign for a Partner
 *
 * - A partner should be able to get their ad data by sending a GET request to the ad server at http://<host>/ad/
 *   <partner_id>.  Response can be delivered as a JSON object representing the active ad
 * - If the current time is greater than a campaign's creation time + duration, then the server's response should be an
 *   error indicating that no active ad campaigns exist for the specified partner.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FetchAdCampaignForPartnerTest {

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
    public void shouldGet() throws Exception {

        Ad ad = MockUtils.getAdMock();

        // Create new ad
        HttpEntity<Ad> httpEntity = new HttpEntity<>(ad);
        ResponseEntity<MessageDTO> responsePost = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity, MessageDTO.class);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        assertEquals("Ad was created with success.", responsePost.getBody().getMessage());

        // Get by partner_id
        ResponseEntity<String> responseGet = restTemplate.exchange("http://localhost:" + port + "/ad/" + ad.getPartner_id(), HttpMethod.GET, null, String.class);

        // Compare JSON response
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertTrue(responseGet.getBody().equals(jsonTester.write(ad).getJson()));
    }

    /**
     * Verifies when there is no active ad
     * @throws Exception
     */
    @Test
    public void shouldNotGetNoActive() throws Exception {

        Ad ad = MockUtils.getAdMock();
        ad.setDuration(1);

        // Create new ad
        HttpEntity<Ad> httpEntity = new HttpEntity<>(ad);
        ResponseEntity<MessageDTO> responsePost = restTemplate.exchange("http://localhost:" + port + "/ad", HttpMethod.POST, httpEntity, MessageDTO.class);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        assertEquals("Ad was created with success.", responsePost.getBody().getMessage());

        Thread.sleep(1000);

        // Get by partner_id
        ResponseEntity<String> responseGet = restTemplate.exchange("http://localhost:" + port + "/ad/" + ad.getPartner_id(), HttpMethod.GET, null, String.class);

        MessageDTO messageDTO = new MessageDTO(MessageEnum.B1001.getMessage(), MessageEnum.B1001.getId());

        // Compare JSON response
        assertEquals(HttpStatus.BAD_REQUEST, responseGet.getStatusCode());
        assertEquals(responseGet.getBody(), jsonTester.write(messageDTO).getJson());
    }


}
