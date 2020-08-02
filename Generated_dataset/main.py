import numpy as np
import matplotlib.pyplot as plt
import itertools
import pandas as pd
from faker import Faker
from utils import pincode_gen
import random

fake = Faker(['en_US'])

connecting_transport = [x for x in range(1, 4)]
place_importance = [x for x in range(1, 6)]
number_of_complaints = [x/100. for x in range(0, 101)]

combinations = [connecting_transport, place_importance, number_of_complaints]

weights = np.array([2, 2, 1])

road_priority = []
features = []
for element in itertools.product(*combinations):
    element = np.array(element)
    features.append(element)
    element = np.sum(element*weights)
    road_priority.append(element)

# Random Road details
road_id = np.array([x for x in range(1, len(road_priority)+1)]).reshape(-1, 1)
last_tender_id = np.random.randint(100, size=len(road_priority)).reshape(-1, 1)
initial_tender_id = np.random.randint(100, size=len(road_priority)).reshape(-1, 1)
remarks = np.array([fake.text() for x in range(len(road_priority))]).reshape(-1, 1)
pincodes = np.array([pincode_gen() for x in range(len(road_priority))]).reshape(-1, 1)
start_point = np.array([fake.address() for x in range(len(road_priority))]).reshape(-1, 1)
end_point = np.array([fake.address() for x in range(len(road_priority))]).reshape(-1, 1)
length_of_road = np.random.randint(low=1, high=100, size=len(road_priority)).reshape(-1, 1)
zone = np.random.randint(low=1, high=10, size=len(road_priority)).reshape(-1, 1)
district = np.array(['District-'+str(random.randint(1, 500)) for x in range(len(road_priority))]).reshape(-1, 1)
sub_category = np.array([random.choice(['Major District Road', 'State Highway', 'National Highway', 'common city road']) for
                x in range(len(road_priority))]).reshape(-1, 1)
road_material = np.array([random.choice(['asphalt', 'gravel', 'concrete', 'Composite pavement']) for
                x in range(len(road_priority))]).reshape(-1, 1)
depth_of_road = np.random.randint(low=1, high=10, size=len(road_priority)).reshape(-1, 1)
traffic_details = np.random.randint(low=1, high=3, size=len(road_priority)).reshape(-1, 1)
heavy_vehicle_density = 50*np.random.rand(len(road_priority),).reshape(-1, 1)
light_four_wheeler_vehicle_density = 100*np.random.rand(len(road_priority),).reshape(-1, 1)
light_two_wheeler_vehicle_density = 100*np.random.rand(len(road_priority),).reshape(-1, 1)


# Preparing a csv
features = np.array(features)
road_priority = np.array(road_priority)
np.random.shuffle(road_priority)
road_indices = [id for id in range(1, road_priority.shape[0]+1)]

data = np.concatenate([road_id, last_tender_id, initial_tender_id, remarks, pincodes, start_point, end_point,
                       length_of_road, zone, district, sub_category, road_material,
                       depth_of_road, traffic_details, heavy_vehicle_density, light_four_wheeler_vehicle_density,
                       light_two_wheeler_vehicle_density, features, road_priority.reshape(-1, 1)], axis=1)

df = pd.DataFrame(data, columns=['road_id', 'last_tender_id', 'initial_tender_id', 'remarks', 'pincodes',
                                 'start_point', 'end_point', 'length_of_road', 'zone', 'district', 'sub_category',
                                 'road_material', 'depth_of_road', 'traffic_details', 'heavy_vehicle_density',
                                 'light_four_wheeler_vehicle_density', 'light_two_wheeler_vehicle_density',
                                 'connecting_features', 'places', 'number_of_complaints', 'road_priority'])
df.to_csv('./data.csv', index=False)

plt.plot(road_indices, road_priority)
plt.show()