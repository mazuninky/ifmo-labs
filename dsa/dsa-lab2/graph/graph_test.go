package graph_test

import (
	"testing"
	. "github.com/zerotwoone/dsa-lab2/graph"
)


var edges = []DirectedEdge{
	{0, 1, 2},
	{1, 0, 8},
	{0,2 ,4},
	{1,3 ,3},
	{1,2 ,1},
	{1,4 ,8},
	{2,4 ,5},
	{3,4 ,1},
}

func TestGraph(t *testing.T) {
	n := 5
	testGraph := NewDirectedGraph(n)

	for _,edge := range edges {
		testGraph.SetEdge(edge.Start, edge.End, edge.Weight)
	}

	for _,edge := range edges {
		if testGraph.GetEdge(edge.Start, edge.End) != edge.Weight {
			t.Errorf("Bad weight. Expected %d but got %d", edge.Weight, testGraph.GetEdge(edge.Start, edge.End))
		}
	}
}