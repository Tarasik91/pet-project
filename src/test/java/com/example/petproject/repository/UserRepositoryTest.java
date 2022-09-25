package com.example.petproject.repository;

import com.example.petproject.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

    @LocalServerPort
    private int port;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @DisplayName("Mocking sample repository works.")
    @Test
    public void checkMockingWorks() {
        List<User> sampleEntityList = asList(getUser());
        Page<User> page = new PageImpl<>(sampleEntityList);
        doReturn(page).when(userRepository).findAll(Pageable.unpaged());

        UserRepository sampleRepositoryFromContext = applicationContext.getBean("userRepository", UserRepository.class);
        List<User> sampleEntityListFromContext = sampleRepositoryFromContext.findAll(Pageable.unpaged()).getContent();
        Assertions.assertFalse(sampleEntityListFromContext.isEmpty());
        Assertions.assertEquals("firstnam1e", sampleEntityListFromContext.get(0).getFirstName());
        Assertions.assertEquals("lastname", sampleEntityListFromContext.get(0).getLastName());
        Assertions.assertEquals("username", sampleEntityListFromContext.get(0).getUsername());
    }

    @Test
    public void testCrudOfSampleEntity() {
        List<User> sampleEntityList = asList(getUser());
        Page<User> page = new PageImpl<>(sampleEntityList);
        doReturn(page).when(userRepository).findAll(Pageable.unpaged());

        ResponseEntity<User> resourcesResponseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                },
                Collections.emptyList());
        System.out.println(resourcesResponseEntity.getStatusCode());
        Assertions.assertEquals(true, resourcesResponseEntity.getStatusCode().is2xxSuccessful());
    }

    private User getUser() {
        User user = new User();
        user.setUsername("username");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        return user;
    }
}
