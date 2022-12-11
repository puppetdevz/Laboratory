#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/12
import sys
from PyQt5.QtWidgets import QWidget, QApplication, QGridLayout, QPushButton


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 定义栅格布局
        grid = QGridLayout()
        # 将窗口设置为栅格布局
        self.setLayout(grid)
        # 定义键名列表
        names = [
            'cls', 'bck', '', 'close',
            '7', '8', '9', '/',
            '4', '5', '6', ' * ',
            '1', '2', '3', '-',
            '0', '.', '=', '+'
        ]
        # 定义位置，元组形式
        positions = [(i, j) for i in range(5) for j in range(4)]
        # 将位置和键名一一对应，封装为一个新的对象，之后遍历对象迭代器
        for position, name in zip(positions, names):
            if name == '':
                continue
            btn = QPushButton(name)
            grid.addWidget(btn, *position)
        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Grid Layout')
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
