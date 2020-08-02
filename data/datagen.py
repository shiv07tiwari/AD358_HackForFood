import pandas as pd
import random
import numpy as np

#complaint_id, road_id, defect_type, is_verified, is_resolved, assigned_insp_id, 
#insp_rep_id, status, reported_month, reported_year, resolved_month, resolved_year

road_id = [1,2,3,4,5]
defect_type = ['crack', 'shoulder', 'pothole', 'relayering']
is_verified = [True, False]
is_resolved = [True, False]
assigned_insp_id = [1,2,3,4]
status = ['unverified']
reported_month = [1,2,3,4,5,6,7,8,9,10,11,12]
reported_year = [2018,2019]
resolved_on_month = [1,2,3,4,5,6,7,8,9,10,11,12]
resolved_on_year = [2019,2020]

headers = ['complaint_id', 'road_id', 'defect_type', 'is_verified', 'is_resolved', 
            'assigned_insp_id', 'insp_rep_id', 'status', 'reported_month', 
            'reported_year', 'resolved_month', 'resolved_year']
data = []

for i in range(1,200):
    row = []
    row.append(i)
    road = random.choice(road_id)
    row.append(road)
    row.append(random.choice(defect_type))
    is_v = np.random.choice(is_verified)
    row.append(random.choice(assigned_insp_id))
    row.append('')
    row.append(random.choice(status))
    rp_m = random.choice(reported_month)
    rp_y = random.choice(reported_year)
    re_m = random.choice([1,2,3,4])
    re_y = rp_y
    if(rp_m + re_m > 12):
        re_m = (rp_m + re_m)%13
        re_y +=1
    else:
        re_m = rp_m + re_m
    row.append(rp_m)
    row.append(rp_y)
    row.append(re_m)
    row.append(re_y)
    data.append(row)

data = np.array(data)
data = pd.DataFrame(data, columns = headers)
data.to_csv('comp_data.csv', index = False)


    