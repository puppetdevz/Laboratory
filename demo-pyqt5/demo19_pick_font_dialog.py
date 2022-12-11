#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
字体选取对话框
"""

import sys
from PyQt5 import QtWidgets, QtGui


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        vbox = QtWidgets.QVBoxLayout()
        # 定义按钮
        btn = QtWidgets.QPushButton('Pick Font', self)
        btn.setSizePolicy(QtWidgets.QSizePolicy.Fixed, QtWidgets.QSizePolicy.Fixed)
        btn.clicked.connect(self.show_dialog)

        # 定义标签
        self.label = QtWidgets.QLabel('Knowledge only matters', self)

        # 调整按钮和标签的位置。注意：如果用了盒布局或栅格布局，则该行为是无效的，会以布局为准
        # btn.move(20, 20)
        # self.label.move(130, 20)

        vbox.addWidget(btn)
        vbox.addWidget(self.label)
        self.setLayout(vbox)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Pick Font Dialog')
        self.show()

    def show_dialog(self):
        font, ok = QtWidgets.QFontDialog.getFont()
        if ok:
            self.label.setFont(font)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
