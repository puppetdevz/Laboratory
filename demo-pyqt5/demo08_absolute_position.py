#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/11
import sys

from PyQt5.QtWidgets import QApplication, QWidget, QLabel


class Example(QWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):
        label01 = QLabel('Python', self)
        label01.move(0, 0)
        label02 = QLabel('Java', self)
        label02.move(20, 20)
        label03 = QLabel('C++', self)
        label03.move(40, 40)
        self.setGeometry(450, 250, 600, 300)
        self.setWindowTitle('Absolute Position')
        self.show()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
