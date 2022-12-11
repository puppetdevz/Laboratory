#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
时间对象
"""

import sys
from PyQt5.QtWidgets import QWidget, QApplication, QGridLayout, QLabel
from PyQt5.QtCore import Qt
from PyQt5 import QtGui


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 定义栅格布局
        grid = QGridLayout()
        grid.setSpacing(10)
        x, y = 0, 0
        # 定义局部变量文本 text 和实例属性 label
        text = "x: {0}, y: {1}".format(x, y)
        self.label = QLabel(text, self)
        # 将 label 部件添加到栅格中
        grid.addWidget(self.label, 0, 0, Qt.AlignTop)
        # 允许捕获鼠标移动
        self.setMouseTracking(True)
        self.setLayout(grid)

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Event Object')
        self.show()

    def mouseMoveEvent(self, a0: QtGui.QMouseEvent) -> None:
        """重写鼠标移动事件处理器"""
        x, y = a0.x(), a0.y()
        text = "x: {0}, y: {1}".format(x, y)
        self.label.setText(text)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
