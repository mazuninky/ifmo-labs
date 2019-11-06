package algo_test

import (
	"testing"
	"github.com/zerotwoone/dsa-lab2/graph"
	"github.com/zerotwoone/dsa-lab2/algo"
)

func testEq(a, b []int) bool {
	if a == nil && b == nil {
		return true;
	}

	if a == nil || b == nil {
		return false;
	}

	if len(a) != len(b) {
		return false
	}

	for i := range a {
		if a[i] != b[i] {
			return false
		}
	}

	return true
}

var edges = []graph.DirectedEdge{
	{0, 1, 2},
	{1, 0, 8},
	{0,2 ,4},
	{1,3 ,3},
	{1,2 ,1},
	{1,4 ,8},
	{2,4 ,5},
	{3,4 ,1},
}

var expectedDist = []int {0, 2, 3, 5, 6}
var expectedParent = []int {0, 0, 1, 1, 3}

func TestDijkstraMark(t *testing.T) {
	n := 5
	testGraph := graph.NewDirectedGraph(n)

	for _,edge := range edges {
		testGraph.SetEdge(edge.Start, edge.End, edge.Weight)
	}

	d, p := algo.DijkstraMark(testGraph, 0, 100)

	if !testEq(d, expectedDist) {
		t.Error(d, expectedDist)
	}

	if !testEq(p, expectedParent) {
		t.Error(p, expectedParent)
	}
}

func TestFordBellman(t *testing.T) {
	n := 5
	testGraph := graph.NewDirectedGraph(n)

	for _,edge := range edges {
		testGraph.SetEdge(edge.Start, edge.End, edge.Weight)
	}

	d:= algo.FordBellman(testGraph, 0, 100)

	if !testEq(d, expectedDist) {
		t.Error(d, expectedDist)
	}
}