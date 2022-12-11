package rectangle

import (
	"fmt"
	"math"
)

func init() {
	fmt.Println("rectangle package initialized")
}

func Area(len, width float64) float64 {
	area := len * width
	return area
}

func Diagonal(length, width float64) (diagonal float64) {
	diagonal = math.Sqrt((length * length) + (width * width))
	return
}
