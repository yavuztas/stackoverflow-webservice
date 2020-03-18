package dev.yavuztas.stackoverflowwebservice.test.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yavuztas.stackoverflowwebservice.config.SwaggerConfig;
import dev.yavuztas.stackoverflowwebservice.controller.UserController;
import dev.yavuztas.stackoverflowwebservice.test.mock.MockUsers;
import dev.yavuztas.stackoverflowwebservice.test.unit.config.UserControllerUnitTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for our {@link dev.yavuztas.stackoverflowwebservice.controller.UserController}
 *
 * @author Yavuz Tas
 */
@ActiveProfiles("dev")
@Import(UserControllerUnitTestConfig.class)
@WebMvcTest(controllers = UserController.class, excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SwaggerConfig.class)
)
class UserControllerUnitTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenUserRequested_userAsJsonReturns() throws Exception {

        String userModel3 = mapper.writeValueAsString(MockUsers.userModel3);

        mockMvc.perform(get("/1.0/user/3"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(userModel3)
                );
    }

}
