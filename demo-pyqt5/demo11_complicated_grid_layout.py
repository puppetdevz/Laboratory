#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/12
import sys

from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QLineEdit, QTextEdit, QGridLayout


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 创建三个 Label
        title = QLabel('Title')
        author = QLabel('Author')
        review = QLabel('Review')
        # 定义三个编辑部件
        title_edit = QLineEdit()
        author_edit = QLineEdit()
        review_edit = QTextEdit()
        # 定义一个栅格布局
        grid = QGridLayout()
        # 设置格子之间的空隙
        grid.setSpacing(10)
        grid.addWidget(title, 1, 0)
        grid.addWidget(title_edit, 1, 1)
        grid.addWidget(author, 2, 0)
        grid.addWidget(author_edit, 2, 1)
        grid.addWidget(review, 3, 0)
        # 表示将“文本编辑框”设置在第三行，第一列（列从 0 开始），并且大小为跨五行，跨一列
        grid.addWidget(review_edit, 3, 1, 5, 1)
        # 将栅格布局设置到窗口中
        self.setLayout(grid)

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Complicated Grid Layout')
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
