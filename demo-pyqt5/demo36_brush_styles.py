#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
QBrush 的样式
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
        self.setWindowTitle('Brush styles')
        self.show()

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写绘画事件处理器，默认为无作为"""
        painter = QtGui.QPainter()
        # 文本绘制只能在 painter 的 begin 和 end 之间进行
        painter.begin(self)
        self.draw_rectangles(painter)
        painter.end()

    def draw_rectangles(self, painter: QtGui.QPainter):
        """利用不同样式的刷子，绘制具有不同填充样式的矩形"""
        # 创建一把刷子，初始样式为：实体模式
        brush = QtGui.QBrush(Qt.SolidPattern)
        painter.setBrush(brush)
        painter.drawRect(10, 15, 90, 60)

        brush.setStyle(Qt.Dense1Pattern)
        painter.setBrush(brush)
        painter.drawRect(130, 15, 90, 60)

        brush.setStyle(Qt.Dense2Pattern)
        painter.setBrush(brush)
        painter.drawRect(250, 15, 90, 60)

        brush.setStyle(Qt.DiagCrossPattern)
        painter.setBrush(brush)
        painter.drawRect(10, 105, 90, 60)

        brush.setStyle(Qt.Dense5Pattern)
        painter.setBrush(brush)
        painter.drawRect(130, 105, 90, 60)

        brush.setStyle(Qt.Dense6Pattern)
        painter.setBrush(brush)
        painter.drawRect(250, 15, 90, 60)

        brush.setStyle(Qt.HorPattern)
        painter.setBrush(brush)
        painter.drawRect(10, 195, 90, 60)

        brush.setStyle(Qt.VerPattern)
        painter.setBrush(brush)
        painter.drawRect(130, 195, 90, 60)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
