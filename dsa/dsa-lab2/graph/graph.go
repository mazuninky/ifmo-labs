package graph

import "math"

type DirectedGraph struct {
	matrix   []int
	size     int
	capacity int
	edges    []DirectedEdge
	changed  bool
}

func (graph DirectedGraph) Size() int {
	return graph.size
}

func (graph DirectedGraph) Capacity() int {
	return graph.size
}

func (graph *DirectedGraph) Edges() []DirectedEdge {
	if graph.changed {
		edges := make([]DirectedEdge, 0)

		for v := 0; v < graph.Size(); v++ {
			for j := 0; j < graph.Size(); j++ {
				if graph.GetEdge(v, j) != 0 {
					edges = append(edges, DirectedEdge{Start: v, End: j, Weight: graph.GetEdge(v, j)})
				}
			}
		}

		graph.edges = edges
		graph.changed = false
	}
	return graph.edges
}

type DirectedEdge struct {
	Start  int
	End    int
	Weight int
}

func (graph *DirectedGraph) SetEdge(start int, end int, weight int) {
	if start < 0 || end < 0 || start >= graph.size || end >= graph.size {
		panic("bad edge index")
	}
	graph.changed = true
	graph.matrix[start*graph.size+end] = weight
}

func (graph *DirectedGraph) SetEdges(edges ...DirectedEdge) {
	for _, edge := range edges {
		graph.SetEdge(edge.Start, edge.End, edge.Weight)
	}
}

func (graph DirectedGraph) GetEdge(start int, end int) int {
	if start < 0 || end < 0 || start >= graph.size || end >= graph.size {
		panic("bad edge index")
	}

	return graph.matrix[start*graph.size+end]
}

func NewDirectedGraph(size int) DirectedGraph {
	if size < 0 {
		panic("bad size")
	}

	return DirectedGraph{matrix: make([]int, int(math.Pow(float64(size), 2))), size: size, capacity: size, edges: make([]DirectedEdge, 0), changed: false}
}

func (graph *DirectedGraph) Resize(size int) {
	if size > graph.capacity {
		panic("size bigger than capacity")
	}

	graph.size = size
}

func NewDirectedGraphWithCapacity(size int, capacity int) DirectedGraph {
	if size < 0 {
		panic("bad size")
	}

	return DirectedGraph{matrix: make([]int, int(math.Pow(float64(capacity), 2))), size: size, capacity: capacity, edges: make([]DirectedEdge, 0), changed: false}
}
