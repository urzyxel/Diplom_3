package api.module;

import api.request.user.CreateUserRequest;
import api.request.user.LoginUserRequest;
import api.response.user.SuccessCreateUserResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Endpoints implements ApiClientInterface {
    static {
        ApiClientInterface.initializeBaseUri(Constants.BASE_URI);
    }

    @Step("Создание тестового пользователя")
    public Response createUser(String name, String email, String password, int statusCode) {
        final Response[] response = new Response[1];
        Allure.step("Выполняем запрос api на создание пользователя", () -> {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            Map<String, String> params = new HashMap<>();
            boolean fl = false;
            int attemptCount = 0;
            do {
                CreateUserRequest user = new CreateUserRequest(name, email, password);
                response[0] = post(Constants.URL_CREATE_USER, headers, params, user);
                attemptCount++;
                if (response[0].getStatusCode() == statusCode) {
                    fl = true;
                    Allure.step("Ответ от сервера: " + response[0].getStatusCode());
                } else {
                    Allure.step("Ответ от сервера: " + response[0].getStatusCode() + "повторная попытка");
                    System.out.println("Ошибка: " + response[0].getStatusCode() + ". Повторная попытка...");
                }
            } while (!fl && attemptCount < 3);
        });
        return response[0];
    }

    @Step("Авторизация тестового пользователя")
    public Response loginUser(String email, String password) {
        final Response[] response = new Response[1];
        Allure.step("Выполняем запрос api на авторизацию пользователя", () -> {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            Map<String, String> params = new HashMap<>();
            LoginUserRequest authorization = new LoginUserRequest(email, password);
            response[0] = post(Constants.URL_LOGIN_USER, headers, params, authorization);
            Allure.step("Ответ от сервера: " + response[0].getStatusCode());
        });
        return response[0];
    }

    @Step("Удаление созданных тестовых пользователей")
    public void deleteUser(List<SuccessCreateUserResponse> userList) {
        Allure.step("Выполняем запрос api на удаление пользователя", () -> {
            String accessToken;
            for (SuccessCreateUserResponse user : userList) {
                accessToken = user.getAccessToken();
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", accessToken);
                headers.put("Content-Type", "application/json");
                Map<String, String> params = new HashMap<>();
                Response response = delete(Constants.URL_INFO_UPDATE_DELETE_USER, headers, params);
                Allure.step("Ответ от сервера: " + response.getStatusCode());
                if (response.getStatusCode() == 202) {
                    Allure.step("Учётная запись с login:  " + user.getUser().getEmail() + ", удалена", Status.PASSED);
                    System.out.println("Пользователь успешно удален!");
                } else {
                    System.out.println("Ошибка при удалении пользователя: " + response.getStatusCode() + " - " + user.getUser().getEmail() + ", " + user.getAccessToken());
                    Allure.step("Учётная запись с login:  " + user.getUser().getEmail() + ", не удалена", Status.FAILED);
                }
            }
        });
    }
}
