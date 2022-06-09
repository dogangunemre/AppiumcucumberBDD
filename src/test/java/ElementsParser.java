import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ElementsParser {

    public String getElementKey(String elementKey) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Object object = parser.parse(new FileReader("src/test/resources/datas/elements.json"));
        JsonObject jsonObject = (JsonObject) object;
        String string = jsonObject.get(elementKey).getAsString();
        return string;
    }
}