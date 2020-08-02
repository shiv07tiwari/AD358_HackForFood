from flask import Flask, request, jsonify
import json
from pymongo import MongoClient
import flask
from bson import json_util
from bson.objectid import ObjectId
import random
from datetime import date
import numpy as np
from flask_cors import CORS, cross_origin
from spothole import isPotHole

app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

POTHOLE_ID = "pothole_id"
LATITUDE = 'latitude'
LONGITUDE = 'longitude'
COUNTER = 'counter'
TIME = "time"
USERID = "userid"
ADMIN_USER = 'iit2017097'
ADMIN_PASSWORD = 'test_pass'
client = MongoClient('127.0.0.1', 27017)
db = client['pothole_database']
POTHOLE_EXISTING = db.potholes
POTHOLE_RESOLVED = db.resolved_potholes
USER_POTHOLE = db.user_pothole
REPAIR_REQUEST = db.repair_request

def shutdown_server():
        func = request.environ.get('werkzeug.server.shutdown')
        if func is None:
            raise RuntimeError('Not running with the Werkzeug Server')
        func()
@app.route('/shutdown')
def shutdown():
    shutdown_server()
    return 'Server shutting down...'


# Testing route
@app.route("/removeall")
def home():
    POTHOLE_EXISTING.remove()
    POTHOLE_RESOLVED.remove()
    USER_POTHOLE.remove()
    REPAIR_REQUEST.remove()
    return "Hello user "
@app.route('/')
def default():
    return "MAA CHUDA"

def addUserPothole(user_id,_id) :
    Dict = {"user_id":user_id,"pothole_id":_id,"resolved":"0"}
    USER_POTHOLE.insert_one(Dict)

def addToMainDatabase(pothole_entry):
    print("Inserted",POTHOLE_EXISTING.insert_one(pothole_entry))
    
def increaseCounterMainDatabase(pothole_entry, counter, time_string):
    counter = int(counter) + 1
    if (time_string != pothole_entry[TIME]):
        time_string = pothole_entry[TIME] + "," + time_string
    
    
    POTHOLE_EXISTING.update_one(
        {LATITUDE:pothole_entry[LATITUDE],LONGITUDE:pothole_entry[LONGITUDE]},
        { "$set": {
            COUNTER : str(counter)
        }}
    )

    POTHOLE_EXISTING.update_one(
         {LATITUDE:pothole_entry[LATITUDE],LONGITUDE:pothole_entry[LONGITUDE]},
        { "$set": {
            TIME : time_string
        }}
    )

def increaseCounterResolvedDatabase(pothole_entry, counter, time_string):
    counter = int(counter) + 1
    
    if (time_string != pothole_entry[TIME]):
        time_string = pothole_entry[TIME] + "," + time_string
    
    
    POTHOLE_RESOLVED.update_one(
        {LATITUDE:pothole_entry[LATITUDE],LONGITUDE:pothole_entry[LONGITUDE]},
        { "$set": {
            COUNTER:str(counter)
        }}
    )
    POTHOLE_RESOLVED.update_one(
        {LATITUDE:pothole_entry[LATITUDE],LONGITUDE:pothole_entry[LONGITUDE]},
        { "$set": {
            TIME:time_string
        }}
    )


