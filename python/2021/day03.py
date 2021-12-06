import os
import numpy as np
from math import ceil

dir_path = os.path.dirname(os.path.realpath(__file__))

def get_most_common_values(rows):
    return (sum(rows) >= ceil(len(rows) / 2)).astype(int)

def get_least_common_values(rows):
    return (sum(rows) < ceil(len(rows) / 2)).astype(int)

# part 1 & 2
with open(dir_path + '/day03.txt') as file:
    lines = []
    total = 0
    for line in file:
        row = np.array([int(bit) for bit in list(line.strip())])
        lines.append(row)
    gamma_rate = get_most_common_values(lines)
    epsilon_rate = get_least_common_values(lines)
    print(epsilon_rate.dot(1 << np.arange(epsilon_rate.size)[::-1]) * gamma_rate.dot(1 << np.arange(gamma_rate.size)[::-1]))

    i = 0
    rows = lines.copy()
    while len(rows) > 1 and i < len(rows[0]):
        mcv = get_most_common_values(rows)
        rows = [row for row in rows if row[i] == mcv[i]]
        i +=1
    oxygen_generator_rating = rows[0]

    i = 0
    rows = lines.copy()
    while len(rows) > 1 and i < len(rows[0]):
        lcv = get_least_common_values(rows)
        rows = [row for row in rows if row[i] == lcv[i]]
        i +=1   
    co2_scrubber_rating = rows[0]
    print(oxygen_generator_rating.dot(1 << np.arange(oxygen_generator_rating.size)[::-1]) * co2_scrubber_rating.dot(1 << np.arange(co2_scrubber_rating.size)[::-1]))
