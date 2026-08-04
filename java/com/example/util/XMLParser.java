package com.example.util;

import com.example.Model.Product;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public List<Product> parse(InputStream inputStream) {

        List<Product> productList = new ArrayList<>();

        try {


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();


            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();


            NodeList nodeList = document.getElementsByTagName("Product");


            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    int id = Integer.parseInt(
                            element.getElementsByTagName("id")
                                    .item(0)
                                    .getTextContent());

                    String name = element.getElementsByTagName("name")
                            .item(0)
                            .getTextContent();

                    String category = element.getElementsByTagName("category")
                            .item(0)
                            .getTextContent();

                    double price = Double.parseDouble(
                            element.getElementsByTagName("price")
                                    .item(0)
                                    .getTextContent());

                    int stock = Integer.parseInt(
                            element.getElementsByTagName("stock")
                                    .item(0)
                                    .getTextContent());

                    Product product = new Product(
                            id,
                            name,
                            category,
                            price,
                            stock
                    );

                    productList.add(product);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
}