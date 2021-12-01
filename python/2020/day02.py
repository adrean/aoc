values = []

with open('./day02.txt') as file:
    cpt_part1 = 0
    cpt_part2 = 0
    for line in file:
        rule, password = line.split(':')
        password = password.strip()
        range, letter = rule.split(' ')
        lower, higher = [int(value) for value in range.split('-')]
        count = password.count(letter)
        if lower <= count <= higher:
            cpt_part1 += 1

        count = 0
        if lower <= len(password):
            if password[lower - 1] == letter:
                count += 1

        if higher <= len(password):
            if password[higher - 1] == letter:
                count += 1
        if count == 1:
            cpt_part2 += 1
    print(cpt_part1)
    print(cpt_part2)
