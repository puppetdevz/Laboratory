#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/3
import sys

from PyQt5.QtWidgets import QApplication, QMainWindow, QDesktopWidget, QAction, qApp, QMenu
from PyQt5.QtGui import QIcon


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        # 创建一个状态栏
        self.status_bar = self.statusBar()
        self.init_ui()

    def init_ui(self):
        # 给状态栏定义文字信息作为状态
        self.status_bar.showMessage('Ready')

        # 新建菜单栏，并给菜单栏添加菜单，再给菜单添加行为
        # 创建菜单栏
        menubar = self.menuBar()
        # menubar.setNativeMenuBar(False)
        view_menu = menubar.addMenu('View')
        # 创建行为
        view_state_act = QAction('View StatusBar', self)
        # 设置为可勾选的
        view_state_act.setCheckable(True)
        view_state_act.setToolTip('View StatusBar')
        # 设置默认勾选状态为 True
        view_state_act.setChecked(True)
        # 设置触发时的行为，默认会将 checked 状态传入
        view_state_act.triggered.connect(self.toggle)
        view_menu.addAction(view_state_act)

        # 注意：如果要窗口居中，则需要先调用 resize() 后再调 center() 否则 center() 没有效果
        self.resize(300, 200)
        self.center()
        # 设置主窗口的标题
        self.setWindowTitle('Checked Menu')
        # 显示主窗口
        self.show()

    def toggle(self, state):
        if state:
            self.status_bar.show()
        else:
            self.status_bar.hide()

    def center(self):
        qr = self.frameGeometry()
        center_point = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(center_point)
        self.move(qr.topLeft())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
