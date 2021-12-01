values = []

# part 2
with open('./day01.txt') as file:
    previous = int(next(file))
    count = 0
    for line in file:
        if int(line) > previous:
            count += 1
        previous = int(line)

    print(count)

# part 2
with open('./day01.txt') as file:
    count = 0
    window = []
    for _ in range(3):
        window.append(int(next(file)))
    for line in file:
        if int(line) > window.pop(0):
            count += 1
        window.append(int(line))
    print(count)
