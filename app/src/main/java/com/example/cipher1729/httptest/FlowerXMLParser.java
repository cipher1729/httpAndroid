package com.example.cipher1729.httptest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cipher1729 on 8/27/2015.
 */
public class FlowerXMLParser {

    static List<flowers> flowersList;
    static flowers newFlower;
    static String text;

    public List<flowers> getFlowers()
    {
        return flowersList;
    }
    public static List<flowers> parse(String content)
    {    flowersList= new ArrayList<>();
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        {
            try {
                factory= XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                parser=factory.newPullParser();
                parser.setInput(new StringReader(content));

                int eventType= parser.getEventType();
                while(eventType!=XmlPullParser.END_DOCUMENT)
                {
                    String tagName= parser.getName();
                    switch(eventType) {

                        case XmlPullParser.START_TAG:
                            if (tagName.equalsIgnoreCase("product")) {
                                // create a new instance of employee
                                newFlower = new flowers();
                            }
                            break;

                        case XmlPullParser.TEXT:
                            text = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(tagName.equals("product"))
                            {
                                flowersList.add(newFlower);
                            }
                            else if(tagName.equals("productId"))
                            {
                                newFlower.setProductId(Integer.parseInt(text));
                            }
                            else if(tagName.equals("category"))
                            {
                                newFlower.setCategory(text);
                            }
                            else if(tagName.equals("name"))
                            {
                                newFlower.setName(text);
                            }
                            else if(tagName.equals("instructions"))
                            {
                                newFlower.setInstructions(text);
                            }
                            else if(tagName.equals("price"))
                            {
                                newFlower.setPrice(Float.parseFloat(text));
                            }
                            else if(tagName.equals("photo"))
                            {
                                newFlower.setPhoto(text);
                            }
                            break;
                        default:
                            break;

                    }
                    eventType=parser.next();
                 }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return flowersList;
    }

}


