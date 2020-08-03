import pandas as pd
import numpy as np
from utils import scale
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error
from sklearn.preprocessing import StandardScaler
import pickle

ht_aadt = 0.2 * np.random.randint(low=0, high=10000, size=10000).reshape(-1, 1)
p_and_l_aadt = 0.1 * np.random.randint(low=0, high=50000, size=10000).reshape(-1, 1)
age = np.random.randint(low=0, high=30, size=10000).reshape(-1, 1)
ann_temp = np.random.uniform(low=13, high=42, size=10000).reshape(-1, 1)
shoulder_width = np.random.uniform(low=2.5, high=8, size=10000).reshape(-1, 1)
no_shoulder = np.random.randint(low=0, high=2, size=10000).reshape(-1, 1)
mountain = np.random.randint(low=0, high=2, size=10000).reshape(-1, 1)
bridge = np.random.randint(low=0, high=2, size=10000).reshape(-1, 1)

single_lane = np.array([np.array([1., 0., 0., 0.]) for i in range(2500)]).reshape(-1, 4)
double_lane = np.array([np.array([0., 1., 0., 0.]) for i in range(2500)]).reshape(-1, 4)
urban_road = np.array([np.array([0., 0., 1., 0.]) for i in range(2500)]).reshape(-1, 4)
express_highway = np.array([np.array([0., 0., 0., 1.]) for i in range(2500)]).reshape(-1, 4)

# single_lane
X1 = np.concatenate([ht_aadt[:2500], p_and_l_aadt[:2500], age[:2500], ann_temp[:2500],
                     shoulder_width[:2500], no_shoulder[:2500], mountain[:2500], bridge[:2500],
                     single_lane],
                    axis=1)

cost1 = ht_aadt[:2500] * p_and_l_aadt[:2500] * age[:2500] * ann_temp[:2500] * \
        shoulder_width[:2500] * np.exp(no_shoulder[:2500]) * np.exp(mountain[:2500]) * \
        np.exp(bridge[:2500])

cost1 = scale(cost1, 100000, 1200000)

# double_lane
X2 = np.concatenate([ht_aadt[2500:5000], p_and_l_aadt[2500:5000], age[2500:5000], ann_temp[2500:5000],
                     shoulder_width[2500:5000], no_shoulder[2500:5000], mountain[2500:5000], bridge[2500:5000],
                     double_lane],
                    axis=1)

cost2 = ht_aadt[2500:5000] * p_and_l_aadt[2500:5000] * age[2500:5000] * ann_temp[2500:5000] * \
        shoulder_width[2500:5000] * np.exp(no_shoulder[2500:5000]) * np.exp(mountain[2500:5000]) * \
        np.exp(bridge[2500:5000])

cost2 = scale(cost2, 1200000, 2500000)

# urban
X3 = np.concatenate([ht_aadt[5000:7500], p_and_l_aadt[5000:7500], age[5000:7500], ann_temp[5000:7500],
                     shoulder_width[5000:7500], no_shoulder[5000:7500], mountain[5000:7500], bridge[5000:7500],
                     urban_road],
                    axis=1)

cost3 = ht_aadt[5000:7500] * p_and_l_aadt[5000:7500] * age[5000:7500] * ann_temp[5000:7500] * \
        shoulder_width[5000:7500] * np.exp(no_shoulder[5000:7500]) * np.exp(mountain[5000:7500]) * \
        np.exp(bridge[5000:7500])

cost3 = scale(cost3, 2500000, 5000000)

# express_highway
X4 = np.concatenate([ht_aadt[7500:], p_and_l_aadt[7500:], age[7500:], ann_temp[7500:],
                     shoulder_width[7500:], no_shoulder[7500:], mountain[7500:], bridge[7500:],
                     express_highway],
                    axis=1)

cost4 = ht_aadt[7500:] * p_and_l_aadt[7500:] * age[7500:] * ann_temp[7500:] * \
        shoulder_width[7500:] * np.exp(no_shoulder[7500:]) * np.exp(mountain[7500:]) * \
        np.exp(bridge[7500:])

cost4 = scale(cost4, 5000000, 40000000)

X = np.concatenate([X1, X2, X3, X4], axis=0)
cost = np.concatenate([cost1, cost2, cost3, cost4], axis=0)

data = np.concatenate([X, cost.reshape(-1, 1)], axis=1)
np.random.shuffle(data)

df = pd.DataFrame(data, columns=['HT_ADDR', 'P_and_L_AADT', 'Age', 'AA_Temp', 'Shoulder_width', 'No_shoulder',
                                 'mountain', 'bridge', 'single_lane', 'double_lane', 'urban', 'express', 'cost/km'])

df.to_csv('Road_cost.csv', index=False)


# Random Forest model
model = RandomForestRegressor(max_depth=20)
X = data[:, :-1]
y = data[:, -1]

sc_X = StandardScaler()
sc_y = StandardScaler()

X_scale_numeric = sc_X.fit_transform(X[:, :8])
X_scale = np.concatenate([X_scale_numeric, X[:, 8:]], axis=1)
y_scale = sc_y.fit_transform(y.reshape(-1, 1))

model.fit(X_scale, y_scale.reshape(-1,))

pred = model.predict(X_scale)

print(mean_squared_error(y_scale, pred))

# Save model and scaler
filename = './road_cost_prediction.sav'
pickle.dump(model, open(filename, 'wb'))

sc_x_filename = './road_cost_scaler_X.pkl'
sc_y_filename = './road_cost_scaler_y.pkl'
pickle.dump(sc_X, open(sc_x_filename, 'wb'))
pickle.dump(sc_y, open(sc_y_filename, 'wb'))
