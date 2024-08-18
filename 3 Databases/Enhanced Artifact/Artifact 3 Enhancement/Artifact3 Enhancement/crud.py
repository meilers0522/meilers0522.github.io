# Mark Eilers
# CS 499: Senior Capstone
# Enhancement Artifact 3
# August 10, 2024

from pymongo import MongoClient
from bson.objectid import ObjectId
from pprint import pprint

class ShelterDBManager:
    """ CRUD operations for the 'animals' collection in MongoDB """

    def __init__(self, user, pwd, db_name):
        # Configuring connection to the local MongoDB instance
        port_number = 27017
        # Creating a MongoClient instance to connect to MongoDB
        self.client = MongoClient(
            'mongodb://%s:%s@localhost:%d/?authSource=%s' % (user, pwd, port_number, db_name))

        # Selecting the database to interact with
        self.db = self.client[db_name]

        # Choosing the default collection 'animals'
        default_collection = 'animals'
        self.collection = self.db[default_collection]

    # Create operation in CRUD
    def create(self, records):
        if records:
            # Records should be a list containing one or more dictionaries
            return self.collection.insert_many(records)
        else:
            raise ValueError(
                "[-] ERROR: No records provided for insertion.")

    # Read operation in CRUD
    def read(self, filter_criteria):
        if filter_criteria:
            # Filter criteria should be a dictionary to search the collection
            return self.collection.find(filter_criteria, {"_id": False})
        else:
            raise ValueError(
                "[-] ERROR: No filter criteria provided for reading.")

    # Update operation in CRUD
    def update(self, filter_and_update):
        if filter_and_update:
            # Expecting filter_and_update as a tuple (filter, update)
            return self.collection.update_many(*filter_and_update)
        else:
            raise ValueError(
                "[-] ERROR: No data provided for updating.")

    # Delete operation in CRUD
    def delete(self, delete_criteria):
        if delete_criteria:
            # Criteria should be a dictionary for deletion
            return self.collection.delete_many(delete_criteria)
        else:
            raise ValueError(
                "[-] ERROR: No criteria provided for deletion.")