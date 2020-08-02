import pandas as pd
import random
import numpy as np


defect_type = ['crack', 'shoulder', 'pothole', 'relayering']
cons_year = [2010,2011, 2012, 1998, 1999, 2019]
cons_month = [1,2,3,4,5,6,7,8,9,10,11,12]
comp_year = [2018,2019]
comp_month = [1,2,3,4,5,6,7,8,9,10,11,12]

headers = ['road_id', 'tender_id',  'cons_month', 'cons_year',  'comp_month', 'comp_year', 'est_cost', 'act_cost', 'road_defect']
data = []

for i in range(1,500):
    row = []
    
    road = random.choice(range(50))
    row.append(road)
    row.append(i)
    cs_m = random.choice(cons_month)
    cs_y = random.choice(cons_year)
    cp_m = random.choice([1,3,5,7])
    cp_y = cs_y
    if(cs_m + cp_m > 12):
        cp_m = (cs_m + cp_m)%13
        cp_y +=1
    else:
        cp_m = cs_m + cp_m
    row.append(cs_m)
    row.append(cs_y)
    row.append(cp_m)
    row.append(cp_y)
    row.append(random.choice(defect_type))
    data.append(row)

data = np.array(data)
data = pd.DataFrame(data, columns = headers)
data.to_csv('comp_data.csv', index = False)


    
