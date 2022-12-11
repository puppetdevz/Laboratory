#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
QCalendarWidget 提供了基于月份的日历插件，十分简易而且直观。
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QDate


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        vbox = QtWidgets.QVBoxLayout(self)
        # 创建一个日历小组件
        calendar = QtWidgets.QCalendarWidget(self)
        calendar.setGridVisible(True)
        # 给 calendar 的点击事件信号绑定回调函数，同时设置入参为 QDate 类型
        calendar.clicked[QDate].connect(self.show_date)

        # 创建一个标签
        self.label = QtWidgets.QLabel(self)
        # 获取初始日期
        init_date = calendar.selectedDate()
        self.label.setText(init_date.toString())

        vbox.addWidget(calendar)
        vbox.addWidget(self.label)
        self.setLayout(vbox)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('QCalendarWidget')
        self.show()

    def show_date(self, date):
        """将点击后拿到的日期（QDate 对象）同步设置到标签 Label 中"""
        self.label.setText(date.toString())


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
