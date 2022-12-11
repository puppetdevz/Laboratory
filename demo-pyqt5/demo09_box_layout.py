#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/12
import sys

from PyQt5.QtWidgets import QWidget, QApplication, QPushButton, QHBoxLayout, QVBoxLayout


class Example(QWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):
        # 先创建两个按钮，分别是 OK 和 Cancel
        ok_btn = QPushButton('OK')
        cancel_btn = QPushButton('Cancel')

        # 水平盒布局
        hbox = QHBoxLayout()
        hbox.addStretch(1)
        hbox.addWidget(ok_btn)
        hbox.addWidget(cancel_btn)

        # 垂直盒布局
        vbox = QVBoxLayout()
        vbox.addStretch(1)
        vbox.addLayout(hbox)

        # 将盒布局设置到整个窗口中
        self.setLayout(vbox)

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Box Layout')
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
