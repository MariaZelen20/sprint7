package Courier;

import Generator.CourierGenerator;
import User.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    private static final String REGISTER_ERROR_400 = "Недостаточно данных для создания учетной записи";
    private static final String REGISTER_ERROR_409 = "Этот логин уже используется. Попробуйте другой.";

    @Test
    @DisplayName("Создание курера с валидными данными")
    @Description("Ожидается ответ 201")
    public void createCourierAllCorrectParameters() {
        Courier request = CourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курера без логина")
    @Description("Ожидается ответ 400")
    public void createCourierWithoutLogin() {
        Courier request = CourierGenerator.getNewCourierWithoutLogin();
        Response response = Courier.createNewCourier(request);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Создание курера без пароля")
    @Description("Ожидается ответ 400")
    public void createCourierWithoutPassword() {
        Courier request = CourierGenerator.getNewCourierWithoutPassword();
        Response response = Courier.createNewCourier(request);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Ожидается ответ 409")
    public void createTwoIdenticalCourier() {
        Courier request = CourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response responseWithError = Courier.createNewCourier(request);
        responseWithError.then()
                .statusCode(409)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_409));
    }

}

