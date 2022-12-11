#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
`QLineEdit` 组件提供了编辑文本的功能，自带了撤销、重做、剪切、粘贴、拖拽等功能。
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
        self.label = QtWidgets.QLabel(self)
        self.label.move(60, 40)

        line_edit = QtWidgets.QLineEdit(self)
        line_edit.move(60, 100)

        line_edit.textChanged[str].connect(self.on_changed)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('QLineEdit')
        self.show()

    def on_changed(self, text):
        self.label.setText(text)
        self.label.adjustSize()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
