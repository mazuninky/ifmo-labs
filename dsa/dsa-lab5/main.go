package main

import (
	"fmt"
)

func compare(a int, b int) int {
	if a > b {
		return 1
	} else if a < b {
		return -1
	}
	return 0
}

func index(n int, i int, j int) int64 {
	var index int64 = 0
	for k := 0; k < i; k++ {
		index += int64(n - k - 1)
	}
	index += int64(j - i - 1)
	return index
}

func Get(matrix []int, n int, i int, j int) int {
	return matrix[index(n, i, j)]
}

func Set(matrix []int, n int, i int, j int, value int) {
	matrix[index(n, i, j)] = value
}

func main() {
	var n int
	fmt.Scan(&n)
	if n < 3 {
		fmt.Printf("0\n")
		return
	}
	sequence := make([]int, n)
	matrix := make([]int, n*(n-1)/2)

	for i := 0; i < n; i++ {
		fmt.Scan(&sequence[i])
		for j := 0; j < i; j++ {
			Set(matrix, n, j, i, compare(sequence[j], sequence[i])) //matrix[j][i] = compare(sequence[j], sequence[i])
		}
	}

	first, second, third := -1, -1, -1
mainLoop:
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			if Get(matrix, n, i, j) != 0 { //matrix[i][j] != 0
				isBigger := Get(matrix, n, i, j) == 1 //matrix[i][j] == 1
				for k := j + 1; k < n; k++ {
					if isBigger && Get(matrix, n, j, k) == -1 || !isBigger && Get(matrix, n, j, k) == 1 {
						first = i + 1
						second = j + 1
						third = k + 1
						break mainLoop
					}
				}
			}
		}
	}
	if first == -1 || second == -1 || third == -1 {
		fmt.Printf("0\n")
	} else {
		fmt.Printf("3\n")
		fmt.Printf("%d %d %d\n", first, second, third)
	}
}