# Access database to add a new pothole or inc the counter if it already exists
@app.route('/pothole', methods = ['POST'])
def savePotholetoDatabase() :
    
    if (request.is_json):

        today = date.today()

        json_data = request.get_json()
        #print(type(json_data))
        json_string = json.dumps(json_data)
        #print(type(json_string))
        parsed_json = (json.loads(json_string))
        #print(type(parsed_json['data']))
        for x in parsed_json['data']:
            print(type(x))
            print(x)

     
        for x in parsed_json['data']:
            print("JSON ",type(x))
           
            step = 0.00000000000001
            to_bin = lambda x: np.floor(x / step) * step
            
            x[LATITUDE] = float(x[LATITUDE])
            x[LONGITUDE] = float(x[LONGITUDE])

            x_latitude = str(x[LATITUDE])
            x_longitude = str(x[LONGITUDE])
            
            x[LONGITUDE] = x_longitude
            x[LATITUDE] = x_latitude
            
            print("Latitude ",x_latitude," ", x_longitude," ",x[LATITUDE]," ",x[LONGITUDE])
            
            test = POTHOLE_EXISTING.find()
            print("Existing database\n")
            for t in test:
                print(t[LATITUDE]," ", t[LONGITUDE])
                print(type(t[LATITUDE]))
                print(type(x_latitude))
            
            bills_post = POTHOLE_EXISTING.find({LATITUDE : str(x_latitude),
             LONGITUDE : x_longitude})
            bills_host = POTHOLE_RESOLVED.find({LATITUDE : str(x_latitude),
            LONGITUDE : x_longitude})
            print(bills_post.count()," counts ",bills_post.count())

            if (bills_post.count() > 0):
                for bill in bills_post:
                    a = bill[COUNTER]
                    b = bill[TIME]
                    #print("Counter", a)
                print("Updating main database")
                increaseCounterMainDatabase(x,a,b)
                addUserPothole(x[USERID], str(bill["_id"]))

            elif (bills_host.count()>0):
                for bill in bills_host:
                    a = bill[COUNTER]
                    b = bill[TIME]
                    increaseCounterResolvedDatabase(x,a,b)
                    addUserPothole(bill[USERID], str(bill["_id"]))
                    print("Updating second database")

            else:
                print("Adding to database")
                x["first_reported"] = x[TIME]
                addToMainDatabase(x)
                pothole_details = POTHOLE_EXISTING.find({LATITUDE : x_latitude,
                                    LONGITUDE : x_longitude})
                for pothole_detail in pothole_details:
                    print(pothole_detail)
                    print(type(pothole_detail))
                    addUserPothole(x[USERID], str(pothole_detail["_id"]))
            
        req = request.json
        print(req)
        return "Thanks"

# Get all counts
@app.route('/counter', methods = ['GET'])
def getPotholeCounts():
    city = request.args.get('city')
    count_existing_total = 0
    count_existing_city = 0
    count_resolved_total = 0
    count_resolved_city = 0

    pothole_existing = POTHOLE_EXISTING.find()
    for i in pothole_existing:
        count_existing_total += 1
        
        a = i['location']
        if (city == a['city']):
            count_existing_city += 1
    
    pothole_resolved = POTHOLE_RESOLVED.find()
    for i in pothole_resolved:
        count_resolved_total += 1

        a = i['location']
        if (city == a['city']):
            count_resolved_city += 1
    a = {'total_existing':count_existing_total,'total_resolved':count_resolved_total,
            'city_existing':count_existing_city, 'city_resolved':count_resolved_city}

    res = json.dumps(a, default=json_util.default)
    return res

# Get users and pothole ids
@app.route('/userpothole', methods=['GET'])
def getUserPotholes():
    userId = request.args.get('user_id')
    potholeId = request.args.get('pothole_id')
    pothole_type = request.args.get('type')

    if (userId == None and potholeId == None):
        bills = list(USER_POTHOLE.find())
    
    elif (userId != None and potholeId == None):
        bills_initial = USER_POTHOLE.find({"user_id":userId})
        Set = set({})
        for bill in bills_initial :
            Set.add(bill['pothole_id'])
        print("SET ",Set)
        bills = list()
        for s in Set:
            if (pothole_type == "existing"):
                items = POTHOLE_EXISTING.find_one({"_id" : ObjectId(s)})
            else:
                items = POTHOLE_RESOLVED.find_one({"_id" : ObjectId(s)})
            print(type(items))
            print(items)
            bills.append(items)

    elif (userId == None and potholeId != None):
        bills = list(USER_POTHOLE.find({"pothole_id":potholeId}))
    else:
        bills = list(USER_POTHOLE.find({"user_id":userId, "pothole_id":potholeId}))
    
    res = json.dumps(bills, default=json_util.default)
    return res

# Test route for pothole check
@app.route('/ispothole',methods = ['POST'])
def checkIfPothole():
    if (request.is_json):

        today = date.today()

        json_data = request.get_json()
        #print(type(json_data))
        json_string = json.dumps(json_data)
        #print(type(json_string))
        parsed_json = (json.loads(json_string))
        #print(type(parsed_json['data']))
        
        float_dict_arr = []
        # for x in parsed_json:
        #     x = dict(zip(x.keys(), [float(value) for value in x.values()]))
        #     float_dict_arr.append(x)
        float_dict_arr.append(parsed_json)
        float_dict_arr.append(parsed_json)
        float_dict_arr.append(parsed_json)
        float_dict_arr.append(parsed_json)
        print(float_dict_arr)
        pred = isPotHole(float_dict_arr)
        print("MODEL RETURNED : " + str(pred))
        return str(pred)

