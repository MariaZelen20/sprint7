package Order;

import Generator.OrderGenerator;
import User.Order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import User.Courier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

@AllArgsConstructor
@RunWith(Parameterized.class)
@DisplayName("Создание заказа")
public class CreateOrderTest {
    private final List<String> colorsOfOrder;
    private final int expectedStatus;

    @Parameterized.Parameters(name = "color: {0}, return {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {List.of("BLACK"), 201},
                {List.of("GREY"), 201},
                {Arrays.asList("BLACK", "GREY"), 201},
                {Collections.emptyList(), 201}
        };
    }

    @Test
    @DisplayName("Создание заказа с параметризацией")
    @Description("Ожидаемый код ответа: 201")
    public void createOrderWithParam() {
        Order request = OrderGenerator.getNewOrder(colorsOfOrder);
        Response response = Courier.createNewOrders(request);

        response.then()
                .statusCode(expectedStatus)
                .and()
                .assertThat().body("track", notNullValue());
    }
}
