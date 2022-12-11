#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
`QPixmap` 是处理图片的组件。
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
        pixmap = QPixmap('med.png')
        label = QtWidgets.QLabel(self)
        label.setPixmap(pixmap)

        hbox.addWidget(label)
        self.setLayout(hbox)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('QPixmap')
        self.show()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
