// geometry.go
package geometry

import (
	"demo/geometry/rectangle"
	"fmt"
	"log"
)

/*
 * 包级别变量
 */
var rectLen, rectWidth float64 = 6, 7

/**
 * 通过 init 函数检查长和宽是否大于 0
 */
func init() {
	println("main package initialize")
	if rectLen < 0 {
		log.Fatal("length is less than zero")
	}
	if rectWidth < 0 {
		log.Fatal("width is less than zero")
	}
}

func call() {
	fmt.Println("Geometrical shape properties")
	fmt.Printf("area of rectangle %.2f\n", rectangle.Area(rectLen, rectWidth))
	fmt.Printf("diagonal of rectangle %.2f\n", rectangle.Diagonal(rectLen, rectWidth))
}
