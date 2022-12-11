#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
文本涂鸦
"""
from curses import panel
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QDate, QMimeData
from PyQt5 import QtGui


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.text = 'hhhhxxixixixi'

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Draw text')
        self.show()

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写绘画事件处理器，默认为无作为"""
        painter = QtGui.QPainter()
        # 文本绘制只能在 painter 的 begin 和 end 之间进行
        painter.begin(self)
        self.draw_text(a0, painter)
        painter.end()

    def draw_text(self, event: QtGui.QPaintEvent, painter: QtGui.QPainter):
        """绘制文本"""
        painter.setPen(QtGui.QColor(168, 34, 3))
        painter.setFont(QtGui.QFont('Jetbrains Mono', 12))
        painter.drawText(event.rect(), Qt.AlignCenter, self.text)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
