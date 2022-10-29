package Courier;

import User.Courier;
import Generator.CourierGenerator;
import User.CourierLogin;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AuthorizationCourierTest {
    private static final String REGISTER_ERROR_404 = "Учетная запись не найдена";
    private static final String REGISTER_ERROR_400 = "Недостаточно данных для входа";

    @Test
    @DisplayName("Успешная авторизация курьера")
    @Description("Ожидается ответ 201")
    public void successfulCourierAutorizathion() {
        Courier request = CourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = Courier.courierAuthorization(new CourierLogin(request.getLogin(), request.getPassword()));

        loginResponse.then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Ожидается ответ 400")
    public void autorizathionWithoutLogin() {
        Courier request = CourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = Courier.courierAuthorization(new CourierLogin(null, request.getPassword()));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Ожидается ответ 400")
    public void autorizathionWithoutPassword() {
        Courier request = CourierGenerator.getRandomNewCourierGenerator();
        Response response = Courier.createNewCourier(request);
        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = Courier.courierAuthorization(new CourierLogin(null, request.getPassword()));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Ожидается ответ 404")
    public void autorizationCourierNotExist() {
        Response loginResponse = Courier.courierAuthorization(new CourierLogin(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_404));
    }

}

