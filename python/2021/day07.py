import os
from statistics import mean, median
from math import floor, ceil
dir_path = os.path.dirname(os.path.realpath(__file__))


# part 1 & 2
with open(dir_path + '/day07.txt') as file:
    crabs=[int(crab) for crab in next(file).split(',')]
    median_crab=median(crabs)
    fuel = 0
    for crab in crabs:
        fuel += abs(crab - median_crab)
    print(fuel)

    mean_crab=floor(mean(crabs))
    fuel = 0
    for crab in crabs:
        if abs(crab - mean_crab) > 0:
            fuel += sum(range(1,abs(crab - mean_crab) + 1))
    print(fuel)