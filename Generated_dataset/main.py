import numpy as np
import matplotlib.pyplot as plt
import itertools
import pandas as pd


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

features = np.array(features)
road_priority = np.array(road_priority)
np.random.shuffle(road_priority)
road_indices = [id for id in range(1, road_priority.shape[0]+1)]

data = np.concatenate([features, road_priority.reshape(-1, 1)], axis=1)
df = pd.DataFrame(data, columns=['connecting_features', 'places', 'number_of_complaints', 'road_priority'])
df.to_csv('./data.csv', index=False)

plt.plot(road_indices, road_priority)
plt.show()