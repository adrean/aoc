import os

dir_path = os.path.dirname(os.path.realpath(__file__))

# part 1
with open(dir_path + '/day05.txt') as file:
    grid = []
    for i in range(1000):
        grid.append([0] * 1000)
    for line in file:
        (x1, y1), (x2, y2) =[[int(coord) for coord in point.strip().split(',')] for point in line.split('->')]
        if (x1 == x2 or y1 == y2):
            x_range = range(x1, x2 + 1) if x1 <= x2 else range(x1, x2 - 1, -1)
            for x in x_range:
                y_range = range(y1, y2 + 1) if y1 <= y2 else range(y1, y2 - 1, -1)
                for y in y_range:
                    grid[x][y] += 1
    
    cpt = 0
    for row in grid:
        for cell in row:
            if cell >= 2:
                cpt += 1
    print(cpt)

# part 2
with open(dir_path + '/day05.txt') as file:
    grid = []
    for i in range(1000):
        grid.append([0] * 1000)
    for line in file:
        (x1, y1), (x2, y2) =[[int(coord) for coord in point.strip().split(',')] for point in line.split('->')]
        if (x1 == x2 or y1 == y2):
            x_range = range(x1, x2 + 1) if x1 <= x2 else range(x1, x2 - 1, -1)
            for x in x_range:
                y_range = range(y1, y2 + 1) if y1 <= y2 else range(y1, y2 - 1, -1)
                for y in y_range:
                    grid[x][y] += 1
        elif abs(x2 - x1) == abs(y2 - y1):
            x_step = 1 if x1 < x2 else -1
            y_step = 1 if y1 < y2 else -1
            for i in range(abs(x2 - x1) + 1):
                grid[x1 + i * x_step][y1 + i * y_step] += 1

    cpt = 0
    for row in grid:
        for cell in row:
            if cell >= 2:
                cpt += 1
    print(cpt)

