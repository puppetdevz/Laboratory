#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
单行文本对话框
"""

import sys
from PyQt5.QtWidgets import QWidget, QApplication, QMainWindow, QPushButton, QInputDialog, QLineEdit
from PyQt5 import QtGui


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        btn = QPushButton('Edit', self)
        btn.move(20, 20)
        btn.clicked.connect(self.show_dialog)

        # 定义一个行编辑部件
        self.line_edit = QLineEdit(self)
        self.line_edit.move(140, 22)

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Input Dialog')
        self.show()

    def show_dialog(self):
        # 返回的 input_value 默认均为 str 类型
        input_value, ok = QInputDialog.getText(self, 'Input Dialog', 'Enter your name: ')
        if ok:
            self.line_edit.setText(input_value)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
