package algo

import (
	"github.com/zerotwoone/dsa-lab3/graph"
	"testing"
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

var edges = []graph.UndirectedEdge{
	{0, 1, 2},
	{1, 0, 8},
	{0, 2, 4},
	{1, 3, 3},
	{1, 2, 1},
	{1, 4, 8},
	{2, 4, 5},
	{3, 4, 1},
}

func TestPrim(t *testing.T) {
	n := 5
	testGraph := graph.NewUndirectedGraph(n)

	for _, edge := range edges {
		testGraph.SetEdge(edge.Start, edge.End, edge.Weight)
	}

	Prim(testGraph, 100)

	/*if !testEq(d, expectedDist) {
		t.Error(d, expectedDist)
	}

	if !testEq(p, expectedParent) {
		t.Error(p, expectedParent)
	}*/
}

func TestKraskal(t *testing.T) {
	n := 5
	testGraph := graph.NewUndirectedGraph(n)

	for _, edge := range edges {
		testGraph.SetEdge(edge.Start, edge.End, edge.Weight)
	}

	Kraskal(testGraph)

	/*if !testEq(d, expectedDist) {
		t.Error(d, expectedDist)
	}

	if !testEq(p, expectedParent) {
		t.Error(p, expectedParent)
	}*/
}
