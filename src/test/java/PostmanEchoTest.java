import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    @Test
    public void shouldSendRequestBody() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8") // тип контента
                .body("Test") // Отправляемые данные
                // Выполняемые действия
                .when()
                .post("/post")
                // Проверки
                .then()
                .statusCode(200) // Проверка статуса ответа
                .contentType(ContentType.JSON) // Проверка типа ответа
                .body("data", equalTo("Test")) // Проверка данных в теле ответа
                .body("headers.content-length", equalTo("5")) // Проверка длины содержимого
                .body("headers.content-type", containsString("text/plain")) // Проверка наличия типа текста
                .body("headers.user-agent", containsString("Apache-HttpClient")); // Проверка заголовка User-Agent
    }
}
