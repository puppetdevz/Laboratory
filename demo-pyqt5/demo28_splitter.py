#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
`QSplitter` 组件能让用户通过拖拽分割线的方式改变子窗口大小的组件。
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QDate
from PyQt5.QtGui import QPixmap


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        hbox = QtWidgets.QHBoxLayout(self)
        top_left = QtWidgets.QFrame(self)
        top_left.setFrameShape(QtWidgets.QFrame.StyledPanel)

        top_right = QtWidgets.QFrame(self)
        top_right.setFrameShape(QtWidgets.QFrame.StyledPanel)

        bottom = QtWidgets.QFrame(self)
        bottom.setFrameShape(QtWidgets.QFrame.StyledPanel)

        splitter1 = QtWidgets.QSplitter(Qt.Horizontal)
        splitter1.addWidget(top_left)
        splitter1.addWidget(top_right)

        splitter2 = QtWidgets.QSplitter(Qt.Vertical)
        splitter2.addWidget(splitter1)
        splitter2.addWidget(bottom)

        hbox.addWidget(splitter2)
        self.setLayout(hbox)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('QSplitter')
        self.show()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
