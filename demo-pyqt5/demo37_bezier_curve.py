#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
绘制贝赛尔曲线
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
        self.setWindowTitle('Bezier curve')
        self.show()

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写绘画事件处理器，默认为无作为"""
        painter = QtGui.QPainter()
        # 文本绘制只能在 painter 的 begin 和 end 之间进行
        painter.begin(self)
        painter.setRenderHint(QtGui.QPainter.Antialiasing)
        self.draw_bezier_curve(painter)
        painter.end()

    def draw_bezier_curve(self, painter: QtGui.QPainter):
        """利用 QPainterPath 绘制”贝塞尔曲线“"""
        path = QtGui.QPainterPath()
        path.moveTo(30, 30)
        path.cubicTo(30, 30, 200, 350, 350, 30)
        painter.drawPath(path)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
