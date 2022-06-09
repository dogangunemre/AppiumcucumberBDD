package parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class UsersParser {
    public static Map<String, String> users = new HashMap<>();

    public void setUser(String userKey) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Object object = parser.parse(new FileReader("src/test/resources/datas/users.json"));
        JsonObject jsonObject = (JsonObject) object;
        JsonObject returnObject = (JsonObject) jsonObject.get(userKey);
        users.put("citizenId", returnObject.get("citizenId").getAsString());
        users.put("phone", returnObject.get("phone").getAsString());
        users.put("birthDate", returnObject.get("birthDate").getAsString());
        users.put("birthYear", returnObject.get("birthYear").getAsString());
        users.put("birthMonth", returnObject.get("birthMonth").getAsString());
        users.put("birthDay", returnObject.get("birthDay").getAsString());
        users.put("firstName", returnObject.get("firstName").getAsString());
        users.put("secondName", returnObject.get("secondName").getAsString());
        users.put("lastName", returnObject.get("lastName").getAsString());
        users.put("motherName", returnObject.get("motherName").getAsString());
        users.put("applicationId", returnObject.get("applicationId").getAsString());

    }

    public Boolean isContains(String key) {
        return users.containsKey(key);
    }

    public String getUserData(String key) {
        return users.get(key);
    }

}