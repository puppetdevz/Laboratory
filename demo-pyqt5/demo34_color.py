#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
颜色
"""
from importlib.metadata import PathDistribution
import random
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QDate, QMimeData
from PyQt5 import QtGui


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Color')
        self.show()

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写绘画事件处理器，默认为无作为"""
        painter = QtGui.QPainter()
        # 文本绘制只能在 painter 的 begin 和 end 之间进行
        painter.begin(self)
        self.draw_rectangles(painter)
        painter.end()

    def draw_rectangles(self, painter: QtGui.QPainter):
        """绘制矩阵"""
        # 自定义颜色
        color = QtGui.QColor(0, 0, 0)
        color.setNamedColor('#d4d4d4')
        painter.setPen(color)

        painter.setBrush(QtGui.QColor(200, 0, 0))
        painter.drawRect(10, 15, 90, 60)

        painter.setBrush(QtGui.QColor(255, 80, 0, 160))
        painter.drawRect(130, 15, 90, 60)

        painter.setBrush(QtGui.QColor(25, 0, 90, 200))
        painter.drawRect(250, 15, 90, 60)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
