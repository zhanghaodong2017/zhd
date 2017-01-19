package com.mongodb;

public class TestMongoDriver {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
		Mongo mongo = new MongoClient(serverAddress);
		DB db = mongo.getDB("testMongoDb");
		DBCollection table = db.getCollection("user");
		System.out.println(table);


	}
}
