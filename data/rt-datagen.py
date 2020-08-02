import pandas as pd
import random
import numpy as np

para = ['Road construction requires the creation of an engineered continuous right-of-way or roadbed overcoming geographic obstacles and having grades low enough to permit vehicle or foot travel and may be required to meet standards set by law or official guidelines. The process is often begun with the removal of earth and rock by digging or blasting construction of embankments bridges and tunnels and removal of vegetation (this may involve deforestation) and followed by the laying of pavement material. A variety of road building equipment is employed in road building.','After design approval planning legal and environmental considerations have been addressed alignment of the road is set out by a surveyor. The radii and gradient are designed and staked out to best suit the natural ground levels and minimize the amount of cut and fill. Great care is taken to preserve reference Benchmarks', 'Roads are designed and built for primary use by vehicular and pedestrian traffic. Storm drainage and environmental considerations are a major concern. Erosion and sediment controls are constructed to prevent detrimental effects. Drainage lines are laid with sealed joints in the road easement with runoff coefficients and characteristics adequate for the land zoning and storm water system. Drainage systems must be capable of carrying the ultimate design flow from the upstream catchment with approval for the outfall from the appropriate authority to a watercourse creek river or the sea for drainage discharge']
line = ['road damage needs to prepared', 'shoulder need repair', 'complete revamp is required']
cost_overrun = [True, False]
defect_type = ['crack', 'shoulder', 'pothole', 'relayering']
cons_year = [2010,2011, 2012, 1998, 1999, 2019]
cons_month = [1,2,3,4,5,6,7,8,9,10,11,12]
comp_year = [2018,2019]
comp_month = [1,2,3,4,5,6,7,8,9,10,11,12]

headers = ['road_id', 'tender_id',  'cons_month', 'cons_year',  'comp_month', 'comp_year', 'est_cost', 'act_cost', 'tender_details', 'tender_remarks']
data = []

for i in range(1,500):
    row = []
    
    road = random.choice(range(50))
    row.append(road)
    row.append(i)
    cs_m = random.choice(cons_month)
    cs_y = random.choice(cons_year)
    cp_m = random.choice([1,3,5,7,8,11])
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
    est_cost = int(random.uniform(0.5,10.0)*100000)
    row.append(est_cost)
    ovrr = random.choice(cost_overrun)
    if ovrr:
        row.append(int(est_cost + est_cost*(random.uniform(0.01,0.7))))
    else:
        row.append(est_cost) 
    row.append(random.choice(para))
    row.append(random.choice(line))
    data.append(row)

data = np.array(data)
data = pd.DataFrame(data, columns = headers)
data.to_csv('rt_data.csv', index = False)


    
