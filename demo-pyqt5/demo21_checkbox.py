#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        checkbox = QtWidgets.QCheckBox('Show Title', self)
        checkbox.move(20, 20)
        # 调用 CheckBox 的构造器
        checkbox.toggle()
        # 绑定状态变化时的回调函数
        checkbox.stateChanged.connect(self.change_title)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('CheckBox')
        self.show()

    def change_title(self, state):
        """修改 title"""
        if state == Qt.Checked:
            self.setWindowTitle('CheckBox')
        else:
            self.setWindowTitle('')


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
