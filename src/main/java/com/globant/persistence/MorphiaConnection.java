package com.globant.persistence;



import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

public class MorphiaConnection {

	private static MorphiaConnection instance = new MorphiaConnection();

	private MongoClient mongo = null;
	private Datastore dataStore = null;
	private Morphia morphia = null;

	private MorphiaConnection() {}

	public MongoClient getMongo() throws RuntimeException {
		if (mongo == null) {
			MongoClientOptions.Builder options = MongoClientOptions.builder()
					.connectionsPerHost(4)
					.maxConnectionIdleTime((60 * 1_000))
					.maxConnectionLifeTime((120 * 1_000));

			MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/high_school", options);

			try {
				mongo = new MongoClient(uri);
			} catch (Exception ex) {
				System.err.print("Error in Mongo");
			}
		}
		return mongo;
	}

	public Morphia getMorphia() {
		if (morphia == null) {
			morphia = new Morphia();
			morphia.mapPackage("com.globant.model");
		}
		return morphia;
	}

	public Datastore getDatastore() {
		if (dataStore == null) {
			String dbName = "high_school";
			dataStore = getMorphia().createDatastore(getMongo(), dbName);
		}

		return dataStore;
	}

	public static MorphiaConnection getInstance() {
		return instance;
	}
}