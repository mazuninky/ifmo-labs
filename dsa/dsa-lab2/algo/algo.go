package algo

import (
	"github.com/zerotwoone/dsa-lab2/graph"
	"math/rand"
	"math"
)

type AlgoContext struct {
	n     int
	m     int
	q     int
	r     int
	graph graph.DirectedGraph
}

func (ctx AlgoContext) Edges() int {
	return ctx.m
}

func (ctx AlgoContext) Size() int {
	return ctx.n
}

func NewContext(q int, r int) AlgoContext {
	return AlgoContext{q: q, r: r}
}

func (ctx *AlgoContext) GenerateGraph(n int) {
	if n < 0 {
		panic("bad size")
	}

	ctx.n = n
	ctx.m = 0
	ctx.graph = graph.NewDirectedGraph(n)
}

func (ctx *AlgoContext) GenerateGraphWithCapacity(n int, capacity int) {
	if n < 0 {
		panic("bad size")
	}

	ctx.n = n
	ctx.m = 0
	ctx.graph = graph.NewDirectedGraphWithCapacity(n, capacity)
}

func (ctx *AlgoContext) ResizeGraph(size int) {
	ctx.graph.Resize(size)
	ctx.n = size
}

func (ctx *AlgoContext) AddEdges(count int) {
	if count < 0 {
		panic("bad count")
	}

	directedGraph := ctx.graph

	for i := 0; i < count; {
		start := 0
		end := 0
		for start == end {
			start = rand.Intn(ctx.n)
			end = rand.Intn(ctx.n)
		}
		edge := directedGraph.GetEdge(start, end)
		if edge != 0 {
			continue
		}
		edge = rand.Intn(ctx.r-ctx.q+1) + ctx.q
		if edge == 0 {
			edge++
		}
		directedGraph.SetEdge(start, end, edge)
		i++
	}

	directedGraph.Edges()

	ctx.graph = directedGraph
	ctx.m += count
}

func DijkstraMark(graph graph.DirectedGraph, s int, INF int) ([]int, []int) {
	d := make([]int, graph.Size())
	p := make([]int, graph.Size())
	u := make([]bool, graph.Size())

	for i := 0; i < graph.Size(); i++ {
		d[i] = INF
	}
	d[s] = 0

	for i := 0; i < graph.Size(); i++ {
		v := -1
		for j := 0; j < graph.Size(); j++ {
			if !u[j] && (v == -1 || d[j] < d[v]) {
				v = j
			}
		}
		if d[v] == INF {
			break
		}
		u[v] = true

		for j := 0; j < graph.Size(); j++ {
			if j != v && graph.GetEdge(v, j) != 0 {
				to := j;
				l := graph.GetEdge(v, to)
				if d[v]+l < d[to] {
					d[to] = d[v] + l;
					p[to] = v;
				}
			}
		}
	}
	return d, p
}

func FordBellman(directedGraph graph.DirectedGraph, s int, INF int) []int {
	d := make([]int, directedGraph.Size())
	for i := 0; i < directedGraph.Size(); i++ {
		d[i] = INF
	}
	d[s] = 0

	edges := directedGraph.Edges()

	for i := 0; i < directedGraph.Size()-1; i++ {
		for _, edge := range edges {
			d[edge.End] = int(math.Min(float64(d[edge.End]), float64(d[edge.Start]+edge.Weight)))
		}
	}

	return d
}

func (ctx AlgoContext) FordBellman(s int) []int {
	return FordBellman(ctx.graph, s, ctx.r+1)
}

func (ctx AlgoContext) DijkstraMark(s int) ([]int, []int) {
	return DijkstraMark(ctx.graph, s, ctx.r+1)
}
