package suite

import "github.com/zerotwoone/dsa-lab2/algo"

type Suite struct {
	context algo.AlgoContext
	first * FuncNode
	last * FuncNode
}

type SuiteContext struct {
	context * algo.AlgoContext
	next * FuncNode
}

func (ctx SuiteContext) Next()  {
	execute := ctx.next
	if execute != nil {
		ctx.next = execute.next
		execute.fun(ctx.context, ctx)
	}
}

func NewSuite(context algo.AlgoContext) Suite {
	return Suite{context:context}
}

func (suite * Suite) AddFunc(function SuiteFunc) {
	funcNode := &FuncNode{fun: function}
	if suite.first == nil {
		suite.first = funcNode
		suite.last = funcNode
	} else {
		suite.last.next = funcNode
		suite.last = funcNode
	}
	funcNode.next = suite.first
}

func (suite * Suite) Handle(functions ...SuiteFunc) {
	for _, function := range functions {
		suite.AddFunc(function)
	}
}

func (suite Suite) Process() {
	if suite.first != nil {
		ctx := SuiteContext{context:&suite.context, next: suite.first.next}
		suite.first.fun(&suite.context, ctx)
	}
}

type SuiteFunc func(context*algo.AlgoContext, c SuiteContext)

type FuncNode struct {
	fun SuiteFunc
	next *FuncNode
}