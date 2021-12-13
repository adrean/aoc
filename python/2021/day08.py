import os
from statistics import mean, median
from math import floor, ceil
dir_path = os.path.dirname(os.path.realpath(__file__))


# part 1
with open(dir_path + '/day08.txt') as file:
    count = 0
    for line in file:
        digits = [digit.strip() for digit in line.split('|')[1].strip().split(' ')]
        for digit in digits:
            if len(digit) in [2, 3, 4, 7]:
                count += 1
    print(count)

# part 2
with open(dir_path + '/day08.txt') as file:
    total = 0
    for line in file:
        splitted = line.split('|')
        patterns = splitted[0].strip().split(' ')
        digits = splitted[1].strip().split(' ')
        rules = [0] * 10
        for pattern in patterns:
            if len(pattern) == 2:
                rules[1] = set(pattern)
            elif len(pattern) == 3:
                rules[7] = set(pattern)
            elif len(pattern) == 4:
                rules[4] = set(pattern)
            elif len(pattern) == 7:
                rules[8] = set(pattern)
        for pattern in patterns:
            if len(pattern) == 5 and rules[1].issubset(set(pattern)):
                rules[3] = set(pattern)
            elif len(pattern) == 6 and rules[4].issubset(set(pattern)):
                rules[9] = set(pattern)
        for pattern in patterns:
            if len(pattern) == 5 and set(pattern).issubset(rules[9]) and set(pattern) not in rules:
                rules[5] = set(pattern)
            elif len(pattern) == 6 and rules[1].issubset(set(pattern)) and set(pattern) not in rules:
                rules[0] = set(pattern)
        for pattern in patterns:
            if len(pattern) == 5 and set(pattern) not in rules:
                rules[2] = set(pattern)
            elif len(pattern) == 6 and set(pattern) not in rules:
                rules[6] = set(pattern)
        value = int(''.join([str(rules.index(set(digit))) for digit in digits]))
        total += value
    print(total)
