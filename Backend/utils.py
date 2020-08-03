import numpy as np
import pickle
import pandas as pd


def get_age(x):
    return 2020 - x


def scale(arr, min, max):
    arr = (arr - np.min(arr)) / (np.max(arr) - np.min(arr))
    arr *= (max - min)
    return arr + min


def get_prioritized(data):
    data = data.sort_values(by='priority', ascending=False)
    return data


def get_repair_time(data, model, sc_road_life, as_str = False):
    features = data[['activeYear', 'single_axle_load', 'crack', 'potholes', 'rut', 'long_crack']]
    id = list(data['road_id'].values)

    features['activeYear'] = features['activeYear'].apply(get_age)  # age
    features = features.to_numpy()
    age = np.copy(features[:, 0])
    features[:, 0] = np.power(features[:, 0], 1.78)

    X = sc_road_life.transform(features)
    iri = model.predict(X).reshape(-1, )

    a = 7.99
    b = 0.0072
    pred = (1 / b) * np.log(a / iri) - age
    pred = scale(pred, 0.0834, 3)  # in years

    order = np.argsort(pred)

    ret_data = {}
    for idx in order:

        if as_str :
            ret_data[str(id[idx])] = round(pred[idx] * 12)
        else :
            ret_data[id[idx]] = round(pred[idx] * 12)

    return ret_data


def get_repair_cost(data, cost_model, sc_X, sc_y, time_model, sc):
    ids = list(data['road_id'].values)
    repair_times = get_repair_time(data, time_model, sc)

    features = data[['hvd', 'lvd', 'activeYear', 'average_temperature', 'average_temperature',
                     'no_shoulder', 'mountain', 'bridge']]
    features['activeYear'] = features['activeYear'].apply(get_age)  # age
    features = features.to_numpy()

    lane_type = data['category'].values

    X_numeric = sc_X.transform(features)
    X = []

    for i in range(X_numeric.shape[0]):
        f = list(X_numeric[i])
        lane = [1., 0., 0., 0.]

        if lane_type[i] == 'ne':
            lane = [0., 0., 0., 1.]
        elif lane_type[i] == 'nh':
            lane = [0., 0., 1., 0.]
        elif lane_type[i] == 'rr':
            lane = [0., 1., 0., 0.]

        f.extend(lane)
        X.append(np.array(f))

    X = np.array(X)
    pred = cost_model.predict(X)
    pred = sc_y.inverse_transform(pred).reshape(-1, )

    ret_data = {'1': 0,
                '2': 0,
                '3': 0,
                '4': 0,
                '5': 0,
                '6': 0,
                '7': 0,
                '8': 0,
                '9': 0,
                '10': 0,
                '11': 0,
                '12': 0,
                'more_than_an_year': 0}

    for id, time in repair_times.items():
        idx = ids.index(id)
        if 0 <= time < 1:
            ret_data['1'] += pred[idx]
        elif 1 <= time < 2:
            ret_data['2'] += pred[idx]
        elif 2 <= time < 3:
            ret_data['3'] += pred[idx]
        elif 3 <= time < 4:
            ret_data['4'] += pred[idx]
        elif 4 <= time < 5:
            ret_data['5'] += pred[idx]
        elif 5 <= time < 6:
            ret_data['6'] += pred[idx]
        elif 7 <= time < 8:
            ret_data['8'] += pred[idx]
        elif 8 <= time < 9:
            ret_data['9'] += pred[idx]
        elif 9 <= time < 10:
            ret_data['10'] += pred[idx]
        elif 10 <= time < 11:
            ret_data['11'] += pred[idx]
        elif 11 <= time < 12:
            ret_data['12'] += pred[idx]
        else:
            ret_data['more_than_an_year'] += pred[idx]

    return ret_data


# data = pd.read_csv('./final-road-data.csv')
# road_life_model = pickle.load(open('./road_life_prediction_model.sav', 'rb'))
# road_cost_model = pickle.load(open('./road_cost_prediction.sav', 'rb'))

# road_life_scaler = pickle.load(open('./road_life_scaler.pkl', 'rb'))
# road_cost_scaler_X = pickle.load(open('./road_cost_scaler_X.pkl', 'rb'))
# road_cost_scaler_y = pickle.load(open('./road_cost_scaler_y.pkl', 'rb'))

# # print(get_prioritized(data)['priority'])
# print(get_repair_cost(data, road_cost_model, road_cost_scaler_X, road_cost_scaler_y,
#                       road_life_model, road_life_scaler))
