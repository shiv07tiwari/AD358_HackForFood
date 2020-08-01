import numpy as np
import matplotlib.pyplot as plt
import itertools


connecting_transport = [x for x in range(1, 4)]
place_importance = [x for x in range(1, 6)]
number_of_complaints = [x/100. for x in range(0, 101)]

combinations = [connecting_transport, place_importance, number_of_complaints]

weights = np.array([2, 2, 1])

road_priority = []
for element in itertools.product(*combinations):
    element = np.array(element)
    element = np.sum(element*weights)
    road_priority.append(element)

road_priority = np.array(road_priority)
np.random.shuffle(road_priority)
road_indices = [id for id in range(1, road_priority.shape[0]+1)]

plt.plot(road_indices, road_priority)
plt.show()