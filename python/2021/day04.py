import os
import numpy as np
from math import ceil
import sys

dir_path = os.path.dirname(os.path.realpath(__file__))

class Number():

    def __init__(self, value) -> None:
        self.value = value
        self.marked = False
    
    def __str__(self) -> str:
        if self.marked:

            return f'\033[94m{self.value}\033[0m' 
        else:
            return str(self.value)
    

class Board():

    def __init__(self) -> None:
        self.board = []
        self.bingo = False

    def __str__(self) -> str:
        board_str=''
        for row  in self.board:
            for number in row:
                board_str += f'{number}\t'
            board_str += '\n'
        return board_str

    def add_line(self, row):
        self.board.append([Number(value) for value in row])
    
    def draw(self, draw):
        for row in self.board:
            for number in row:
                if number.value == draw:
                    number.marked = True
        return self.check_bingo()

    def check_bingo(self):
        for row in self.board:
            if all([number.marked for number in row]):
                self.bingo = True
        for i in range(len(self.board[0])):
            if all(row[i].marked for row in self.board):
                self.bingo = True
        return self.bingo
    
    def get_sum_unmarked(self):
        total = 0
        for row in self.board:
            for number in row:
                if not number.marked:
                    total += number.value
        return total

# part 1 & 2
with open(dir_path + '/day04.txt') as file:
    draws = [int(number) for number in next(file).split(',')]
    print(draws)
    boards = []
    board = None
    for line in file:
        if line.strip() == '':
            if board:
                boards.append(board)
            board= Board()
        else:
            board.add_line([int(number) for number in line.split()])
    
    bingo_order=[]
    for draw in draws:
        for board in boards:
            if not board.bingo:
                if board.draw(draw):
                    bingo_order.append({'board': board, 'score': board.get_sum_unmarked() * draw})
    
    print('--------------FIRST BINGO')
    print(bingo_order[0]['board'])
    print(bingo_order[0]['score'])
    print('--------------LAST BINGO')
    print(bingo_order[-1]['board'])
    print(bingo_order[-1]['score'])