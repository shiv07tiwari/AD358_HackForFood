import numpy as np
import pandas as pd
import string
import random


def random_string(size=6, chars=string.ascii_letters):
    return ''.join(random.choice(chars) for x in range(size))


project_id = np.random.randint(low=1, high=100, size=20).reshape(-1, 1)
road_length = np.random.uniform(low=0, high=200, size=20).reshape(-1, 1)

construction_status = np.random.choice(['In progress', 'To be started', 'Finished'], size=20).reshape(-1, 1)
contract_id = np.array([random_string() for i in range(20)]).reshape(-1, 1)
upper_level_compaction_test_ref = np.array([random_string() for i in range(20)]).reshape(-1, 1)
compaction_test_ref = np.array([random_string() for i in range(20)]).reshape(-1, 1)
complete_placement = np.array([random_string() for i in range(20)]).reshape(-1, 1)
removal_of_organic_materials = np.array([random_string() for i in range(20)]).reshape(-1, 1)
crusher_run_grade_acceptance = np.array([random_string() for i in range(20)]).reshape(-1, 1)
slashing = np.array([random_string() for i in range(20)]).reshape(-1, 1)

data = np.concatenate([project_id, road_length, construction_status, contract_id,
                       upper_level_compaction_test_ref, compaction_test_ref, complete_placement,
                       removal_of_organic_materials, crusher_run_grade_acceptance, slashing], axis=1)

df = pd.DataFrame(data, columns=['project_id', 'road_length', 'construction_status', 'contract_id',
                                 'upper_level_compaction_test_ref', 'compaction_test_ref', 'complete_placement',
                                 'removal_of_organic_materials', 'crusher_run_grade_acceptance', 'slashing'])
df.to_csv('tiwari.csv', index=False)