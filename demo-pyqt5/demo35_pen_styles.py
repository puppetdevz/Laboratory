#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
QPen 的样式
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
        self.setWindowTitle('Pen styles')
        self.show()

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写绘画事件处理器，默认为无作为"""
        painter = QtGui.QPainter()
        # 文本绘制只能在 painter 的 begin 和 end 之间进行
        painter.begin(self)
        self.draw_lines(painter)
        painter.end()

    def draw_lines(self, painter: QtGui.QPainter):
        """利用不同样式的笔，绘制不同样式的线"""
        # 创建一支笔，初始样式为：黑色，线粗为 2px，实线
        pen = QtGui.QPen(Qt.black, 2, Qt.SolidLine)
        painter.setPen(pen)
        painter.drawLine(20, 40, 250, 40)

        pen.setStyle(Qt.DashLine)
        painter.setPen(pen)
        painter.drawLine(20, 80, 250, 80)

        pen.setStyle(Qt.DashDotLine)
        painter.setPen(pen)
        painter.drawLine(20, 120, 250, 120)

        pen.setStyle(Qt.DotLine)
        painter.setPen(pen)
        painter.drawLine(20, 160, 250, 160)

        pen.setStyle(Qt.DashDotDotLine)
        painter.setPen(pen)
        painter.drawLine(20, 200, 250, 200)

        pen.setStyle(Qt.CustomDashLine)
        pen.setDashPattern([1, 4, 5, 4])
        painter.setPen(pen)
        painter.drawLine(20, 240, 250, 240)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
