import pandas as pd
import numpy as np
from sklearn.preprocessing import StandardScaler
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

# target
# iri = np.random.uniform(low=1.5, high=5.99, size=10000)

# inputs
sc = StandardScaler()

age = np.random.randint(low=1, high=15, size=10000)
exp_age = np.power(list(age), 1.78)
norm_age = sc.fit_transform(exp_age.reshape(-1, 1))

esal = sc.fit_transform(np.random.uniform(low=721418.14, high=1676597.84, size=10000).reshape(-1, 1))
cracking = sc.fit_transform(np.random.uniform(low=10.0, high=45.0, size=10000).reshape(-1, 1))
potholes = sc.fit_transform(np.random.randint(low=0, high=19, size=10000).reshape(-1, 1))
rutting = sc.fit_transform(np.random.uniform(low=2.2, high=24, size=10000).reshape(-1, 1))
long_crack = sc.fit_transform(np.random.uniform(low=2.5, high=29, size=10000).reshape(-1, 1))

iri = norm_age + esal + cracking + potholes + rutting + long_crack
iri = (iri-np.min(iri)) / (np.max(iri)-np.min(iri)) * 4.49 + 1.5


X = np.concatenate([norm_age, esal, cracking, potholes, rutting, long_crack], axis=1)

# Linear Regression
model = LinearRegression()
model.fit(X, iri)

pred_train = model.predict(X)
print(mean_squared_error(iri, pred_train))

# Testing
test_age = np.array([i for i in range(1, 16)])
exp_test_age = np.power(test_age, 1.78)
test_norm_age = sc.fit_transform(exp_test_age.reshape(-1, 1))

esal = sc.fit_transform(np.random.uniform(low=721418.14, high=1676597.84, size=15).reshape(-1, 1))
cracking = sc.fit_transform(np.random.uniform(low=10.0, high=45.0, size=15).reshape(-1, 1))
potholes = sc.fit_transform(np.random.randint(low=0, high=19, size=15).reshape(-1, 1))
rutting = sc.fit_transform(np.random.uniform(low=2.2, high=24, size=15).reshape(-1, 1))
long_crack = sc.fit_transform(np.random.uniform(low=2.5, high=29, size=15).reshape(-1, 1))

test_X = np.concatenate([test_norm_age, esal, cracking, potholes, rutting, long_crack], axis=1)

pred = model.predict(test_X)

# RSL
a = 5.99
b = 0.3
rsl = (1/b)*np.log(a/1) - 5
print(rsl)


# AFE vs IRI
test_X = np.concatenate([test_norm_age, np.zeros((15, 5))], axis=1)
pred = model.predict(test_X)

plt.plot(test_age, pred)
plt.show()
