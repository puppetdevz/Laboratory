#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
1. 获取事件发送者
2. 按钮点击时间
"""

import sys
from PyQt5.QtWidgets import QWidget, QApplication, QMainWindow, QPushButton
from PyQt5.QtCore import Qt
from PyQt5 import QtGui


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        btn1 = QPushButton("Button 1", self)
        btn2 = QPushButton("Button 2", self)
        btn1.move(30, 50)
        btn2.move(150, 50)

        # 给按钮的点击事件绑定回调函数
        btn1.clicked.connect(self.buttonClicked)
        btn2.clicked.connect(self.buttonClicked)

        # 创建状态栏
        self.statusBar()

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Event Sender')
        self.show()

    def buttonClicked(self):
        """自定义事件触发回调函数"""
        sender = self.sender()
        self.statusBar().showMessage(sender.text() + ' was pressed')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
