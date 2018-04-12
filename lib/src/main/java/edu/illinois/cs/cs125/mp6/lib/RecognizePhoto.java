package edu.illinois.cs.cs125.mp6.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Recognizes a photo for many circumstances.
 */
public final class RecognizePhoto {

    /**
     * Get the image width.
     *
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the width of the image or 0 on failure
     */
    public static int getWidth(final String json) {
        if (json == null) {
            return 0;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject meta = result.get("metadata").getAsJsonObject();
        int width = meta.get("width").getAsInt();
        return width;
    }

    /**
     * Get the image height.
     *
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the width of the image or 0 on failure
     */
    public static int getHeight(final String json) {
        if (json == null) {
            return 0;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject meta = result.get("metadata").getAsJsonObject();
        int height = meta.get("height").getAsInt();
        return height;
    }

    /**
     * Get the image file type.
     *
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the type of the image or null
     */
    public static String getFormat(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject meta = result.get("metadata").getAsJsonObject();
        String format = meta.get("format").getAsString();
        return format;
    }

    /**
     *
     * Get the image caption.
     *
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the caption of the image or null
     */
    public static String getCaption(final String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject describe = result.get("description").getAsJsonObject();
        JsonArray captions = describe.get("captions").getAsJsonArray();
        JsonObject data = captions.get(0).getAsJsonObject();
        String text = data.get("text").getAsString();
        return text;
    }

    /**
     *
     * @param json the JSON passed by Microsoft Cognitive Services API
     * @param minConfidence the minimum confidence to test against
     * @return true if a cat, false all else
     */
    public static boolean isACat(final String json,
                                 final double minConfidence) {
        if (json == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonArray tags = result.get("tags").getAsJsonArray();
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getAsJsonObject().get("name").getAsString().equals("cat")
                    && tags.get(i).getAsJsonObject().get("confidence").getAsDouble() >= minConfidence) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Determines doghood.
     *
     * @param json JSON from Marcosorft
     * @param minConfidence minimum confidence to check against
     * @return true if dog, false all else
     */
    public static boolean isADog(final String json,
                                 final double minConfidence) {
        if (json == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonArray tags = result.get("tags").getAsJsonArray();
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getAsJsonObject().get("name").getAsString().equals("dog")
                    && tags.get(i).getAsJsonObject().get("confidence").getAsDouble() >= minConfidence) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Will he give you up? Will he let you down?
     *
     * @param json JSON of a picture of (hopefully) rick astley
     * @return true if never gonna give never gonna give, false if he did
     */
    public static boolean isRick(final String json) {
        if (json == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        return false;
    }
}
