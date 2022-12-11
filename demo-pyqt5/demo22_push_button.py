#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
切换按钮就是 `QPushButton` 的一种特殊模式。

它只有两种状态：按下 和 未按下。
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QColor


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 初始颜色
        self.color = QColor(0, 0, 0)

        red_btn = QtWidgets.QPushButton('Red Button', self)
        # 设置按钮为“切换按钮”模式
        red_btn.setCheckable(True)
        red_btn.move(10, 10)
        # 把点击信号和定义好的函数关联起来，clicked[bool] 是把点击事件转换成布尔值。
        red_btn.clicked[bool].connect(self.set_color)

        green_btn = QtWidgets.QPushButton('Green Button', self)
        green_btn.setCheckable(True)
        green_btn.move(10, 60)
        green_btn.clicked[bool].connect(self.set_color)

        blue_btn = QtWidgets.QPushButton('Blue Button', self)
        blue_btn.setCheckable(True)
        blue_btn.move(10, 110)
        blue_btn.clicked[bool].connect(self.set_color)

        self.square = QtWidgets.QFrame(self)
        self.square.setGeometry(150, 20, 100, 100)
        self.square.setStyleSheet('QWidget { background-color: %s }' % self.color.name())

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('QPushButton')
        self.show()

    def set_color(self, pressed):
        """设置颜色"""
        # 获取信号发送对象
        source = self.sender()
        if pressed:
            value = 255
        else:
            value = 0

        if source.text() == 'Red Button':
            self.color.setRed(value)
        elif source.text() == 'Green Button':
            self.color.setGreen(value)
        else:
            self.color.setBlue(value)

        self.square.setStyleSheet('QFrame { background-color: %s }' % self.color.name())


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
