package Utils;

import Model.ChessBoard;
import Model.Game;
import Model.Question;
import Model.Square;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.omg.CORBA.IRObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private static JsonParser instance;
    private Gson gson;
    private GsonBuilder builder;
    private JsonParser() {
        builder = new GsonBuilder();
        gson = builder.setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(IRObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();;
    }

    public static JsonParser getInstance() {
        if(instance == null)
            instance = new JsonParser();
        return instance;
    }

    /**
     * This method parses JsonArray to List of objects
     * @param <T>		the generic type of the class
     * @param data		string parameter that contains the JsonArray that had been read from a text file
     * @param clazz		an object that describes witch class we want to parse the JsonArray to
     * @return			List contains parsed objects of the specific class
     */
    public <T> List<T> parseToList(String data, Object clazz){
        try {
            Type type = null;
            if(clazz instanceof ChessBoard)
                type = new TypeToken<List<ChessBoard>>(){}.getType();
            else if(clazz instanceof Game)
                type = new TypeToken<List<Game>>(){}.getType();
            else if(clazz instanceof Question)
                type = new TypeToken<List<Question>>(){}.getType();
            else if(clazz instanceof Square)
                type = new TypeToken<List<Square>>(){}.getType();
            else if(clazz instanceof String)
                type = new TypeToken<List<String>>(){}.getType();
            List<T> parsedList = gson.fromJson(data, type);
            return parsedList;
        } catch (JsonParseException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }


    /**
     * This method parses JsonObject to one object
     * @param <T>		the generic type of the class
     * @param data		string parameter that contains the JsonObject that had been read from a text file
     * @param clazz		an object that describes witch class we want to parse the JsonObject to
     * @return			parsed object of the specific class
     */
    public Object parseToObject(String data , Object clazz) {
        try {
            Object parsedObject;
            Type type = null;
            if(clazz instanceof ChessBoard)
                type = new TypeToken<ChessBoard>(){}.getType();
            else if(clazz instanceof Game)
                type = new TypeToken<Game>(){}.getType();
            else if(clazz instanceof Question)
                type = new TypeToken<Question>(){}.getType();
            else if(clazz instanceof Square)
                type = new TypeToken<Square>(){}.getType();
            else if(clazz instanceof String)
                type = new TypeToken<String>(){}.getType();
            parsedObject = gson.fromJson(data, type);
            return parsedObject;
        } catch (JsonParseException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method parse an object to JsonObject format
     * @param <T>				the generic type of the class
     * @param objectsList		list of object we want to parse
     * @param clazz				object that desctibes the class of the list elements
     * @return					string contains the list in a json format
     */
    public <T> String parseListToJsonArray(HashMap<Difficulty, ArrayList<Question>> objectsList, Object clazz) {
        try {
            Type type = null;
            if(clazz instanceof ChessBoard)
                type = new TypeToken<List<ChessBoard>>(){}.getType();
            else if(clazz instanceof Game)
                type = new TypeToken<List<Game>>(){}.getType();
            else if(clazz instanceof Question)
                type = new TypeToken<List<Question>>(){}.getType();
            else if(clazz instanceof Square)
                type = new TypeToken<List<Square>>(){}.getType();
            else if(clazz instanceof String)
                type = new TypeToken<List<String>>(){}.getType();
            String parsedList = gson.toJson(objectsList, Collection.class);
            return parsedList;
        } catch (JsonParseException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method parse an object to JsonObject format
     * @param object	the object that will be parsed
     * @return			string that contains the object in json format
     */
    public String parseObjectToJsonObject(Object object) {
        try {
            String jsonData;
            Type type = null;
            if(object instanceof ChessBoard)
                type = new TypeToken<ChessBoard>(){}.getType();
            else if(object instanceof Game)
                type = new TypeToken<Game>(){}.getType();
            else if(object instanceof Question)
                type = new TypeToken<Question>(){}.getType();
            else if(object instanceof Square)
                type = new TypeToken<Square>(){}.getType();
            jsonData = gson.toJson(object, type);
            return jsonData;
        } catch (JsonParseException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}
