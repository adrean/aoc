values = []

with open('./day01.txt') as file:
    for line in file:
        values.append(int(line))

for i in range(0, len(values) - 1):
    for j in range(i, len(values)):
        if values[i]+values[j] == 2020:
            print(values[i]*values[j])

for i in range(0, len(values) - 2):
    for j in range(i, len(values) - 1):
        for k in range(j, len(values)):
            if values[i]+values[j]+values[k] == 2020:
                print(values[i]*values[j]*values[k])
