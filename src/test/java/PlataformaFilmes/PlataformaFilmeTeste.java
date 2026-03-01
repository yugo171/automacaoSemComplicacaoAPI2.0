package PlataformaFilmes;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Restutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.*;
public class PlataformaFilmeTeste {

    static String token;

    @Test
    public void ValidarLogin(){

        Restutils.setBaseURI("http://localhost:8080");

        String json = "{\"email\":\"aluno@email.com\",\"senha\":\"123456\"}";

        /*json = json.replace("123456", "789");
        * FAZ A ALTERÇÃO DA SENHA
        * */

        Response response = Restutils.post(json, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        token = response.jsonPath().get("token");
        System.out.println("TOKEN DO VALIDATELOGIN: " + token);

    }

    @BeforeAll
    public static void validarLoginMap(){
        Restutils.setBaseURI("http://localhost:8080");
        Map<String, String> map = new HashMap<>();
        map.put("email", "aluno@email.com");
        map.put("senha", "123456");
        /*map.put("senha", "789");*/
        /*map.remove("senha");*/
        Response response = Restutils.post(map, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        token = response.jsonPath().get("token");
        System.out.println("TOKEN DO BEFOREALL: " + token);

    }

    @Test
    public void validarContultoriasCategorias(){
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer "+token);

        Response response = Restutils.get(header, "categorias");
        assertEquals(200, response.statusCode());


        System.out.println(response.jsonPath().get().toString());

        assertEquals("Terror",response.jsonPath().get("tipo[2]"));

        List<String> listTipo = response.jsonPath().get("tipo");
        assertTrue(listTipo.contains("Terror"),"Não foi encontrado a categoria Terror na lista de categorias");



    }
}
