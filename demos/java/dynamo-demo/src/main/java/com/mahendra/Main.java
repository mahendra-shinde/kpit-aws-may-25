package com.mahendra;
 
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        DynamoDbClient ddb = DynamoDbClient.create();

        // Create table "products"
        CreateTableRequest request = CreateTableRequest.builder()
            .tableName("products")
            .keySchema(KeySchemaElement.builder()
                .attributeName("productId")
                .keyType(KeyType.HASH)
                .build())
            .attributeDefinitions(AttributeDefinition.builder()
                .attributeName("productId")
                .attributeType(ScalarAttributeType.S)
                .build())
            .provisionedThroughput(ProvisionedThroughput.builder()
                .readCapacityUnits(5L)
                .writeCapacityUnits(5L)
                .build())
            .build();

        try {
            ddb.createTable(request);
            System.out.println("Table created. Waiting for it to become active...");
            // Wait for table to become active (simple sleep, for demo)
            Thread.sleep(8000);
        } catch (ResourceInUseException e) {
            System.out.println("Table already exists.");
        } catch (InterruptedException ex){
            System.out.println("Thread interrupted: " + ex.getMessage());
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // Add two items
        Map<String, AttributeValue> item1 = new HashMap<>();
        item1.put("productId", AttributeValue.builder().s("P1001").build());
        item1.put("name", AttributeValue.builder().s("Laptop").build());
        item1.put("price", AttributeValue.builder().n("1200").build());

        Map<String, AttributeValue> item2 = new HashMap<>();
        item2.put("productId", AttributeValue.builder().s("P1002").build());
        item2.put("name", AttributeValue.builder().s("Smartphone").build());
        item2.put("price", AttributeValue.builder().n("800").build());

        ddb.putItem(PutItemRequest.builder().tableName("products").item(item1).build());
        ddb.putItem(PutItemRequest.builder().tableName("products").item(item2).build());

        System.out.println("Added two items to 'products' table.");

        ddb.close();
    }
}