package User;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Courier extends Rest {     // В этом классе я создаю методы в апи

    private static final String COURIER = "courier";
    private String login;
    private String password;
    private String firstName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private static final String COURIER_LOGIN = "courier/login";
    private static final String ORDERS = "orders";


    @Step("Создать курьера")
    public static Response createNewCourier(Courier body) { //создание нового курьера
        return given().log().all()
                .spec(getDefaultRequestSpec())
                .body(body)
                .when()
                .post(COURIER);
    }

    @Step("Проверка авторизации курьера")
    public static Response courierAuthorization(CourierLogin body) { //авторизация курьера
        return given().log().all()
                .spec(getDefaultRequestSpec())
                .body(body)
                .when()
                .post(COURIER_LOGIN);
    }

    @Step("Проверка создания заказа")
    public static Response createNewOrders(Order body) { //создание нового заказа
        return given()
                .spec(getDefaultRequestSpec())
                .body(body)
                .when()
                .post(ORDERS);
    }
}
