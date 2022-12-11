#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
自定义组件：Burning widget（该组件常见于 Nero、K3B，或者其他 CD/DVD 烧录软件中）
"""
from importlib.metadata import PathDistribution
import random
from re import A
import sys
import typing

from PyQt5.QtWidgets import QWidget, QApplication, QSlider, QHBoxLayout, QVBoxLayout
from PyQt5.QtCore import Qt, QMimeData, QObject, pyqtSignal
from PyQt5 import QtGui


class Communicator(QObject):
    update_burning_widget = pyqtSignal(int)


class BurningWidget(QWidget):
    """自定义组件，烧制进度组件，常见于刻录软件

    Args:
        QWidget (QWidget): 父类，PyQt5.QtWidget.QWidget
    """

    def __init__(self) -> None:
        super().__init__()
        self.init_ui()

    def init_ui(self):
        """初始化图形界面
        """
        # 设置最小尺寸，1 为 min_width，30 为 min_height
        self.setMinimumSize(1, 30)
        # 当前值
        self.value = 75
        # 刻度值
        self.num = [75, 150, 225, 300, 375, 450, 525, 600, 675]

    def set_value(self, value):
        """设置当前值

        Args:
            value (int): 当前值
        """
        self.value = value

    def paintEvent(self, a0: QtGui.QPaintEvent) -> None:
        """重写 paintEvent 事件处理器

        Args:
            a0 (QtGui.QPaintEvent): _description_
        """
        painter = QtGui.QPainter()
        painter.begin(self)
        self.draw_widget(painter)
        painter.end()

    def draw_widget(self, painter: QtGui.QPainter):
        """绘制刻度板

        Args:
            painter (QtGui.QPainter): _description_
        """
        # 最大容量
        MAX_CAPACITY = 700
        # 超出
        OVER_CAPACITY = 750

        # 给 painter 设置字体
        font = QtGui.QFont('Jetbrains Mono', 7, QtGui.QFont.Light)
        painter.setFont(font)

        # 获取组件的尺寸、宽度和高度
        size = self.size()
        width = size.width()
        height = size.height()

        step = int(round(width / 10))  # 设置步长，为后续的刻度做准备

        till = int(((width / OVER_CAPACITY) * self.value))
        full = int(((width / OVER_CAPACITY) * MAX_CAPACITY))

        if self.value >= MAX_CAPACITY:
            painter.setPen(QtGui.QColor(255, 255, 255))
            painter.setBrush(QtGui.QColor(255, 255, 184))
            painter.drawRect(0, 0, full, height)

            painter.setPen(QtGui.QColor(255, 175, 175))
            painter.setBrush(QtGui.QColor(255, 175, 175))
            painter.drawRect(full, 0, till - full, height)
        else:
            painter.setPen(QtGui.QColor(255, 255, 255))
            painter.setBrush(QtGui.QColor(255, 255, 184))
            painter.drawRect(0, 0, till, height)

        # 绘制整个刻度外形
        pen = QtGui.QPen(QtGui.QColor(20, 20, 20), 1, Qt.SolidLine)
        painter.setPen(pen)
        painter.setBrush(Qt.NoBrush)
        painter.drawRect(0, 0, width - 1, height - 1)

        # 绘制刻度线
        j = 0
        for i in range(step, 10 * step, step):
            painter.drawLine(i, 0, i, 5)
            metrics = painter.fontMetrics()
            fw = metrics.width(str(self.num[j]))
            painter.drawText(int(i - fw / 2),
                             int(height / 2), str(self.num[j]))
            j = j + 1


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        OVER_CAPACITY = 750

        slider = QSlider(Qt.Horizontal, self)
        slider.setFocusPolicy(Qt.NoFocus)
        slider.setRange(1, OVER_CAPACITY)
        slider.setValue(75)
        slider.setGeometry(30, 40, 150, 30)

        # 创建 Communicator 用于 Slider 与 BurningWidget 建立联系
        self.communicator = Communicator()
        self.burning_widget = BurningWidget()
        self.communicator.update_burning_widget[int].connect(
            self.burning_widget.set_value)

        slider.valueChanged[int].connect(self.change_value)

        hbox = QHBoxLayout()
        hbox.addWidget(self.burning_widget)

        vbox = QVBoxLayout()
        vbox.addStretch(1)
        vbox.addLayout(hbox)
        self.setLayout(vbox)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Burning widget')
        self.show()

    def change_value(self, value):
        self.communicator.update_burning_widget.emit(value)
        self.burning_widget.repaint()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
