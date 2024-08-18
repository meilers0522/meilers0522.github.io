#Mark Eilers
#Assignment 5-1 Project 1
#CS-340: Client/Server Development
#November 24, 2023

#Shelter Code for Python

#Import the pymongo MongoClient and ObjectID. 
from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
    """ CRUD operations for Animal collection in MongoDB """
    
    def __init__(self):
        #Initializing the MongoClient. 
        if username and password: 
            self.client = MongoClient('mongodb://%s:%s@localhost:56101/?authMechanism=DEFAULT&authSource=AAC' % (username, password))
            self.database = self.client['AAC']

    #Create method to implement the C in CRUD.
    #Def create constructor using self, and data. 
    def create(self, data):
        
        #if statment for if the data is not found.  Inform user.
        if data is not None:
            #insets data successfully
            self.database.animals.insert(data) #data should be dictionary
            return True
        
        else:
            print('Nothing to save, because data parameter is empty')
            return False
            
    #Create Method to implement the R in CRUD.
    #Def read constructor using self, and data. 
    def read(self, data):
        if data is not None:
            return self.database.animals.find_one(data)
        else:
            print('Nothing to read, because data parameter is empty')
            return False   

    #Create method to implement the U in CRUD.
    #Def update constructor using self, query, record.
    def update(self, data, change):
        
        if data is not None:
            return self.database.animals.update(data,{"$set": change}) #Data and change are dictionaries.
        else:
            print('Nothing to update, because data parameter is empty')
            return False
                                                      
    
    #Create method to implement the D in CRUD.
    #Def delete constructor using self, data
    def delete(self, data):
        
        if data is not Nont:
            return self.database.animals.delete_one(data) #Data is dictionary.
        else:
            print('Nothing to delete, because data parameter is empty')
            return False
   