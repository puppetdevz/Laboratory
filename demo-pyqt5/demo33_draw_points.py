#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
点的绘制
"""
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
        self.setWindowTitle('Draw points')
        self.show()

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写绘画事件处理器，默认为无作为"""
        painter = QtGui.QPainter()
        # 文本绘制只能在 painter 的 begin 和 end 之间进行
        painter.begin(self)
        self.draw_points(painter)
        painter.end()

    def draw_points(self, painter: QtGui.QPainter):
        """绘制点"""
        painter.setPen(Qt.red)
        # 获取窗口组件的大小
        size = self.size()
        for i in range(1000):
            # 绘制一千个点
            # 随机生成点的位置：x 和 y
            x = random.randint(1, size.width() - 1)
            y = random.randint(1, size.height() - 1)
            # 在生成的 x 和 y 点处画一个点
            painter.drawPoint(x, y)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
