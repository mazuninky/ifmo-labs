package graph

import "math"

type UndirectedGraph struct {
	matrix   []int
	size     int
	capacity int
	edges    []UndirectedEdge
	changed  bool
}

func (graph *UndirectedGraph) Edges() []UndirectedEdge {
	if graph.changed {
		edges := make([]UndirectedEdge, 0)

		for v := 0; v < graph.Size(); v++ {
			for j := v; j < graph.Size(); j++ {
				if graph.GetEdge(v, j) != 0 {
					edges = append(edges, UndirectedEdge{Start: v, End: j, Weight: graph.GetEdge(v, j)})
				}
			}
		}

		graph.edges = edges
		graph.changed = false
	}

	return graph.edges
}

type UndirectedEdge struct {
	Start  int
	End    int
	Weight int
}

type Edge struct {
	Start  int
	End    int
}


func (graph UndirectedGraph) Size() int {
	return graph.size
}

func (graph UndirectedGraph) Capacity() int {
	return graph.size
}

func (graph *UndirectedGraph) SetEdge(start int, end int, weight int) {
	if start < 0 || end < 0 || start >= graph.size || end >= graph.size {
		panic("bad edge index")
	}
	graph.matrix[start*graph.size+end] = weight
	graph.matrix[end*graph.size+start] = weight
	graph.changed = true
}

func (graph *UndirectedGraph) SetEdges(edges ...UndirectedEdge) {
	for _, edge := range edges {
		graph.SetEdge(edge.Start, edge.End, edge.Weight)
	}
}

func (graph UndirectedGraph) GetEdge(start int, end int) int {
	if start < 0 || end < 0 || start >= graph.size || end >= graph.size {
		panic("bad edge index")
	}

	return graph.matrix[start*graph.size+end]
}

func NewUndirectedGraph(size int) UndirectedGraph {
	if size < 0 {
		panic("bad size")
	}

	return UndirectedGraph{matrix: make([]int, int(math.Pow(float64(size), 2))), size: size, capacity: size, edges: make([]UndirectedEdge, 0), changed: false}
}

func (graph *UndirectedGraph) Resize(size int) {
	if size > graph.capacity {
		panic("size bigger than capacity")
	}

	graph.size = size
}

func NewUndirectedGraphWithCapacity(size int, capacity int) UndirectedGraph {
	if size < 0 {
		panic("bad size")
	}

	return UndirectedGraph{matrix: make([]int, int(math.Pow(float64(capacity), 2))), size: size, capacity: capacity, edges: make([]UndirectedEdge, 0), changed: false}
}
