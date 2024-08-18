# Mark Eilers
# CS 499: Senior Capstone
# Enhancement Artifact 3
# August 10, 2024

from pymongo import MongoClient
from bson.objectid import ObjectId
from pprint import pprint

class ShelterManager:
    """ CRUD operations for the 'animals' collection in MongoDB """

    def __init__(self, user, pwd, database_name):
        # Localhost port configuration for MongoDB
        port_number = 27017
        # Establish connection to MongoDB using the credentials provided
        self.client = MongoClient(
            'mongodb://%s:%s@localhost:%d/?authSource=%s' % (user, pwd, port_number, database_name))
        print("[+] Connected to the Database: %s" % database_name)
        
        # Set active database and list collections in the database
        self.db = self.client[database_name]
        print("\n[+] Available Collections:")
        print("---------------------------")
        for collection in self.db.list_collection_names():
            print(collection)
        
        # Define default collection
        default_collection = 'animals'
        print("\n[?] Selected Collection: %s" % default_collection)
        print("\n[+] Now using collection: < %s >" % default_collection)
        self.collection = self.db[default_collection]
        
        print("\n*** Connection Established ***")

    # Create operation in CRUD
    def create(self, records):
        if records:
            result = self.collection.insert_many(records)  # Expecting a list of dictionaries
            print("[+] Successfully Inserted Documents: %d" % len(records))
            print("---------------------------------")
            for record_id in result.inserted_ids:
                print("New ObjectId: %s" % record_id)
            print("---------------------------------\n*** Insertion Complete ***")
        else:
            raise ValueError("[-] ERROR: No data provided for insertion.")

    # Read operation in CRUD
    def read(self, query):
        if query:
            results = self.collection.find(query)  # Query should be a dictionary
            print("[+] Documents Found: %d" % self.collection.count_documents(query))
            print("---------------------------------")
            for document in results:
                pprint(document)
            print("---------------------------------\n*** Query Complete ***")
        else:
            raise ValueError("[-] ERROR: No query provided for reading.")

    # Update operation in CRUD
    def update(self, criteria):
        if criteria:
            updates = self.collection.update_many(*criteria)  # Criteria should be a tuple (filter, update)
            print("[+] Documents Updated: %d" % updates.modified_count)
            print("---------------------------------")
            updated_flag = {'updated': 'true'}
            for doc in self.collection.find(updated_flag):
                pprint(doc)
            self.collection.update_many({}, {'$unset': {'updated': ''}})  # Clean up 'updated' flag from docs
            print("---------------------------------\n*** Update Complete ***")
        else:
            raise ValueError("[-] ERROR: No criteria provided for updating.")

    # Delete operation in CRUD
    def delete(self, criteria):
        if criteria:
            deleted = self.collection.delete_many(criteria)  # Criteria should be a dictionary
            print("[+] Documents Deleted: %d" % deleted.deleted_count)
        else:
            raise ValueError("[-] ERROR: No criteria provided for deletion.")