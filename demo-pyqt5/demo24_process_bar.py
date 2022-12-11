#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
进度条。用来展示任务进度。它的滚动能让用户了解到任务的进度。
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QBasicTimer, QTimerEvent
from PyQt5.QtGui import QColor, QPixmap


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        self.process_bar = QtWidgets.QProgressBar(self)
        self.process_bar.setGeometry(30, 40, 200, 25)

        self.btn = QtWidgets.QPushButton('Start', self)
        self.btn.move(40, 80)
        self.btn.clicked.connect(self.do_action)

        # 创建一个基本的计时器
        self.timer = QBasicTimer()
        self.step = 0

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('QProgressBar')
        self.show()

    def timerEvent(self, a0: 'QTimerEvent') -> None:
        """计时器事件处理器"""
        if self.step >= 100:
            self.timer.stop()
            self.btn.setText('Finished')
            return

        self.step = self.step + 1
        self.process_bar.setValue(self.step)

    def do_action(self):
        # 如果计时器是激活的状态
        if self.timer.isActive():
            self.timer.stop()
            self.btn.setText('Start')
        # 否则
        else:
            # 设置计时器的步长，单位为微秒，意思为每隔多少微秒就触发一次计时器事件
            self.timer.start(100, self)
            self.btn.setText('Stop')


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
