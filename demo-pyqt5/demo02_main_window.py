#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/3
import sys

from PyQt5.QtWidgets import QApplication, QMainWindow, QDesktopWidget


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.statusBar().showMessage('Ready')
        # move() 与 resize() 的结合，前两个入参看作是 move() 的入参，后两个是 resize() 的入参
        # self.setGeometry(300, 300, 250, 150)
        self.resize(300, 200)
        self.center()
        self.setWindowTitle('Statusbar')
        self.show()

    def center(self):
        qr = self.frameGeometry()
        center_poit = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(center_poit)
        self.move(qr.topLeft())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
