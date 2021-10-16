package microservices.book.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices.book.api.domain.Multiplication;
import microservices.book.api.domain.MultiplicationAttempt;
import microservices.book.api.domain.User;
import microservices.book.api.services.multiplication.MultiplicationService;
import microservices.book.controllers.MultiplicationResultAttemptController.ResultResponse;

@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

    @MockBean private MultiplicationService multiplicationService;

    @Autowired private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<MultiplicationAttempt> jsonResult;
    private JacksonTester<ResultResponse> jsonResponse;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postResultReturnCorrect() throws Exception {
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnNotCorrect() throws Exception {
        genericParameterizedTest(false);
    }

    private void genericParameterizedTest(final boolean correct) throws Exception {
        // given (remember we're not testing here the service itself)
        given(multiplicationService.checkAttempt(any(MultiplicationAttempt.class)))
                .willReturn(correct);
        
        User user = new User("john");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationAttempt attempt =
                new MultiplicationAttempt(user, multiplication, 3500);

        // when
        MockHttpServletResponse response =
                mvc.perform(
                                post("/results")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonResult.write(attempt).getJson()))
                        .andReturn()
                        .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonResponse.write(new ResultResponse(correct)).getJson());
    }
}
