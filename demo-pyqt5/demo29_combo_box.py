#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
`QComboBox` 组件能让用户在多个选择项中选择一个。
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
        combo = QtWidgets.QComboBox(self)
        combo.addItems(['Ubuntu', 'Mandriva', 'Fedora', 'Arch'])
        combo.move(50, 50)

        self.label = QtWidgets.QLabel('Ubuntu', self)
        self.label.move(50, 150)

        combo.activated[str].connect(self.on_activated)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Combo Box')
        self.show()

    def on_activated(self, text):
        self.label.setText(text)
        self.label.adjustSize()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
