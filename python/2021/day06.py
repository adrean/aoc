import os

dir_path = os.path.dirname(os.path.realpath(__file__))


# part 1 & 2
with open(dir_path + '/day06.txt') as file:
    fish_dict = {value:{'pv': 0, 'nv':0} for value in range(9)}
    fishes = [int(fish) for fish in next(file).split(',')]
    for fish in fishes:
        fish_dict[fish]['nv'] += 1
    print(len(fishes))
    for day in range(256):
        for i in range(9):
            fish_dict[i]['pv'] = fish_dict[i]['nv']
        for i in range(8):
            fish_dict[i]['nv'] = fish_dict[i + 1]['pv']
        fish_dict[6]['nv'] += fish_dict[0]['pv']
        fish_dict[8]['nv'] = fish_dict[0]['pv']
        print(f'day[{day + 1}] : {sum([fish["nv"] for fish in fish_dict.values()])}')
