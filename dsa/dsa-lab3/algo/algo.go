package algo

import (
	"github.com/zerotwoone/dsa-lab3/graph"
	"math/rand"
	"sort"
)

type AlgoContext struct {
	n     int
	m     int
	q     int
	r     int
	graph graph.UndirectedGraph
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
	ctx.graph = graph.NewUndirectedGraph(n)
}

func (ctx *AlgoContext) GenerateGraphWithCapacity(n int, capacity int) {
	if n < 0 {
		panic("bad size")
	}

	ctx.n = n
	ctx.m = 0
	ctx.graph = graph.NewUndirectedGraphWithCapacity(n, capacity)
}

func (ctx *AlgoContext) ResizeGraph(size int) {
	ctx.graph.Resize(size)
	ctx.n = size
}

const PICK_COUNT = 5

func (ctx *AlgoContext) AddEdges(count int) {
	if count < 0 {
		panic("bad count")
	}

	if count == 0 {
		return
	}

	directedGraph := ctx.graph

	edges := directedGraph.Edges()

	for len(edges) == 0 {
		start := 0
		end := 0
		for start == end {
			start = rand.Intn(ctx.n)
			end = rand.Intn(ctx.n)
		}
		weight := rand.Intn(ctx.r-ctx.q+1) + ctx.q
		if weight == 0 {
			weight++
		}
		directedGraph.SetEdge(start, end, weight)
		edges = append(edges, graph.UndirectedEdge{Start: start, End: end, Weight: weight})
		count--
	}

	for i := 0; i < count; {
		currentNode := edges[rand.Intn(len(edges))].End
		for j := 0; j < PICK_COUNT && i < count; {
			end := rand.Intn(ctx.n)
			for currentNode == end {
				end = rand.Intn(ctx.n)
			}

			weight := directedGraph.GetEdge(currentNode, end)
			if weight != 0 {
				continue
			}
			weight = rand.Intn(ctx.r-ctx.q+1) + ctx.q
			if weight == 0 {
				weight++
			}
			directedGraph.SetEdge(currentNode, end, weight)
			edges = append(edges, graph.UndirectedEdge{Start: currentNode, End: end, Weight: weight})
			j++
			i++
			currentNode = end
		}
	}

	directedGraph.Edges()
	ctx.graph = directedGraph
	ctx.m += count
}

type PrimSet struct {
	first     int
	index     int
	indexList []int
	valueList []int
	size      int
}

func NewPrimeSet(n int) PrimSet {
	return PrimSet{-1, 0, make([]int, n), make([]int, n), 0}
}

func (set PrimSet) Size() int {
	return set.size
}

func (set PrimSet) IsEmpty() bool {
	return set.size == 0
}

func (set *PrimSet) Insert(first int, second int) {
	if set.valueList[first] == -1 && set.valueList[first] != second {
		set.size++
	}
	set.valueList[first] = second
	set.indexList[first] = set.index
	set.index++
}

func Prim(graph graph.UndirectedGraph, INF int) {
	/*set := NewPrimeSet(graph.Size())
	minE := make([]int, graph.Size())
	selE := make([]int, graph.Size())
	for i := 0; i < graph.Size(); i++ {
		minE[i] = INF
		selE[i] = -1
	}
	minE[0] = 0
	set.Insert(0, 0)
	for i := 0; i < graph.Size(); i++ {
		if set.IsEmpty() {
			return
		}

		v :=
	}*/
	used := make([]bool, graph.Size())
	minE := make([]int, graph.Size())
	selE := make([]int, graph.Size())
	for i := 0; i < graph.Size(); i++ {
		minE[i] = INF
		selE[i] = -1
	}
	minE[0] = 0

	for i := 0; i < graph.Size(); i++ {
		v := -1;
		for j := 0; j < graph.Size(); j++ {
			if !used[j] && (v == -1 || minE[j] < minE[v]) {
				v = j
			}
		}
		if minE[v] == INF {
			return
			//cout << "No MST!";
			//exit(0);
		}

		used[v] = true;
		//if selE[v] != -1 {
		//cout << v << " " << sel_e[v] << endl;
		//}
		for to := 0; to < graph.Size(); to++ {
			if to != v && graph.GetEdge(v, to) != 0 {
				if graph.GetEdge(v, to) < minE[to] {
					minE[to] = graph.GetEdge(v, to)
					selE[to] = v
				}
			}
		}
	}
}

func Kraskal(ugraph graph.UndirectedGraph) []graph.Edge {
	edges := ugraph.Edges()
	sort.Slice(edges, func(i, j int) bool {
		return edges[i].Weight < edges[j].Weight
	})

	treeId := make([]int, ugraph.Size())
	for i := 0; i < ugraph.Size(); i++ {
		treeId[i] = i
	}

	cost := 0

	res := make([]graph.Edge, 0)

	for i := 0; i < len(edges); i++ {
		start := edges[i].Start
		end := edges[i].End
		weight := edges[i].Weight
		if treeId[start] != treeId[end] {
			cost += weight
			res = append(res, graph.Edge{Start: start, End: end})
			oldId := treeId[start]
			newId := treeId[end]
			for j := 0; j < ugraph.Size(); j++ {
				if treeId[j] == oldId {
					treeId[j] = newId;
				}
			}
		}
	}

	return res
}

func (ctx AlgoContext) Kraskal() {
	Kraskal(ctx.graph)
}

func (ctx AlgoContext) Prim() {
	Prim(ctx.graph, ctx.r+1)
}
