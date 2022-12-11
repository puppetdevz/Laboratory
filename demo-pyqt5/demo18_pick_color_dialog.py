#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
颜色选取对话框
"""

import sys
from PyQt5 import QtWidgets, QtGui


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 定义按钮
        btn = QtWidgets.QPushButton('Pick Color', self)
        btn.move(20, 20)
        btn.clicked.connect(self.show_dialog)

        # 定义小框
        self.frm = QtWidgets.QFrame(self)
        # 初始化小框的背景色
        color = QtGui.QColor(0, 0, 0)
        # 设置小框的层叠样式
        self.frm.setStyleSheet('QWidget { background-color: %s }' % color.name())
        # 设置小框在主框中的位置和大小
        self.frm.setGeometry(130, 22, 100, 100)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Pick Color Dialog')
        self.show()

    def show_dialog(self):
        color = QtWidgets.QColorDialog.getColor()
        if color.isValid():
            self.frm.setStyleSheet('QWidget { background-color: %s }' % color.name())


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
