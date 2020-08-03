import pandas as pd
import random
import numpy as np

# {road_name=Dhandbad, road_grades=Give road grades, radio_protocol_signs=No, Description=Description, 
# anticipated_turnout_constructed=7, overhead_power_lines=None, remedial_codes=Install or repair, priority=low,
# local_name=Something, kilometre_marker_boards=Yes, hill=None, stop_sign=No, narrow_road_sections=No,
# audit=Construction, activities=Bridge, rail_crossing_present=Yes,
# risk=Moderate Safety/Environment Hazard, stream_culvert=Less than 20%, vehicle_usage=medium}
audit = ['Construction', 'Maintainance', 'Installation']
inspection_codes = ['Tension cracks visibly', 'Cutslope failures', 'Fillslope faliures', 'Potholes', 'Damage from cattle']
remedial_codes = ['Install or repair', 'License inspections required as per plan', 'Certify structures', 'Correct to speculations']
activities = ['Bridge', 'Culvert', 'Crossditch', 'Waterbar', 'Beaver', 'Ditching','Subgrade','Subgrade','Grading','Brushing', 'Sign']
priority = ['Low','Medium','High']
risk = ['No safety/Environment Hazard', 'Minor Safety/Environment Hazard', 'Moderate Safety/Environment Hazard']
stream_culvert = ['Stream', 'Hanging Outlet', 'Less than 20%']
stop_sign = ['Yes', 'No']
kilometre_marker_boards = ['Yes', 'No']
radio_protocol_signs = ['Yes','No']
rail_crossing_present = ['Yes','No']
overhead_power_lines = ['Yes','No']
hill = ['Yes','No']
narrow_road_sections = ['Yes','No']
vehicle_usage = ['Low','Medium','High']
com_id = road_id = range(1,200)

headers = ['sub_id','com_id','road_name', 'inspection_codes', 'road_grades', 'radio_protocol_signs', 'description', 'anticipated_turnout_constructed', 
            ' overhead_power_lines',
            'remedial_codes', 'priority', 'local_name','kilometre_marker_boards','hill','stop_sign', 'narrow_road_sections', 'audit',
             'activities',
            'rail_crossing_present','risk','stream_culvert', 'vehicle_usage']
data = []
# 22
for i in range(1,20):
    row = []
    row.append(i) #sub_id
    row.append(random.choice(com_id)) #com_id
    row.append("MG Road, Indore") #road_name
    row.append(random.choice(inspection_codes)) #insp_codes
    row.append("Grade-A") #road_grades
    row.append(random.choice(radio_protocol_signs)) #radio_protocol
    row.append("Description") #desc
    row.append(7) #anticipated
    row.append(random.choice(overhead_power_lines)) #power_ines
    row.append(random.choice(remedial_codes)) #rem_code
    row.append(random.choice(priority)) #priority
    row.append("Local Road Name") #local_name
    row.append(random.choice(kilometre_marker_boards)) #km_marker_boards
    row.append(random.choice(hill))
    row.append(random.choice(stop_sign))
    row.append(random.choice(narrow_road_sections))
    row.append(random.choice(audit))
    row.append(random.choice(activities))
    row.append(random.choice(rail_crossing_present))
    row.append(random.choice(risk))
    row.append(random.choice(stream_culvert))
    row.append(random.choice(vehicle_usage))


    data.append(row)

data = np.array(data)
data = pd.DataFrame(data, columns = headers)
data.to_csv('ver_form_data.csv', index = False)