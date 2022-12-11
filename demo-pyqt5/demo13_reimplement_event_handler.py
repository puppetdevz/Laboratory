#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
复写时间处理器
"""

import sys
from PyQt5.QtWidgets import QWidget, QApplication
from PyQt5.QtCore import Qt
from PyQt5 import QtGui


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Reimplement Event Handler')
        self.show()

    def keyPressEvent(self, a0: QtGui.QKeyEvent) -> None:
        """复写按键按下时的事件处理器"""
        if a0.key() == Qt.Key_Escape:
            self.close()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
