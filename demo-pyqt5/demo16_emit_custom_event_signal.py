#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
自定义 Qt 对象，并在对象中自定义事件信号
"""

import sys
from PyQt5.QtWidgets import QWidget, QApplication, QMainWindow, QPushButton
from PyQt5.QtCore import Qt, pyqtSignal, QObject
from PyQt5 import QtGui


class Communicator(QObject):
    """自定义 Qt 对象"""
    # 定义一个事件信号 close_app
    close_app = pyqtSignal()


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 创建自定义的 Qt 对象
        self.c = Communicator()
        # 给对象的事件信号 close_app 绑定回调函数
        self.c.close_app.connect(self.close)

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Event Sender')
        self.show()

    def mousePressEvent(self, a0: QtGui.QMouseEvent) -> None:
        """重写鼠标按下事件处理器"""
        # 发送自定义 Qt 对象里的自定义事件信号 close_app
        self.c.close_app.emit()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
