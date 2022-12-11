#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/2
import sys

from PyQt5 import QtGui
from PyQt5.QtWidgets import QWidget, QPushButton, QApplication, QMessageBox, QDesktopWidget
from PyQt5.QtCore import QCoreApplication
from loguru import logger


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.initUI()

    def initUI(self):
        btn = QPushButton('Quit', self)
        btn.clicked.connect(QCoreApplication.instance().quit)
        btn.resize(btn.sizeHint())
        btn.move(50, 50)

        self.setGeometry(300, 300, 250, 150)
        self.center()
        self.setWindowTitle('quit button')
        self.show()

    def closeEvent(self, a0: QtGui.QCloseEvent) -> None:
        logger.info('I am called')
        reply = QMessageBox.question(self,
                                     'Message',
                                     "Are you sure to quit?",
                                     QMessageBox.No | QMessageBox.Yes,
                                     QMessageBox.No)
        if reply == QMessageBox.Yes:
            a0.accept()
        else:
            a0.ignore()

    def center(self):
        """实现窗口居中"""
        # 获取 self 窗口所在框架
        qr = self.frameGeometry()
        # 获取显示器的分辨率，得到屏幕中间点的位置
        center_poit = QDesktopWidget().availableGeometry().center()
        # 把主窗口框架的中心点放置到屏幕的中心位置
        qr.moveCenter(center_poit)
        # 吧主窗口的左上角移动到其框架的左上角，从而实现居中
        self.move(qr.topLeft())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