# Access database to get active potholes
@app.route('/potholes',methods = ['GET'])
def getPotholes():
    
    latitude = request.args.get('lat')
    longitude = request.args.get('lon')
    counter = request.args.get('count')
    print("FINAL : ",latitude,longitude,counter)

    if (latitude == None and longitude == None and counter == None):
         bills_post = list(POTHOLE_EXISTING.find())
    elif (latitude != None and longitude != None):
         bills_post = list(POTHOLE_EXISTING.find({LATITUDE:latitude, LONGITUDE:longitude}))
    else:
        return "Invalid request"
    for x in bills_post:
        print(type(x))
        print(x)
    res = json.dumps(bills_post, default=json_util.default)

    return res

# Get an individual active pothole based on it's id
@app.route('/pothole/<id>',methods=['GET'])
def getPothole(id):
    print(id)
    pothole_details = POTHOLE_EXISTING.find({"_id" : ObjectId(id)})
    print(type(pothole_details))

    for x in pothole_details:
        print("VDUDUVDFVYUDVUD ",x)
        res = json.dumps(x, default=json_util.default)
        return res
    return "POthole not found"

# Resolve a Pothole 
# Add it to new resolved database with counter 0 and remove it from main database
@app.route('/resolve/<id>', methods = ['GET'])
def resolvePothole(id):
    pothole_details = POTHOLE_EXISTING.find_one({"_id" : ObjectId(id)})
    print(pothole_details)

    x = POTHOLE_EXISTING.delete_one(pothole_details)
    print("Delete stats : ",x)

    pothole_details['counter'] = 0
    POTHOLE_RESOLVED.insert_one(pothole_details)
    USER_POTHOLE.update_many(
        {"pothole_id":id},
        { "$set": {
            "resolved" : str(1)
        }}
    )
    REPAIR_REQUEST.update_many(
        {"pothole_id":id},
        { "$set": {
            "isCompleted" : str(1),
            "complete" : str(date.today())
        }}
    )
    return "Pothole Resolved"

# Access all the reolved potholes
@app.route('/resolved')
def getResolvedPotholes():
    latitude = request.args.get('lat')
    longitude = request.args.get('lon')
    if (latitude == None and longitude == None):
         bills_post = list(POTHOLE_RESOLVED.find())
    elif (latitude != None and longitude != None):
         bills_post = list(POTHOLE_RESOLVED.find({LATITUDE:latitude, LONGITUDE:longitude}))
    else:
        return "Invalid request"
    res = json.dumps(bills_post, default=json_util.default)
    return res

# Get an individual resolved pothole based on it's id
@app.route('/resolved/<id>',methods=['GET'])
def getPotholeResolved(id):
    print("Type 1 ",type(id))
    pothole_details = POTHOLE_RESOLVED.find({"_id" : ObjectId(id)})
    for x in pothole_details:
        res = json.dumps(x, default=json_util.default)
    return res

# Add a repair request
@app.route('/repair',methods = ['POST'])
def repairRequest():
    json_data = request.get_json()
    #print(type(json_data))
    json_string = json.dumps(json_data)
    #print(type(json_string))
    parsed_json = (json.loads(json_string))
    
    createDate = parsed_json['createDate']
    companyAlloted = parsed_json['company']
    budget = parsed_json['budget']
    PotholeID = parsed_json['potholeId']

    dictRepair = {'create':createDate,'isCompleted':"0",'complete':"",'company':companyAlloted,'budget':budget,'pothole_id':PotholeID}    
    REPAIR_REQUEST.insert_one(dictRepair)

    return "Request Successful"

# Get all repair requests
@app.route('/repair', methods = ['GET'])
def repairs():
    repair_id = request.args.get('id')
    if (repair_id != None):
        beta = list(REPAIR_REQUEST.find({"_id" : ObjectId(repair_id)}))
    else:
        beta = list(REPAIR_REQUEST.find())
    res = json.dumps(beta, default=json_util.default)
    return res

if __name__ == "__main__":
    app.run(debug=True, port=5000)