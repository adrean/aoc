import os

dir_path = os.path.dirname(os.path.realpath(__file__))
values = []
# part 1
with open(dir_path + '/day02.txt') as file:
    horiz_pos = 0
    depth = 0
    
    for line in file:
        action, value = line.split(' ')
        value = int(value)
        if action == 'forward':
            horiz_pos += value
        elif action == 'down':
            depth += value
        elif action == 'up':
            depth -= value

    print(f'depth:{depth} * horizontal position:{horiz_pos} = {depth * horiz_pos}')
# part 2
with open(dir_path + '/day02.txt') as file:
    horiz_pos = 0
    depth = 0
    aim = 0
    for line in file:
        action, value = line.split(' ')
        value = int(value)
        if action == 'forward':
            horiz_pos += value
            depth += aim * value
        elif action == 'down':
            aim += value
        elif action == 'up':
            aim -= value

    print(f'depth:{depth} * horizontal position:{horiz_pos} = {depth * horiz_pos}')
