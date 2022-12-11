#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/3
import sys

from PyQt5 import QtGui
from PyQt5.QtWidgets import QApplication, QMainWindow, QDesktopWidget, qApp, QMenu


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 注意：如果要窗口居中，则需要先调用 resize() 后再调 center() 否则 center() 没有效果
        self.resize(300, 200)
        self.center()
        # 设置主窗口的标题
        self.setWindowTitle('Context Menu')
        # 显示主窗口
        self.show()

    def center(self):
        qr = self.frameGeometry()
        center_point = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(center_point)
        self.move(qr.topLeft())

    def contextMenuEvent(self, event: QtGui.QContextMenuEvent) -> None:
        # 该菜单为右键菜单，故不需要名称
        context_menu = QMenu(self)
        # 给菜单添加以下三个行为：New、Open、Quit
        new_act = context_menu.addAction('New')
        open_act = context_menu.addAction('Open')
        quit_act = context_menu.addAction('Quit')
        # 使用 exec_() 调出“右键菜单”，self.mapToGlobal(event.pos()) 可以获取当前鼠标在屏幕中的位置
        action = context_menu.exec_(self.mapToGlobal(event.pos()))

        if action == quit_act:
            qApp.quit()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
